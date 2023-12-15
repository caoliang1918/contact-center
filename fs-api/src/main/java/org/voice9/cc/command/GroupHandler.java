package org.voice9.cc.command;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.voice9.core.constant.Constant;
import com.voice9.core.entity.CallDetail;
import com.voice9.core.entity.GroupMemory;
import com.voice9.core.entity.GroupMemoryConfig;
import com.voice9.core.enums.CauseEnums;
import com.voice9.core.enums.NextType;
import com.voice9.core.po.*;
import com.voice9.core.strategy.AgentStrategy;
import com.voice9.core.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.voice9.cc.command.base.BaseHandler;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.*;

/**
 * Create by caoliang on 2020/8/23
 * <p>
 * 进技能组
 */
@Component
public class GroupHandler extends BaseHandler {

    /**
     * 每通电话最大排队超时时间，1小时
     */
    @Value("${queue.max.timeout:3600000}")
    private Long timeout;

    /**
     * 排队电话
     */
    private Map<Long, PriorityQueue<CallQueue>> callInfoMap = new ConcurrentHashMap<>();

    /**
     * 空闲坐席 <技能组id, 空闲坐席>
     */
    private Map<Long, PriorityQueue<AgentQueue>> agentInfoMap = new ConcurrentHashMap<>();

    /**
     * 通话中坐席
     */
    private Map<Long, List<String>> callAgents = new ConcurrentHashMap<>();

    /**
     * 转接坐席线程
     */
    private ThreadPoolExecutor callAgentService = new ThreadPoolExecutor(4, 4, 0L,
            TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(), new ThreadFactoryBuilder().setNameFormat("call-agent-pool-%d").build());

    /**
     * 定时线程组
     */
    private ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(1, new ThreadFactoryBuilder().setNameFormat("acd-pool-%d").build());


    /**
     * 电话进入技能组,呼入电话有可能多次经过这
     *
     * @param callInfo
     * @param groupInfo
     * @param deviceId
     */
    public void hander(CallInfo callInfo, GroupInfo groupInfo, String deviceId) {
        if (deviceId == null || groupInfo == null) {
            return;
        }
        logger.info("callId:{} on groupId:{} groupName:{}", callInfo.getCallId(), groupInfo.getId(), groupInfo.getName());
        CallDetail joinGroup = null;
        Long now = Instant.now().toEpochMilli();
        //电话经过技能组
        joinGroup = new CallDetail();
        joinGroup.setCallId(callInfo.getCallId());
        joinGroup.setCts(now);
        joinGroup.setStartTime(now);
        joinGroup.setDetailIndex(callInfo.getCallDetails().size() + 1);
        joinGroup.setTransferType(3);
        joinGroup.setTransferId(callInfo.getGroupId());
        callInfo.getCallDetails().add(joinGroup);

        /**
         * 指定坐席
         */
        if (desiganteAgent(callInfo, groupInfo, deviceId)) {
            return;
        }

        /**
         * 走记忆坐席
         */
        if (memonryAgent(callInfo, groupInfo, deviceId)) {
            return;
        }
        Long groupId = callInfo.getGroupId();
        //排队溢出策略
        GroupOverflowPo groupOverFlow = getEffectiveOverflow(groupInfo);
        if (groupOverFlow == null) {
            logger.error("callId:{}, groupName:{} 无有效的溢出策略", callInfo.getCallId(), groupInfo.getName());
            hangupCall(callInfo.getMediaHost(), callInfo.getCallId(), deviceId);
            return;
        }
        logger.info("callId:{}, group:{} overflow:{}", callInfo.getCallId(), groupInfo.getName(), groupOverFlow.getName());

        /**
         * 1:排队,2:溢出,3:挂机
         */
        switch (groupOverFlow.getHandleType()) {
            case 1:
                logger.info("group:{} handleType is lineUp, queueTimeout:{}秒, busyType:{}, busyTimeoutType:{}, overflowType:{}, overflowValue:{}, callId:{}", groupInfo.getName(), groupOverFlow.getQueueTimeout(), groupOverFlow.getBusyType(), groupOverFlow.getBusyTimeoutType(), groupOverFlow.getOverflowType(), groupOverFlow.getOverflowValue(), callInfo.getCallId());
                PriorityQueue<CallQueue> callQueues = callInfoMap.get(groupId);
                if (callQueues == null) {
                    callQueues = new PriorityQueue<CallQueue>();
                }
                callInfo.setQueueStartTime(Instant.now().toEpochMilli());
                if (callInfo.getFristQueueTime() == null) {
                    callInfo.setFristQueueTime(callInfo.getQueueStartTime());
                }
                Long queueLevel = groupOverFlow.getLineupStrategy().calculateLevel(callInfo);
                callInfo.setQueueLevel(queueLevel);
                callQueues.add(new CallQueue(callInfo.getQueueLevel(), callInfo.getCallId(), deviceId, callInfo.getQueueStartTime() / 1000, groupId, groupOverFlow));
                callInfoMap.put(callInfo.getGroupId(), callQueues);
                cacheService.addCallInfo(callInfo);

                /**
                 * 放音
                 */
                //callInfo.getNextCommands().add(new NextCommand(deviceId, NextType.NEXT_QUEUE_PLAY, null));
                break;

            case 2:
                overFlowHandler.overflow(callInfo, deviceId, groupOverFlow);
                break;

            case 3:
                logger.info("group:{} handleType is hangup, callId:{}", groupInfo.getName(), callInfo.getCallId());
                //技能组策略挂机
                callInfo.setHangupDir(3);
                callInfo.setHangupCode(CauseEnums.OVERFLOW_TIMEOUT.getHuangupCode());
                hangupCall(callInfo.getMediaHost(), callInfo.getCallId(), deviceId);
                cacheService.addCallInfo(callInfo);
                break;
            default:
                break;
        }
    }

