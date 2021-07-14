package org.zhongweixian.cc.websocket.handler;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.cti.cc.po.AgentInfo;
import org.cti.cc.po.GroupInfo;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.zhongweixian.cc.configration.HandlerType;
import org.zhongweixian.cc.entity.AgentMonitorVo;
import org.zhongweixian.cc.websocket.event.WsMonitorEvent;
import org.zhongweixian.cc.websocket.handler.base.WsBaseHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        List<Long> groupIds = monitor.getGroupIds();
        logger.info("agent:{} monitor group:{}", event.getAgentKey(), groupIds.toString());
        groupIds.forEach(id -> {
            List<AgentMonitorVo> monitorVos = new ArrayList<>();
            GroupInfo groupInfo = cacheService.getGroupInfo(id);
            if (groupInfo == null) {
                return;
            }
            groupInfo.getOnlineAgents().forEach(key -> {
                AgentInfo agent = cacheService.getAgentInfo(key);
                AgentMonitorVo agentMonitorVo = new AgentMonitorVo();
                agentMonitorVo.setAgentKey(agent.getAgentKey());
                agentMonitorVo.setCts(agent.getStateTime());
                agentMonitorVo.setAgentState(agent.getAgentState());
                agentMonitorVo.setCallId(agent.getCallId());
                monitorVos.add(agentMonitorVo);
            });
            sendMessgae(event, monitorVos);
        });
    }

    public void monitorCancel(String agentKey) {
        monitorEventMap.remove(agentKey);
    }

    /**
     * 班长定时监听坐席
     */
    public void start() {
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            if (CollectionUtils.isEmpty(monitorEventMap)) {
                return;
            }
            monitorEventMap.forEach((k, v) -> {
                handleEvent(v);
            });
        }, 30, 5, TimeUnit.SECONDS);
    }

    public void stop() {
        scheduledExecutorService.shutdown();
    }
}
