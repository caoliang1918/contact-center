package org.zhongweixian.cc.websocket.handler;

import org.cti.cc.po.AgentInfo;
import org.cti.cc.po.AgentState;
import org.cti.cc.po.CallInfo;
import org.springframework.stereotype.Component;
import org.zhongweixian.cc.configration.HandlerType;
import org.zhongweixian.cc.websocket.event.WsHoldEvent;
import org.zhongweixian.cc.websocket.handler.base.WsBaseHandler;
import org.zhongweixian.cc.websocket.response.WsResponseEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caoliang on 2021/7/19
 * <p>
 * 呼叫保持:坐席听不到用户声音，用户听到的是保持音
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
        List<String> list = new ArrayList<>(callInfo.getDeviceList());
        list.remove(deviceId);
        this.bridgeBreak(callInfo.getMediaHost(), callInfo.getCallId(), list.get(0));
        this.holdPlay(callInfo.getMediaHost(), callInfo.getCallId(), list.get(0), "/app/clpms/sounds/hold.wav");
        agentInfo.setAgentState(AgentState.HOLD);
        sendMessage(event, new WsResponseEntity<>(event.getCmd(), event.getAgentKey()));
        callInfo.getDeviceInfoMap().get(deviceId).setState(AgentState.HOLD.name());
        cacheService.addCallInfo(callInfo);
        cacheService.addAgentInfo(agentInfo);
    }

}