    /**
     * 转坐席
     *
     * @param agentInfo
     * @param callInfo
     * @param thisDeviceId
     */
    private void callAgent(AgentInfo agentInfo, CallInfo callInfo, String thisDeviceId) {
        agentNotReady(agentInfo);
        transferAgentHandler.hanlder(callInfo, agentInfo, thisDeviceId);
    }


    /**
     * 转指定坐席
     *
     * @param callInfo
     * @return
     */
    private boolean desiganteAgent(CallInfo callInfo, GroupInfo groupInfo, String deviceId) {
        if (!callInfo.getProcessData().containsKey(Constant.DESIGNATE_AGENT)) {
            return false;
        }
        GroupMemoryConfig groupMemoryConfig = groupInfo.getGroupMemoryConfig();
        AgentInfo agentInfo = cacheService.getAgentInfo(callInfo.getProcessData().get(Constant.DESIGNATE_AGENT).toString());
        if (agentInfo != null && agentInfo.getAgentState() == AgentState.READY) {
            logger.info("callId:{} get desiganteAgent:{} on group:{}", callInfo.getCallId(), agentInfo.getAgentKey(), callInfo.getGroupId());
            //呼叫坐席
            Long end = Instant.now().toEpochMilli();
            callInfo.setQueueEndTime(end);
            agentNotReady(agentInfo);
            CallDetail joinGroup = callInfo.getCallDetails().get(callInfo.getCallDetails().size() - 1);
            joinGroup.setEndTime(end);
            callAgent(agentInfo, callInfo, deviceId);
            return true;
        }
        //没有空闲坐席，走匹配失败策略
        if (groupMemoryConfig == null) {
            return false;
        }
        switch (groupMemoryConfig.getFailStrategy()) {
            case 1:
                //转其他空闲坐席
                return false;
            case 2:
                //转其他技能组
                hander(callInfo, cacheService.getGroupInfo(groupMemoryConfig.getFailStrategyValue()), deviceId);
                return true;
            case 3:
                break;
            case 4:
                break;
            case 5:
                hangupCall(callInfo.getMediaHost(), callInfo.getCallId(), deviceId);
                return true;

            default:
                break;
        }
        return false;
    }

