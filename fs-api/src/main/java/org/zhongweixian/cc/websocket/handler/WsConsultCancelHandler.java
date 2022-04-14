package org.zhongweixian.cc.websocket.handler;

import org.apache.commons.lang3.StringUtils;
import org.cti.cc.enums.ErrorCode;
import org.cti.cc.po.AgentInfo;
import org.cti.cc.po.AgentState;
import org.cti.cc.po.CallInfo;
import org.cti.cc.po.DeviceInfo;
import org.springframework.stereotype.Component;
import org.zhongweixian.cc.configration.HandlerType;
import org.zhongweixian.cc.websocket.event.WsConsultCancelEvent;
import org.zhongweixian.cc.websocket.handler.base.WsBaseHandler;
import org.zhongweixian.cc.websocket.response.WsCallEntity;
import org.zhongweixian.cc.websocket.response.WsResponseEntity;

/**
 * Created by caoliang on 2022/2/8.
 * <p>
 * 取消咨询
 */
@Component
@HandlerType("WS_CONSULT_CANCEL")
public class WsConsultCancelHandler extends WsBaseHandler<WsConsultCancelEvent> {


    @Override
    public void handleEvent(WsConsultCancelEvent event) {
        AgentInfo agentInfo = getAgent(event);
        CallInfo callInfo = cacheService.getCallInfo(agentInfo.getCallId());
        if (agentInfo.getCallId() == null || callInfo == null || StringUtils.isBlank(agentInfo.getConsultDeviceId())) {
            sendMessage(event, new WsResponseEntity<String>(ErrorCode.CALL_NOT_EXIST, event.getCmd(), event.getAgentKey()));
            return;
        }


        //如果已经应答，则不能走取消咨询
        DeviceInfo deviceInfo = callInfo.getDeviceInfoMap().get(agentInfo.getConsultDeviceId());
        if (deviceInfo == null || deviceInfo.getAnswerTime() != null) {
            sendMessage(event, new WsResponseEntity<String>(ErrorCode.CALL_NOT_EXIST, event.getCmd(), event.getAgentKey()));
            return;
        }

        //被咨询设备直接挂机
        hangupCall(callInfo.getMediaHost(), callInfo.getCallId(), agentInfo.getConsultDeviceId());

        WsCallEntity callEntity = new WsCallEntity();
        callEntity.setCallId(callInfo.getCallId());
        callEntity.setCallType(callInfo.getCallType());
        callEntity.setAgentState(agentInfo.getAgentState());
        callEntity.setCaller(callInfo.getCaller());
        callEntity.setCalled(callInfo.getCalled());
        callEntity.setDirection(callInfo.getDirection());
        sendMessage(event, new WsResponseEntity<WsCallEntity>(AgentState.TALKING.name(), agentInfo.getAgentKey(), callEntity));
    }
}
