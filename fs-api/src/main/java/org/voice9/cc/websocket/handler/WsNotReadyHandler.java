package org.voice9.cc.websocket.handler;

import com.voice9.core.enums.ErrorCode;
import com.voice9.core.po.AgentInfo;
import com.voice9.core.po.AgentState;
import org.springframework.stereotype.Component;
import org.voice9.cc.configration.HandlerType;
import org.voice9.cc.websocket.handler.base.WsBaseHandler;
import org.voice9.cc.websocket.response.WsResponseEntity;
import org.voice9.cc.websocket.event.WsNotReadyEvent;

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
            if (event.getChannel() != null) {
                event.getChannel().close();
            }
            return;
        }
        if (agentInfo.getAgentState() == AgentState.NOT_READY) {
            logger.warn("agent:{} already NOT_READY, agent state:{}", event.getAgentKey(), agentInfo.getAgentState());
            sendMessage(event, new WsResponseEntity<String>(ErrorCode.AGENT_ALREADY_NOT_READY, event.getCmd(), event.getAgentKey()));
            return;
        }
        if (agentInfo.getAgentState().name().contains("CALL") || agentInfo.getAgentState().name().contains("TALK")) {
            logger.warn("agent:{} NOT_READY error, agent state:{}", event.getAgentKey(), agentInfo.getAgentState());
            sendMessage(event, new WsResponseEntity<String>(ErrorCode.AGENT_CALLING, event.getCmd(), event.getAgentKey()));
            return;
        }

        sendMessage(event, new WsResponseEntity<>(event.getCmd(), event.getAgentKey()));

        agentInfo.setBeforeState(agentInfo.getAgentState());
        agentInfo.setBeforeTime(agentInfo.getStateTime());
        agentInfo.setAgentState(AgentState.NOT_READY);
        agentInfo.setStateTime(Instant.now().getEpochSecond());

        /**
         * 广播坐席状态
         */
        syncAgentStateMessage(agentInfo);
    }
}