    /**
     * 呼入记忆坐席---------------待完
     *
     * @param callInfo
     * @param groupInfo
     * @param deviceId
     * @return
     */
    private boolean memonryAgent(CallInfo callInfo, GroupInfo groupInfo, String deviceId) {
        if (groupInfo.getGroupMemoryConfig() == null || groupInfo.getGroupMemoryConfig().getStatus() == 0) {
            return false;
        }
        GroupMemoryConfig groupMemoryConfig = groupInfo.getGroupMemoryConfig();
        if (groupMemoryConfig == null || groupMemoryConfig.getStatus() != 1) {
            return false;
        }
        Long time = groupMemoryConfig.getMemoryDay() == 0 ? 0L : DateTimeUtil.addday(new Date(), -groupMemoryConfig.getMemoryDay());

        GroupMemory params = new GroupMemory();
        params.setGroupId(groupInfo.getId());
        params.setCts(time);
        params.setPhone(callInfo.getCaller());
        GroupMemory groupMemory = groupMemoryService.selectByGroup(params);

        if (groupMemory == null) {
            switch (groupMemoryConfig.getFailStrategy()) {
                case 1:
                    return false;
                case 2:
                    hander(callInfo, cacheService.getGroupInfo(groupMemoryConfig.getFailStrategyValue()), deviceId);
                    return true;
                default:
                    return false;
            }
        }
        AgentInfo agentInfo = cacheService.getAgentInfo(groupMemory.getAgentKey());
        if (agentInfo != null && agentInfo.getAgentState() == AgentState.READY) {
            transferAgentHandler.hanlder(callInfo, agentInfo, deviceId);
            return true;
        }
        //匹配上了记忆坐席
        switch (groupMemoryConfig.getSuccessStrategy()) {
            case 1:
                //一直等待记忆坐席(一直放音)
                return true;

            case 2:
                //超时转空闲坐席(放音一段时间后转其他空闲坐席)
                return true;

            case 3:
                //忙碌转空闲坐席
                break;

            default:
                return false;
        }
        return false;
    }

    /**
     * 获取空闲坐席
     *
     * @param groupId
     * @return
     */
    private AgentInfo getAgentQueue(Long groupId) {
        if (CollectionUtils.isEmpty(agentInfoMap.get(groupId))) {
            return null;
        }
        AgentQueue agentQueue = agentInfoMap.get(groupId).poll();
        if (agentQueue == null) {
            return null;
        }
        AgentInfo agentInfo = cacheService.getAgentInfo(agentQueue.getAgentKey());
        if (agentInfo == null) {
            return getAgentQueue(groupId);
        }
        return agentInfo;
    }


    /**
     * 坐席空闲
     *
     * @param agentInfo
     */
    public void agentFree(AgentInfo agentInfo) {
        if (CollectionUtils.isEmpty(agentInfo.getGroupIds())) {
            return;
        }
        agentInfo.getGroupIds().forEach(groupId -> {
            PriorityQueue<AgentQueue> agentQueues = agentInfoMap.get(groupId);
            if (agentQueues == null) {
                agentQueues = new PriorityQueue<AgentQueue>();
            }
            logger.info("agent:{} ready for group:{}", agentInfo.getAgentKey(), groupId);
            //根据空闲策略
            GroupInfo groupInfo = cacheService.getGroupInfo(groupId);
            if (groupInfo == null) {
                return;
            }
            //坐席空闲策略接口
            AgentStrategy agentStrategy = groupInfo.getGroupAgentStrategyPo().getAgentStrategy();
            Long priority = agentStrategy.calculateLevel(agentInfo);
            agentQueues.offer(new AgentQueue(priority, agentInfo.getAgentKey()));
            agentInfoMap.put(groupId, agentQueues);

        });
    }


    /**
     * 坐席忙碌
     *
     * @param agentInfo
     */
    public void agentNotReady(AgentInfo agentInfo) {
        agentInfo.getGroupIds().forEach(groupId -> {
            PriorityQueue<AgentQueue> agentQueues = agentInfoMap.get(groupId);
            if (agentQueues == null) {
                return;
            }
            logger.info("agent:{} not ready for group:{}", agentInfo.getAgentKey(), groupId);
            agentQueues.remove(new AgentQueue(1L, agentInfo.getAgentKey()));
            agentInfoMap.put(groupId, agentQueues);
            if (agentInfo.getAgentState() == AgentState.TALKING) {
                List<String> callAgentList = callAgents.get(groupId);
                if (CollectionUtils.isEmpty(callAgentList)) {
                    callAgentList = new ArrayList<>();
                }
                callAgentList.add(agentInfo.getAgentKey());
            }
            if (agentInfo.getAgentState() == AgentState.AFTER) {
                List<String> callAgentList = callAgents.get(groupId);
                if (!CollectionUtils.isEmpty(callAgentList)) {
                    callAgentList.remove(agentInfo.getAgentKey());
                }
            }
        });
    }

    /**
     * 技能组中所有空闲坐席
     *
     * @param groupId
     * @return
     */
    public List<String> getFreeAgents(Long groupId) {
        PriorityQueue<AgentQueue> agentQueues = agentInfoMap.get(groupId);
        if (CollectionUtils.isEmpty(agentQueues)) {
            return null;
        }
        List<String> freeAgents = new ArrayList<>();
        for (AgentQueue agentQueue : agentQueues) {
            freeAgents.add(agentQueue.getAgentKey());
        }
        return freeAgents;
    }

