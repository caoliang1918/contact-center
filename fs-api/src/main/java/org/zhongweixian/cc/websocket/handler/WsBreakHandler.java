package org.zhongweixian.cc.websocket.handler;

import org.cti.cc.enums.ErrorCode;
import org.cti.cc.po.AgentInfo;
import org.cti.cc.po.CallInfo;
import org.springframework.stereotype.Component;
import org.zhongweixian.cc.configration.HandlerType;
import org.zhongweixian.cc.websocket.event.WsBreakEvent;
import org.zhongweixian.cc.websocket.handler.base.WsBaseHandler;
import org.zhongweixian.cc.websocket.response.WsResponseEntity;

import java.time.Instant;

/**
 * Created by caoliang on 2022/3/3
 * 强插：所有设备挂机
 */
@Component
@HandlerType("WS_BREAK")
public class WsBreakHandler extends WsBaseHandler<WsBreakEvent> {

    @Override
    public void handleEvent(WsBreakEvent event) {
        AgentInfo agentInfo = getAgent(event);
        if (agentInfo.getAgentType() != 2) {
            return;
        }
        AgentInfo monitorAgent = cacheService.getAgentInfo(event.getMonitorAgent());
        if (monitorAgent == null || monitorAgent.getCallId() == null) {
            sendMessage(event, new WsResponseEntity<String>(ErrorCode.CALL_NOT_EXIST, event.getCmd(), event.getAgentKey()));
            return;
        }

        CallInfo callInfo = cacheService.getCallInfo(monitorAgent.getCallId());
        String deviceId = monitorAgent.getDeviceId();

        if (callInfo == null || !callInfo.getDeviceList().contains(deviceId)) {
            return;
        }

        Long time = Instant.now().toEpochMilli();
        callInfo.setHangupDir(3);
        callInfo.getDeviceInfoMap().forEach((k, v) -> {
            if (v.getEndTime() == null) {
                v.setEndTime(time);
                hangupCall(callInfo.getMediaHost(), callInfo.getCallId(), k);
            }
        });
        cacheService.addCallInfo(callInfo);
    }
}
