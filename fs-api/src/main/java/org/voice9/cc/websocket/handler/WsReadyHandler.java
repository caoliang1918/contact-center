package org.voice9.cc.websocket.handler;

import com.voice9.core.enums.ErrorCode;
import com.voice9.core.po.AgentInfo;
import com.voice9.core.po.AgentState;
import org.springframework.stereotype.Component;
import org.voice9.cc.configration.HandlerType;
import org.voice9.cc.websocket.handler.base.WsBaseHandler;
import org.voice9.cc.websocket.response.WsResponseEntity;
import org.voice9.cc.websocket.event.WsReadyEvent;

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
            logger.warn("agent:{} already READY, agent state:{}", event.getAgentKey(), agentInfo.getAgentState());
            sendMessage(event, new WsResponseEntity<String>(ErrorCode.AGENT_ALREADY_READY, event.getCmd(), event.getAgentKey()));
            return;
        }
        if (agentInfo.getAgentState().name().contains("CALL") || agentInfo.getAgentState().name().contains("TALKING")) {
            logger.warn("agent:{} READY error, agent state:{}", event.getAgentKey(), agentInfo.getAgentState());
            sendMessage(event, new WsResponseEntity<String>(ErrorCode.AGENT_CALLING, event.getCmd(), event.getAgentKey()));
            return;
        }
        //坐席空闲时刷新token时间
        cacheService.refleshAgentToken(agentInfo.getAgentKey(), agentInfo.getToken());

        sendMessage(event, new WsResponseEntity<>(event.getCmd(), event.getAgentKey()));
        agentInfo.setBeforeState(agentInfo.getAgentState());
        agentInfo.setBeforeTime(agentInfo.getStateTime());
        agentInfo.setAgentState(AgentState.READY);
        agentInfo.setStateTime(Instant.now().getEpochSecond());
        /**
         * 广播坐席状态
         */
        syncAgentStateMessage(agentInfo);
    }
}