    /**
     * 删除电话
     *
     * @param groupId
     * @param callId
     */
    public void removeCall(Long groupId, Long callId) {
        if (groupId == null || callId == null) {
            return;
        }
        PriorityQueue<CallQueue> callQueues = callInfoMap.get(groupId);
        if (callQueues == null) {
            return;
        }
        callQueues.remove(callId);
    }

    /**
     * 获取有效的排队策略
     *
     * @param groupInfo
     * @return
     */
    public GroupOverflowPo getEffectiveOverflow(GroupInfo groupInfo) {
        if (CollectionUtils.isEmpty(groupInfo.getGroupOverflows())) {
            return null;
        }
        for (GroupOverflowPo groupOverflowPo : groupInfo.getGroupOverflows()) {
            PriorityQueue<CallQueue> callQueues = callInfoMap.get(groupInfo.getId());
            if (groupOverflowPo.isAvailable(callQueues == null ? 0 : callQueues.size(), groupInfo.getMaxWaitTime(), groupInfo.getCallInAnswer(), groupInfo.getCallInTotal())) {
                return groupOverflowPo;
            }
            return null;
        }
        return null;
    }

    /**
     * 超时出队列
     *
     * @param callQueue
     */
    private void queueTimeout(CallQueue callQueue) {
        Long now = Instant.now().toEpochMilli();
        CallInfo callInfo = cacheService.getCallInfo(callQueue.getCallId());
        if (callInfo == null) {
            return;
        }
        callInfo.setQueueEndTime(Instant.now().toEpochMilli());
        DeviceInfo deviceInfo = callInfo.getDeviceInfoMap().get(callQueue.deviceId);
        GroupOverflowPo groupOverflowPo = callQueue.getGroupOverflowPo();
        logger.info("callId:{} queueTimeout, busyTimeoutType:{}", callQueue.getCallId(), groupOverflowPo.getBusyTimeoutType());
        NextCommand nextCommand = null;
        switch (groupOverflowPo.getBusyTimeoutType()) {
            case 1:
                //排队超时走溢出策略,1:group,2:ivr,3:vdn
                switch (groupOverflowPo.getOverflowType()) {
                    case 1:
                        nextCommand = new NextCommand(deviceInfo.getDeviceId(), NextType.NEXT_QUEUE_OVERFLOW_GROUP, groupOverflowPo.getOverflowValue().toString());
                        break;
                    case 2:
                        nextCommand = new NextCommand(deviceInfo.getDeviceId(), NextType.NEXT_QUEUE_OVERFLOW_IVR, groupOverflowPo.getOverflowValue().toString());
                        break;
                    case 3:
                        nextCommand = new NextCommand(deviceInfo.getDeviceId(), NextType.NEXT_QUEUE_OVERFLOW_VDN, groupOverflowPo.getOverflowValue().toString());
                        break;

                    default:
                        break;
                }
                break;
            case 2:
                //排队超时挂机
                callInfo.setHangupDir(3);
                callInfo.setHangupCode(CauseEnums.QUEUE_TIMEOUT.getHuangupCode());
                nextCommand = new NextCommand(deviceInfo.getDeviceId(), NextType.NEXT_HANGUP, groupOverflowPo.getOverflowValue().toString());
                break;

            default:
                logger.warn("============:{}", callQueue);
                break;
        }
        callInfo.setQueueEndTime(now);
        if (!CollectionUtils.isEmpty(callInfo.getCallDetails())) {
            CallDetail callDetail = callInfo.getCallDetails().get(callInfo.getCallDetails().size() - 1);
            if (callDetail != null) {
                callDetail.setEndTime(now);
            }
        }
        fsListen.playBreak(callInfo.getMediaHost(), callQueue.getDeviceId());
        doNextCommand(callInfo, deviceInfo, nextCommand);
    }

