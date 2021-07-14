package org.zhongweixian.cc.websocket.handler;

import org.cti.cc.enums.ErrorCode;
import org.cti.cc.po.AgentInfo;
import org.cti.cc.po.AgentState;
import org.springframework.stereotype.Component;
import org.zhongweixian.cc.configration.HandlerType;
import org.zhongweixian.cc.websocket.event.WsReadyEvent;
import org.zhongweixian.cc.websocket.handler.base.WsBaseHandler;
import org.zhongweixian.cc.websocket.response.WsResponseEntity;

import java.time.Instant;

/**
 * Create by caoliang on 2020/11/1
 * <p>
 * 坐席空闲
 * 2002 示闲失败
 */
@Component
@HandlerType("WS_READY")
public class WsReadyHandler extends WsBaseHandler<WsReadyEvent> {

    @Override
    public void handleEvent(WsReadyEvent event) {
        AgentInfo agentInfo = getAgent(event);
        if (agentInfo == null || agentInfo.getStatus() == 0) {
            event.getChannel().close();
            return;
        }
        if (agentInfo.getAgentState() == AgentState.READY) {
            logger.warn("agent:{} READY error, agent state:{}", event.getAgentKey(), agentInfo.getAgentState());
            sendMessgae(event, new WsResponseEntity<String>(ErrorCode.AGENT_ALREADY_READY, event.getCmd(), event.getAgentKey()));
            return;
        }
        if (agentInfo.getAgentState().name().contains("CALL") || agentInfo.getAgentState().name().contains("TALK")) {
            logger.warn("agent:{} READY error, agent state:{}", event.getAgentKey(), agentInfo.getAgentState());
            sendMessgae(event, new WsResponseEntity<String>(ErrorCode.AGENT_CALLING, event.getCmd(), event.getAgentKey()));
            return;
        }

        sendMessgae(event, new WsResponseEntity<>(event.getCmd(), event.getAgentKey()));
        agentInfo.setBeforeState(agentInfo.getAgentState());
        agentInfo.setBeforeTime(agentInfo.getStateTime());
        agentInfo.setAgentState(AgentState.READY);
        agentInfo.setStateTime(Instant.now().toEpochMilli());
        /**
         * 广播坐席状态
         */
        sendAgentStateMessage(agentInfo);
    }
}
