package org.zhongweixian.cc.websocket.handler;

import org.cti.cc.enums.ErrorCode;
import org.cti.cc.po.AgentInfo;
import org.cti.cc.po.AgentState;
import org.cti.cc.po.CallInfo;
import org.cti.cc.po.DeviceInfo;
import org.springframework.stereotype.Component;
import org.zhongweixian.cc.configration.HandlerType;
import org.zhongweixian.cc.websocket.event.WsAnswerEvent;
import org.zhongweixian.cc.websocket.handler.base.WsBaseHandler;
import org.zhongweixian.cc.websocket.response.WsResponseEntity;
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
        if (agentInfo.getCallId() == null || agentInfo.getDeviceId() == null) {
            sendMessgae(event, new WsResponseEntity<>(ErrorCode.CALL_NOT_EXIST, event.getCmd(), event.getAgentKey()));
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
            sendMessgae(event, new WsResponseEntity<>(ErrorCode.CALL_NOT_EXIST, event.getCmd(), event.getAgentKey()));
            return;
        }
        /**
         * 判断电话是否已经应答
         */
        DeviceInfo deviceInfo = callInfo.getDeviceInfoMap().get(agentInfo.getDeviceId());
        if (deviceInfo.getAnswerTime()!=null) {
            sendMessgae(event, new WsResponseEntity<>(ErrorCode.CALL_ANSWERED, event.getCmd(), event.getAgentKey()));
            return;
        }
        EslMessage message = answer(callInfo.getMedia(), agentInfo.getDeviceId());
        if (message != null) {
            logger.info("{}", message.getBodyLines());
        }

    }
}