    /**
     * 进入到队列的电话，需要定时找空闲坐席
     */
    private void acd() {
        Long now = Instant.now().toEpochMilli();
        //技能组先排序,默认按照技能组优先级，如果优先级相等，则最
        List<GroupInfo> groupInfos = new ArrayList<>();
        for (Long id : callInfoMap.keySet()) {
            groupInfos.add(cacheService.getGroupInfo(id));
        }
        Collections.sort(groupInfos, new Comparator<GroupInfo>() {
            @Override
            public int compare(GroupInfo o1, GroupInfo o2) {
                return o1.getLevelValue().equals(o2.getLevelValue()) ?
                        o2.getLastServiceTime().compareTo(o1.getLastServiceTime()) :
                        o1.getLevelValue().compareTo(o2.getLevelValue());
            }
        });

        for (GroupInfo groupInfo : groupInfos) {
            PriorityQueue<CallQueue> entry = callInfoMap.get(groupInfo.getId());
            if (CollectionUtils.isEmpty(entry)) {
                continue;
            }

            Iterator<CallQueue> iterator = entry.iterator();
            while (iterator.hasNext()) {
                CallQueue callQueue = iterator.next();
                //正常排队超时
                if (now / 1000 - callQueue.getStartTime() >= callQueue.getGroupOverflowPo().getQueueTimeout().longValue()) {
                    callAgentService.execute(() -> {
                        queueTimeout(callQueue);
                    });
                    iterator.remove();
                    continue;
                }

                //查找空闲坐席
                AgentInfo agentInfo = this.getAgentQueue(groupInfo.getId());
                if (agentInfo == null) {
                    if (!callQueue.isPlay()) {
                        CallInfo callInfo = cacheService.getCallInfo(callQueue.getCallId());
                        fsListen.playfile(callInfo.getMediaHost(), callQueue.getDeviceId(), "queue.wav");
                        callQueue.setPlay(true);
                    }
                    continue;
                }
                iterator.remove();
                //先停止放音
                CallInfo callInfo = cacheService.getCallInfo(callQueue.getCallId());
                if (callInfo == null) {
                    continue;
                }
                callAgentService.execute(() -> {
                    if (callQueue.isPlay()) {
                        fsListen.playBreak(callInfo.getMediaHost(), callQueue.getDeviceId());
                    }
                    callInfo.setQueueEndTime(now);
                    this.callAgent(agentInfo, callInfo, callQueue.getDeviceId());
                });
                //记录最后服务时间
                groupInfo.setLastServiceTime(now);
            }
        }
    }

    public void stop() {
        callAgentService.shutdown();
        scheduledExecutorService.shutdown();
    }

    public void start() {
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            acd();
        }, 5, 1, TimeUnit.SECONDS);
    }


    class AgentQueue implements Comparable<AgentQueue> {
        private Long priority;

        private String agentKey;

        public AgentQueue(Long priority, String agentKey) {
            this.priority = priority;
            this.agentKey = agentKey;
        }

        public Long getPriority() {
            return priority;
        }

        public void setPriority(Long priority) {
            this.priority = priority;
        }

        public String getAgentKey() {
            return agentKey;
        }

        public void setAgentKey(String agentKey) {
            this.agentKey = agentKey;
        }

        @Override
        public int compareTo(AgentQueue o) {
            return o.priority.compareTo(this.priority);
        }

        @Override
        public boolean equals(Object obj) {
            AgentQueue agentQueue = (AgentQueue) obj;
            return this.agentKey.equals(agentQueue.getAgentKey());
        }
    }


    class CallQueue implements Comparable<CallQueue> {
        private Long priority;

        private Long callId;

        private Long startTime;

        private Long groupId;

        private GroupOverflowPo groupOverflowPo;

        private String deviceId;

        private boolean play;

        public CallQueue(Long priority, Long callId, String deviceId, Long startTime, Long groupId, GroupOverflowPo groupOverflowPo) {
            this.priority = priority;
            this.callId = callId;
            this.startTime = startTime;
            this.deviceId = deviceId;
            this.groupId = groupId;
            this.groupOverflowPo = groupOverflowPo;
        }

        public Long getPriority() {
            return priority;
        }

        public void setPriority(Long priority) {
            this.priority = priority;
        }

        public Long getCallId() {
            return callId;
        }

        public void setCallId(Long callId) {
            this.callId = callId;
        }

        public Long getStartTime() {
            return startTime;
        }

        public void setStartTime(Long startTime) {
            this.startTime = startTime;
        }

        public Long getGroupId() {
            return groupId;
        }

        public void setGroupId(Long groupId) {
            this.groupId = groupId;
        }

        public GroupOverflowPo getGroupOverflowPo() {
            return groupOverflowPo;
        }

        public void setGroupOverflowPo(GroupOverflowPo groupOverflowPo) {
            this.groupOverflowPo = groupOverflowPo;
        }

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }

        public boolean isPlay() {
            return play;
        }

        public void setPlay(boolean play) {
            this.play = play;
        }

        @Override
        public int compareTo(CallQueue o) {
            return priority.compareTo(this.priority);
        }
    }
}
