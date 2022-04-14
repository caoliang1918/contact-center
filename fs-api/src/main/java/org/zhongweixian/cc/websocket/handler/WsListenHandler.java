package org.zhongweixian.cc.websocket.handler;

import org.cti.cc.entity.RouteGetway;
import org.cti.cc.enums.ErrorCode;
import org.cti.cc.enums.NextType;
import org.cti.cc.po.*;
import org.springframework.stereotype.Component;
import org.zhongweixian.cc.configration.HandlerType;
import org.zhongweixian.cc.websocket.event.WsListenEvent;
import org.zhongweixian.cc.websocket.handler.base.WsBaseHandler;
import org.zhongweixian.cc.websocket.response.WsResponseEntity;

import java.time.Instant;

/**
 * Created by caoliang on 2022/3/3
 * <p>
 * 监听：班长可以听到别人的声音，别人听不到班长的声音
 * <p>
 * 1、eavesdrop_bridge_aleg=true
 * 2、eavesdrop_bridge_bleg=true
 * 3、eavesdrop
 */
@Component
@HandlerType("WS_LISTEN")
public class WsListenHandler extends WsBaseHandler<WsListenEvent> {

    @Override
    public void handleEvent(WsListenEvent event) {
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

        DeviceInfo deviceInfo = DeviceInfo.DeviceInfoBuilder.builder().withDeviceId(getDeviceId()).withAgentKey(agentInfo.getAgentKey()).withDeviceType(1).withCdrType(6).withCallId(callInfo.getCallId()).withCallTime(Instant.now().toEpochMilli()).withCalled(agentInfo.getSipPhone()).withCaller(agentInfo.getAgentCode()).withDisplay(agentInfo.getAgentCode()).build();

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

        deviceInfo.setState(AgentState.LISTEN.name());
        agentInfo.setAgentState(AgentState.LISTEN);
        agentInfo.setCallId(callInfo.getCallId());
        agentInfo.setDeviceId(deviceInfo.getDeviceId());
        syncAgentStateMessage(agentInfo);

        callInfo.getNextCommands().add(new NextCommand(deviceId, NextType.NEXT_LISTEN_CALL, deviceInfo.getDeviceId()));
        callInfo.getDeviceList().add(deviceInfo.getDeviceId());
        callInfo.getDeviceInfoMap().put(deviceInfo.getDeviceId(), deviceInfo);

        cacheService.addCallInfo(callInfo);
        cacheService.addDevice(deviceInfo.getDeviceId(), callInfo.getCallId());
        cacheService.addAgentInfo(agentInfo);
    }
}
