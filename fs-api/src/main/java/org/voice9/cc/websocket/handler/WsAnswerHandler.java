package org.voice9.cc.websocket.handler;

import com.voice9.core.enums.ErrorCode;
import com.voice9.core.po.AgentInfo;
import com.voice9.core.po.AgentState;
import com.voice9.core.po.CallInfo;
import com.voice9.core.po.DeviceInfo;
import org.springframework.stereotype.Component;
import org.voice9.cc.configration.HandlerType;
import org.voice9.cc.fs.esl.transport.message.EslMessage;
import org.voice9.cc.websocket.handler.base.WsBaseHandler;
import org.voice9.cc.websocket.response.WsResponseEntity;
import org.voice9.cc.websocket.event.WsAnswerEvent;

/**
 * Created by caoliang on 2020/11/6
 */
@Component
@HandlerType("WS_ANSWER")
public class WsAnswerHandler extends WsBaseHandler<WsAnswerEvent> {

    @Override
    public void handleEvent(WsAnswerEvent event) {
        AgentInfo agentInfo = getAgent(event);
        if (agentInfo.getCallId() == null || agentInfo.getDeviceId() == null) {
            sendMessage(event, new WsResponseEntity<>(ErrorCode.CALL_NOT_EXIST, event.getCmd(), event.getAgentKey()));
            return;
        }
        /**
         * 判断电话是否存在
         */
        CallInfo callInfo = cacheService.getCallInfo(agentInfo.getCallId());
        if (callInfo == null || callInfo.getDeviceInfoMap().get(agentInfo.getDeviceId()) == null) {
            agentInfo.setCallId(null);
            agentInfo.setDeviceId(null);
            agentInfo.setAgentState(AgentState.NOT_READY);
            logger.warn("agentKey:{} not find call", event.getAgentKey());
            sendMessage(event, new WsResponseEntity<>(ErrorCode.CALL_NOT_EXIST, event.getCmd(), event.getAgentKey()));
            return;
        }
        /**
         * 判断电话是否已经应答
         */
        DeviceInfo deviceInfo = callInfo.getDeviceInfoMap().get(agentInfo.getDeviceId());
        if (deviceInfo.getAnswerTime()!=null) {
            sendMessage(event, new WsResponseEntity<>(ErrorCode.CALL_ANSWERED, event.getCmd(), event.getAgentKey()));
            return;
        }
        EslMessage message = answer(callInfo.getMediaHost(), agentInfo.getDeviceId());
        if (message != null) {
            logger.info("{}", message.getBodyLines());
        }

    }
}
