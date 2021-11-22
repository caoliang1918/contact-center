package org.zhongweixian.cc.command;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.cti.cc.constant.Constants;
import org.cti.cc.entity.CallDetail;
import org.cti.cc.entity.GroupMemory;
import org.cti.cc.entity.GroupMemoryConfig;
import org.cti.cc.enums.CauseEnums;
import org.cti.cc.enums.NextType;
import org.cti.cc.po.*;
import org.cti.cc.strategy.AgentStrategy;
import org.cti.cc.util.DateTimeUtil;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.zhongweixian.cc.command.base.BaseHandler;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Create by caoliang on 2020/8/23
 * <p>
 * 进技能组
 */
@Component
public class GroupHandler extends BaseHandler {

    /**
     * 排队电话
     */
    private Map<Long, PriorityQueue<CallQueue>> callInfoMap = new ConcurrentHashMap<>();

    /**
     * 空闲坐席
     */
    private Map<Long, PriorityQueue<AgentQueue>> agentInfoMap = new ConcurrentHashMap<>();


    /**
     * 转接坐席线程
     */
    private ThreadPoolExecutor callAgentService = new ThreadPoolExecutor(4, 4, 0L,
            TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(), new ThreadFactoryBuilder().setNameFormat("call-agent-pool-%d").build());

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
        if (callInfo.getFristQueueTime() == null) {
            callInfo.setFristQueueTime(now);
        }
        //电话经过技能组
        joinGroup = new CallDetail();
        joinGroup.setCallId(callInfo.getCallId());
        joinGroup.setCts(now);
        joinGroup.setStartTime(now);
        joinGroup.setDetailIndex(callInfo.getCallDetails().size() + 1);
        joinGroup.setTransferType(3);
        joinGroup.setTransferId(callInfo.getGroupId());
        callInfo.getCallDetails().add(joinGroup);
        callInfo.setQueueStartTime(now);

        AgentInfo agentInfo = null;
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
        agentInfo = getAgentQueue(groupId);
        if (agentInfo != null) {
            logger.info("callId:{} get free agent:{} on group:{}", callInfo.getCallId(), agentInfo.getAgentKey(), groupId);
            //呼叫坐席
            Long end = Instant.now().toEpochMilli();
            callInfo.setQueueEndTime(end);
            agentNotReady(agentInfo);
            if (joinGroup == null) {
                joinGroup = callInfo.getCallDetails().get(callInfo.getCallDetails().size() - 1);
            }
            joinGroup.setEndTime(end);
            callAgent(agentInfo, callInfo, deviceId);
            return;
        }
        logger.info("callId:{} join group:{} agent busy", callInfo.getCallId(), groupInfo.getId());

        //排队溢出策略
        GroupOverflowPo groupOverFlow = getEffectiveOverflow(groupInfo);
        if (groupOverFlow == null) {
            logger.error("callId:{}, groupName:{} 无有效的溢出策略", callInfo.getCallId(), groupInfo.getName());
            hangupCall(callInfo.getMedia(), callInfo.getCallId(), deviceId);
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
                Long queueLevel = groupOverFlow.getLineupStrategy().calculateLevel(callInfo);
                callInfo.setQueueLevel(queueLevel);
                callQueues.add(new CallQueue(callInfo.getQueueLevel(), callInfo.getCallId(), deviceId, callInfo.getQueueStartTime() / 1000, groupId, groupOverFlow));
                callInfoMap.put(callInfo.getGroupId(), callQueues);

                /**
                 * 放音
                 */
                fsListen.playback(callInfo.getMedia(), deviceId, "sounds/queue.wav");
                callInfo.getNextCommands().add(new NextCommand(deviceId, NextType.NEXT_QUEUE_PLAY, null));
                break;

            case 2:
                overFlowHandler.overflow(callInfo, deviceId, groupOverFlow);
                break;

            case 3:
                logger.info("group:{} handleType is hangup, callId:{}", groupInfo.getName(), callInfo.getCallId());
                //技能组策略挂机
                callInfo.setHangupDir(3);
                callInfo.setHangupCode(CauseEnums.OVERFLOW_TIMEOUT.getHuangupCode());
                hangupCall(callInfo.getMedia(), callInfo.getCallId(), deviceId);
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
        transferAgentHandler.hanlder(callInfo, agentInfo, thisDeviceId);
    }


    /**
     * 转指定坐席
     *
     * @param callInfo
     * @return
     */
    private boolean desiganteAgent(CallInfo callInfo, GroupInfo groupInfo, String deviceId) {
        if (!callInfo.getProcessData().containsKey(Constants.DESIGNATE_AGENT)) {
            return false;
        }
        GroupMemoryConfig groupMemoryConfig = groupInfo.getGroupMemoryConfig();
        AgentInfo agentInfo = cacheService.getAgentInfo(callInfo.getProcessData().get(Constants.DESIGNATE_AGENT).toString());
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
                hangupCall(callInfo.getMedia(), callInfo.getCallId(), deviceId);
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
                callInfo.setQueueEndTime(Instant.now().toEpochMilli());
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
        fsListen.playbreak(callInfo.getMedia(), callQueue.getDeviceId());
        doNextCommand(callInfo, deviceInfo, nextCommand);
    }

    /**
     * 进入到队列的电话，需要定时找空闲坐席
     */
    public void acd() {
        Long now = Instant.now().getEpochSecond();
        for (Map.Entry<Long, PriorityQueue<CallQueue>> entry : callInfoMap.entrySet()) {
            if (CollectionUtils.isEmpty(entry.getValue())) {
                continue;
            }
            Iterator<CallQueue> iterator = entry.getValue().iterator();
            while (iterator.hasNext()) {
                CallQueue callQueue = iterator.next();
                if (now - callQueue.getStartTime() >= callQueue.getGroupOverflowPo().getQueueTimeout().longValue()) {
                    callAgentService.execute(() -> {
                        queueTimeout(callQueue);
                    });
                    iterator.remove();
                    continue;
                }
                //查找空闲坐席
                AgentInfo agentInfo = this.getAgentQueue(entry.getKey());
                if (agentInfo != null) {
                    iterator.remove();
                    //先停止放音
                    CallInfo callInfo = cacheService.getCallInfo(callQueue.getCallId());
                    if (callInfo == null) {
                        continue;
                    }
                    DeviceInfo deviceInfo = callInfo.getDeviceInfoMap().get(callQueue.getDeviceId());
                    callAgentService.execute(() -> {
                        fsListen.playbreak(callInfo.getMedia(), callQueue.getDeviceId());
                        this.callAgent(agentInfo, callInfo, callQueue.getDeviceId());
                    });
                }
            }
        }

    }

    public void stop() {
        callAgentService.shutdown();
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


    class CallQueue {
        private Long priority;

        private Long callId;

        private Long startTime;

        private Long groupId;

        private GroupOverflowPo groupOverflowPo;

        private String deviceId;

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


    }
}
