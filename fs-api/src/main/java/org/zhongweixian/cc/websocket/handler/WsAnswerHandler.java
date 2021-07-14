package org.zhongweixian.cc.websocket.handler;

import org.cti.cc.po.AgentInfo;
import org.cti.cc.po.AgentState;
import org.cti.cc.po.CallInfo;
import org.springframework.stereotype.Component;
import org.zhongweixian.cc.configration.HandlerType;
import org.zhongweixian.cc.websocket.event.WsAnswerEvent;
import org.zhongweixian.cc.websocket.handler.base.WsBaseHandler;
import org.zhongweixian.esl.transport.message.EslMessage;

/**
 * Created by caoliang on 2020/11/6
 */
@Component
@HandlerType("WS_ANSWER")
public class WsAnswerHandler extends WsBaseHandler<WsAnswerEvent> {

    @Override
    public void handleEvent(WsAnswerEvent event) {
        AgentInfo agentInfo = getAgent(event);
        CallInfo callInfo = cacheService.getCallInfo(agentInfo.getCallId());
        if (callInfo == null || callInfo.getMedia() == null) {
            agentInfo.setCallId(null);
            agentInfo.setDeviceId(null);
            agentInfo.setAgentState(AgentState.NOT_READY);
            logger.warn("agentKey:{} not find call", event.getAgentKey());
            return;
        }
        if (callInfo.getAnswerTime() != null) {
            return;
        }

        EslMessage message = answer(callInfo.getMedia(), agentInfo.getDeviceId());
        if (message != null) {
            logger.info("{}", message.getBodyLines());
        }

    }
}
