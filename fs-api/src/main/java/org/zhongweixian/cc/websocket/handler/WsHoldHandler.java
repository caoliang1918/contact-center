package org.zhongweixian.cc.websocket.handler;

import org.cti.cc.po.AgentInfo;
import org.cti.cc.po.CallInfo;
import org.springframework.stereotype.Component;
import org.zhongweixian.cc.configration.HandlerType;
import org.zhongweixian.cc.websocket.event.WsHoldEvent;
import org.zhongweixian.cc.websocket.handler.base.WsBaseHandler;

/**
 * Created by caoliang on 2021/7/19
 * <p>
 * 呼叫保持
 */
@Component
@HandlerType("WS_HOLD")
public class WsHoldHandler extends WsBaseHandler<WsHoldEvent> {
    @Override
    public void handleEvent(WsHoldEvent event) {
        AgentInfo agentInfo = getAgent(event);
        if (agentInfo == null || agentInfo.getCallId() == null) {

            return;
        }
        String deviceId = agentInfo.getDeviceId();
        CallInfo callInfo = cacheService.getCallInfo(agentInfo.getCallId());
        if (deviceId == null || callInfo == null) {

            return;
        }
        this.hold(callInfo.getMedia(), callInfo.getCallId(), deviceId);
    }

}
