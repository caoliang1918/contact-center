package org.zhongweixian.cc.websocket.handler;

import org.cti.cc.po.AgentInfo;
import org.cti.cc.po.AgentState;
import org.cti.cc.po.CallInfo;
import org.cti.cc.po.DeviceInfo;
import org.springframework.stereotype.Component;
import org.zhongweixian.cc.configration.HandlerType;
import org.zhongweixian.cc.websocket.event.WsInsertEvent;
import org.zhongweixian.cc.websocket.handler.base.WsBaseHandler;
import org.zhongweixian.cc.websocket.response.WsResponseEntity;

/**
 * Created by caoliang on 2021/11/19
 * 班长强插
 */
@Component
@HandlerType("WS_HOLD")
public class WsInsertHandler extends WsBaseHandler<WsInsertEvent> {
    @Override
    public void handleEvent(WsInsertEvent event) {
        AgentInfo agentInfo = getAgent(event);
        if (agentInfo == null || agentInfo.getCallId() == null) {
            return;
        }
        CallInfo callInfo = cacheService.getCallInfo(event.getCallId());
        String deviceId = event.getDeviceId();


        DeviceInfo deviceInfo = DeviceInfo.DeviceInfoBuilder.builder()
                .withDeviceId(getDeviceId())
                .withAgentKey(agentInfo.getAgentKey())
                .withDeviceType(1)
                .withCdrType(7)
                .withCallId(event.getCallId())
                .withCalled(agentInfo.getSipPhone())
                .build();

        this.hold(callInfo.getMedia(), callInfo.getCallId(), deviceId);
        //
//        callInfo.getNextCommands().add();

        agentInfo.setAgentState(AgentState.INSERT);
        sendMessgae(event, new WsResponseEntity<>(event.getCmd(), event.getAgentKey()));
        callInfo.getDeviceInfoMap().get(deviceId).setState(AgentState.INSERT.name());

    }
}
