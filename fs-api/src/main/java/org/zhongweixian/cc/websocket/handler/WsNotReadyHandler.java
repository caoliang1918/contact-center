package org.zhongweixian.cc.websocket.handler;

import org.cti.cc.enums.ErrorCode;
import org.cti.cc.po.AgentInfo;
import org.cti.cc.po.AgentState;
import org.springframework.stereotype.Component;
import org.zhongweixian.cc.configration.HandlerType;
import org.zhongweixian.cc.websocket.event.WsNotReadyEvent;
import org.zhongweixian.cc.websocket.handler.base.WsBaseHandler;
import org.zhongweixian.cc.websocket.response.WsResponseEntity;

import java.time.Instant;

/**
 * Create by caoliang on 2020/11/1
 * <p>
 * 坐席忙碌
 * 2001 示忙失败
 */
@Component
@HandlerType("WS_NOT_READY")
public class WsNotReadyHandler extends WsBaseHandler<WsNotReadyEvent> {
    @Override
    public void handleEvent(WsNotReadyEvent event) {
        AgentInfo agentInfo = getAgent(event);
        if (agentInfo == null || agentInfo.getStatus() == 0) {
            event.getChannel().close();
            return;
        }
        if (agentInfo.getAgentState() == AgentState.NOT_READY) {
            logger.warn("agent:{} NOT_READY error, agent state:{}", event.getAgentKey(), agentInfo.getAgentState());
            sendMessgae(event, new WsResponseEntity<String>(ErrorCode.AGENT_ALREADY_NOT_READY, event.getCmd(), event.getAgentKey()));
            return;
        }
        if (agentInfo.getAgentState().name().contains("CALL") || agentInfo.getAgentState().name().contains("TALK")) {
            logger.warn("agent:{} NOT_READY error, agent state:{}", event.getAgentKey(), agentInfo.getAgentState());
            sendMessgae(event, new WsResponseEntity<String>(ErrorCode.AGENT_CALLING, event.getCmd(), event.getAgentKey()));
            return;
        }

        sendMessgae(event, new WsResponseEntity<>(event.getCmd(), event.getAgentKey()));

        agentInfo.setBeforeState(agentInfo.getAgentState());
        agentInfo.setBeforeTime(agentInfo.getStateTime());
        agentInfo.setAgentState(AgentState.NOT_READY);
        agentInfo.setStateTime(Instant.now().toEpochMilli());

        /**
         * 广播坐席状态
         */
        sendAgentStateMessage(agentInfo);
    }
}
