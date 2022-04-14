package org.zhongweixian.cc.websocket.handler;

import org.cti.cc.po.AgentInfo;
import org.cti.cc.po.CallInfo;
import org.springframework.stereotype.Component;
import org.zhongweixian.cc.configration.HandlerType;
import org.zhongweixian.cc.websocket.event.WsHoldCancelEvent;
import org.zhongweixian.cc.websocket.handler.base.WsBaseHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caoliang on 2021/7/16
 * <p>
 * 取消保持
 */
@Component
@HandlerType("WS_HOLD_CANCEL")
public class WsHoldCancelHandler extends WsBaseHandler<WsHoldCancelEvent> {

    @Override
    public void handleEvent(WsHoldCancelEvent event) {
        AgentInfo agentInfo = getAgent(event);
        if (agentInfo == null || agentInfo.getCallId() == null) {

            return;
        }
        String deviceId = agentInfo.getDeviceId();
        CallInfo callInfo = cacheService.getCallInfo(agentInfo.getCallId());
        if (deviceId == null || callInfo == null) {
            return;
        }
        List<String> list = new ArrayList<>(callInfo.getDeviceList());
        list.remove(agentInfo.getDeviceId());
        bridgeCall(callInfo.getMediaHost(), callInfo.getCallId(), agentInfo.getDeviceId(), list.get(0));
    }
}
