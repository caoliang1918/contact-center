package org.zhongweixian.cc.websocket.handler;

import org.apache.commons.lang3.StringUtils;
import org.cti.cc.po.AgentInfo;
import org.cti.cc.po.AgentState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.zhongweixian.cc.configration.HandlerType;
import org.zhongweixian.cc.websocket.WebSocketHandler;
import org.zhongweixian.cc.websocket.event.WsLogoutEvent;
import org.zhongweixian.cc.websocket.handler.base.WsBaseHandler;

import java.time.Instant;

/**
 * Created by caoliang on 2020/10/30
 * <p>
 * 坐席退出
 */
@Component
@HandlerType("WS_LOGOUT")
public class WsLogoutHandler extends WsBaseHandler<WsLogoutEvent> {

    @Autowired
    private WsMonitorHandler wsMonitorHandler;

    @Autowired
    private WebSocketHandler webSocketHandler;

    @Override
    public void handleEvent(WsLogoutEvent event) {
        if (StringUtils.isBlank(event.getAgentKey())) {
            return;
        }
        logger.info("agent {} logout", event.getAgentKey());
        if (event.getChannel() != null && event.getChannel().isOpen()) {
            event.getChannel().close();
        }

        webSocketHandler.removeAgentChannel(event.getAgentKey());

        AgentInfo agentInfo = getAgent(event);
        if (agentInfo == null || agentInfo.getAgentState() == AgentState.LOGOUT) {
            return;
        }
        //班长坐席关闭班长监控
        if (agentInfo.getAgentType() == 2) {
            wsMonitorHandler.monitorCancel(event.getAgentKey());
        }

        agentInfo.setBeforeState(agentInfo.getAgentState());
        agentInfo.setBeforeTime(agentInfo.getStateTime());
        agentInfo.setStateTime(Instant.now().toEpochMilli());
        agentInfo.setLogoutTime(agentInfo.getStateTime());
        agentInfo.setLoginTime(0L);
        agentInfo.setAgentState(AgentState.LOGOUT);
        agentInfo.setDeviceId(null);
        agentInfo.setCallId(null);

        /**
         *  广播坐席状态
         */
        sendAgentStateMessage(agentInfo);
    }
}
