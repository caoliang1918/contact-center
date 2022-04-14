package org.zhongweixian.cc.websocket.handler;

import org.apache.commons.lang3.StringUtils;
import org.cti.cc.enums.ErrorCode;
import org.cti.cc.po.AgentInfo;
import org.cti.cc.po.AgentState;
import org.cti.cc.po.CallInfo;
import org.cti.cc.po.DeviceInfo;
import org.springframework.stereotype.Component;
import org.zhongweixian.cc.configration.HandlerType;
import org.zhongweixian.cc.websocket.event.WsConsultStopEvent;
import org.zhongweixian.cc.websocket.handler.base.WsBaseHandler;
import org.zhongweixian.cc.websocket.response.WsResponseEntity;

import java.util.Map;

/**
 * Created by caoliang on 2022/2/8.
 * <p>
 * 结束咨询
 */
@Component
@HandlerType("WS_CONSULT_STOP")
public class WsConsultStopHandler extends WsBaseHandler<WsConsultStopEvent> {
    @Override
    public void handleEvent(WsConsultStopEvent event) {
        AgentInfo agentInfo = getAgent(event);
        CallInfo callInfo = cacheService.getCallInfo(agentInfo.getCallId());
        if (agentInfo.getCallId() == null || callInfo == null || StringUtils.isBlank(agentInfo.getConsultDeviceId())) {
            sendMessage(event, new WsResponseEntity<String>(ErrorCode.CALL_NOT_EXIST, event.getCmd(), event.getAgentKey()));
            return;
        }
        /**
         * 结束咨询:
         * 1、先把hold设备break
         * 2、桥接设备
         * 3、被咨询挂机
         */

        String holdDeviceId = null;

        for (Map.Entry<String, DeviceInfo> entry : callInfo.getDeviceInfoMap().entrySet()) {
            if (AgentState.HOLD.name().equals(entry.getValue().getState()) && entry.getValue().getEndTime() == null) {
                playBreak(callInfo.getMediaHost(), callInfo.getCallId(), entry.getKey());
                holdDeviceId = entry.getKey();
            }
        }

        bridgeCall(callInfo.getMediaHost(), callInfo.getCallId(), agentInfo.getDeviceId(), holdDeviceId);
        hangupCall(callInfo.getMediaHost(), callInfo.getCallId(), agentInfo.getConsultDeviceId());

        agentInfo.setConsultDeviceId(null);
        cacheService.addAgentInfo(agentInfo);
    }
}
