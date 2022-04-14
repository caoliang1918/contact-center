package org.zhongweixian.cc.websocket.handler;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.cti.cc.enums.ErrorCode;
import org.cti.cc.po.AgentInfo;
import org.cti.cc.po.AgentState;
import org.cti.cc.po.GroupInfo;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.zhongweixian.cc.configration.HandlerType;
import org.zhongweixian.cc.entity.AgentMonitorVo;
import org.zhongweixian.cc.websocket.event.WsMonitorEvent;
import org.zhongweixian.cc.websocket.handler.base.WsBaseHandler;
import org.zhongweixian.cc.websocket.response.WsResponseEntity;

import java.util.*;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Create by caoliang on 2020/11/10
 */
@Component
@HandlerType("WS_MONITOR")
public class WsMonitorHandler extends WsBaseHandler<WsMonitorEvent> {

    /**
     * 正在监控的班长坐席
     */
    private Map<String, WsMonitorEvent> monitorEventMap = new HashMap<>();

    /**
     * 定时线程
     */
    private ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(1, new ThreadFactoryBuilder().setNameFormat("agent-monitor-pool-%d").build());


    @Override
    public void handleEvent(WsMonitorEvent event) {
        if (monitorEventMap.containsKey(event.getAgentKey())) {
            return;
        }
        AgentInfo monitor = getAgent(event);
        if (monitor.getAgentType() != 2) {
            sendMessage(event, new WsResponseEntity<String>(ErrorCode.ACCOUNT_AUTH_ERROR, event.getCmd(), event.getAgentKey()));
            return;
        }
        List<Long> groupIds = CollectionUtils.isEmpty(event.getGroupIds()) ? monitor.getGroupIds() : event.getGroupIds();
        logger.info("agent:{} monitor group:{}", event.getAgentKey(), groupIds.toString());
        if (CollectionUtils.isEmpty(groupIds)) {
            logger.warn("agent:{} monitor group is empty", monitor.getAgentKey());
            return;
        }
        event.setGroupIds(groupIds);
        monitorEventMap.put(event.getAgentKey(), event);
        sendMessage(event, new WsResponseEntity<>(AgentState.MONITOR.name(), event.getAgentKey()));
    }


    /**
     * 启动班长监控
     */
    public void start() {
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            if (CollectionUtils.isEmpty(monitorEventMap)) {
                return;
            }
            Iterator<WsMonitorEvent> iterator = monitorEventMap.values().iterator();
            while (iterator.hasNext()) {
                WsMonitorEvent monitorEvent = iterator.next();
                AgentInfo agentInfo = cacheService.getAgentInfo(monitorEvent.getAgentKey());
                if (agentInfo.getAgentState() == AgentState.LOGOUT) {
                    iterator.remove();
                    continue;
                }
                //当前班长监控的技能组集合
                List<Long> groupIds = monitorEvent.getGroupIds();

                //当前班长监控的坐席集合
                List<String> agents = new ArrayList<>();
                Set<AgentMonitorVo> monitorVos = null;
                for (Long groupId : groupIds) {
                    monitorVos = new HashSet<>();
                    GroupInfo groupInfo = cacheService.getGroupInfo(groupId);
                    if (groupInfo == null) {
                        continue;
                    }
                    for (String agentKey : groupInfo.getOnlineAgents()) {
                        if (agents.contains(agentKey)) {
                            continue;
                        }
                        agents.add(agentKey);
                        AgentInfo agent = cacheService.getAgentInfo(agentKey);
                        AgentMonitorVo agentMonitorVo = new AgentMonitorVo();
                        agentMonitorVo.setAgentKey(agent.getAgentKey());
                        agentMonitorVo.setCts(agent.getStateTime());
                        agentMonitorVo.setAgentState(agent.getAgentState());
                        agentMonitorVo.setCallId(agent.getCallId());
                        monitorVos.add(agentMonitorVo);
                    }
                    sendMessage(monitorEvent, monitorVos);
                }
            }
        }, 10, 5, TimeUnit.SECONDS);
    }

    public void stop() {
        scheduledExecutorService.shutdownNow();
    }


}
