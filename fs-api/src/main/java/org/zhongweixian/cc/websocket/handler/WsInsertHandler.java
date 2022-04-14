package org.zhongweixian.cc.websocket.handler;

import org.cti.cc.entity.RouteGetway;
import org.cti.cc.enums.ErrorCode;
import org.cti.cc.enums.NextType;
import org.cti.cc.po.*;
import org.springframework.stereotype.Component;
import org.zhongweixian.cc.configration.HandlerType;
import org.zhongweixian.cc.websocket.event.WsInsertEvent;
import org.zhongweixian.cc.websocket.handler.base.WsBaseHandler;
import org.zhongweixian.cc.websocket.response.WsResponseEntity;

import java.time.Instant;

/**
 * Created by caoliang on 2021/11/19
 * <p>
 * 班长强插 (班长显示强插通话，坐席不显示)
 */
@Component
@HandlerType("WS_INSERT")
public class WsInsertHandler extends WsBaseHandler<WsInsertEvent> {
    @Override
    public void handleEvent(WsInsertEvent event) {
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


        DeviceInfo deviceInfo = DeviceInfo.DeviceInfoBuilder.builder().withDeviceId(getDeviceId()).withAgentKey(agentInfo.getAgentKey()).withDeviceType(1).withCdrType(7).withCallId(callInfo.getCallId()).withCallTime(Instant.now().toEpochMilli()).withCalled(agentInfo.getSipPhone()).withCaller(agentInfo.getAgentCode()).withDisplay(agentInfo.getAgentCode()).build();


        String caller = agentInfo.getCalled();

        RouteGetway routeGetway = cacheService.getRouteGetway(callInfo.getCompanyId(), caller);
        if (routeGetway == null) {
            logger.error("agent:{} make call:{} origin route error", agentInfo.getAgentKey(), callInfo.getCallId());
            agentInfo.setBeforeTime(agentInfo.getStateTime());
            agentInfo.setBeforeState(agentInfo.getAgentState());
            agentInfo.setStateTime(Instant.now().toEpochMilli());
            agentInfo.setAgentState(AgentState.AFTER);
            syncAgentStateMessage(agentInfo);
            agentInfo.setCallId(null);

            /**
             * 通知ws坐席请求外呼
             */
            sendMessage(event, new WsResponseEntity<>(ErrorCode.CALL_ROUTE_ERROR, AgentState.OUT_CALL.name(), event.getAgentKey()));
            return;
        }

        logger.info("agent:{} makecall, callId:{}, caller:{} called:{}", event.getAgentKey(), callInfo.getCallId(), agentInfo.getAgentId(), caller);
        fsListen.makeCall(callInfo.getMediaHost(), routeGetway, agentInfo.getAgentId(), caller, callInfo.getCallId(), deviceInfo.getDeviceId());


        deviceInfo.setState(AgentState.INSERT.name());
        agentInfo.setAgentState(AgentState.INSERT);
        agentInfo.setCallId(callInfo.getCallId());
        agentInfo.setDeviceId(deviceInfo.getDeviceId());
        sendMessage(event, new WsResponseEntity<>(event.getCmd(), event.getAgentKey()));


        callInfo.getNextCommands().add(new NextCommand(deviceId, NextType.NEXT_INSERT_CALL, deviceInfo.getDeviceId()));
        callInfo.getDeviceList().add(deviceInfo.getDeviceId());
        callInfo.getDeviceInfoMap().put(deviceInfo.getDeviceId(), deviceInfo);

        cacheService.addCallInfo(callInfo);
        cacheService.addDevice(deviceInfo.getDeviceId(), callInfo.getCallId());
        cacheService.addAgentInfo(agentInfo);

    }
}
