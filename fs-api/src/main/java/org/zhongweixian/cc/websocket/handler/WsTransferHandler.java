package org.zhongweixian.cc.websocket.handler;

import org.apache.commons.lang3.StringUtils;
import org.cti.cc.entity.RouteGetway;
import org.cti.cc.enums.Direction;
import org.cti.cc.enums.ErrorCode;
import org.cti.cc.enums.NextType;
import org.cti.cc.po.*;
import org.springframework.stereotype.Component;
import org.zhongweixian.cc.configration.HandlerType;
import org.zhongweixian.cc.websocket.event.WsTransferEvent;
import org.zhongweixian.cc.websocket.handler.base.WsBaseHandler;
import org.zhongweixian.cc.websocket.response.WsResponseEntity;

import java.time.Instant;

/**
 * Created by caoliang on 2020/11/6
 * <p>
 * 坐席发起转接  1:坐席,2:技能组,3:ivr,4:外线
 */
@Component
@HandlerType("WS_TRANSFER")
public class WsTransferHandler extends WsBaseHandler<WsTransferEvent> {

    @Override
    public void handleEvent(WsTransferEvent event) {
        AgentInfo agentInfo = getAgent(event);
        Long callId = agentInfo.getCallId();
        if (callId == null) {
            logger.warn("agent:{} transfer not found callId", event.getAgentKey());
            return;
        }
        if (!StringUtils.isNoneEmpty(event.getTransferValue()) || event.getTransferType() == 0) {
            return;
        }
        CallInfo callInfo = cacheService.getCallInfo(callId);

        logger.info("callId:{} transfer type:{} , value:{}", callId, event.getTransferType(), event.getTransferValue());
        /**
         * 1:坐席,2:技能组,3:ivr,4:外线
         */
        switch (event.getTransferType()) {
            case 1:
                transferAgent(callInfo, agentInfo, event);
                break;

            case 2:
                break;

            case 3:
                break;

            case 4:
                break;

            default:
                break;
        }
    }

    /**
     * 转接坐席
     *
     * @param callInfo
     * @param agentInfo
     * @param event
     */
    private void transferAgent(CallInfo callInfo, AgentInfo agentInfo, WsTransferEvent event) {
        if (!event.getTransferValue().contains(agentInfo.getAgentKey().split("@")[1])) {
            logger.warn("转接坐席:{}不是和当前坐席不是一个企业", event.getTransferValue());
            return;
        }
        AgentInfo transferAgent = cacheService.getAgentInfo(event.getTransferValue());
        if (transferAgent == null || transferAgent.getLogoutTime() > 0L) {
            logger.warn("转接坐席不在线:{}", event.getTransferValue());
            return;
        }
        //坐席不在READY、NOT_READY
        if (transferAgent.getAgentState() != AgentState.READY && transferAgent.getAgentState() != AgentState.NOT_READY) {
            sendMessgae(event, new WsResponseEntity<>(ErrorCode.AGENT_BUSY, AgentState.INNER_CALL.name(), event.getAgentKey()));
            return;
        }

        String deviceId = getDeviceId();
        DeviceInfo deviceInfo = new DeviceInfo();
        //如果是呼入，则caller,如果是外呼，则called
        deviceInfo.setCaller(callInfo.getDirection() == Direction.INBOUND ? callInfo.getCaller() : callInfo.getCalled());
        deviceInfo.setDisplay(agentInfo.getAgentId());
        deviceInfo.setCalled(transferAgent.getCalled());
        deviceInfo.setCallTime(Instant.now().toEpochMilli());
        deviceInfo.setCallId(agentInfo.getCallId());
        deviceInfo.setDeviceId(deviceId);
        deviceInfo.setCdrType(4);
        deviceInfo.setDeviceType(1);
        deviceInfo.setAgentKey(transferAgent.getAgentKey());
        //传入客户侧id
        deviceInfo.setNextCommand(new NextCommand(NextType.NEXT_TRANSFER_CALL, agentInfo.getDeviceId()));


        callInfo.getDeviceList().add(deviceId);
        callInfo.getDeviceInfoMap().put(deviceId, deviceInfo);
        cacheService.addCallInfo(callInfo);
        cacheService.addDevice(deviceId, callInfo.getCallId());

        RouteGetway routeGetway = cacheService.getRouteGetway(callInfo.getCompanyId(), transferAgent.getCalled());
        if (routeGetway == null) {
            logger.error("make call:{} origin route error", callInfo.getCallId());
            /**
             * 通知ws坐席请求外呼
             */
            sendMessgae(event, new WsResponseEntity<>(ErrorCode.CALL_ROUTE_ERROR, AgentState.OUT_CALL.name(), event.getAgentKey()));
            return;
        }
        logger.info("agent:{} transfer call to {}, callId:{}", event.getAgentKey(), event.getTransferValue(), callInfo.getCallId());
        fsListen.makeCall(routeGetway, agentInfo.getAgentId(), transferAgent.getCalled(), deviceId);

        /**
         * 通知ws坐席请求外呼
         */
        sendMessgae(event, new WsResponseEntity<>(AgentState.TRANSFER_CALL.name(), event.getAgentKey()));

        /**
         * 坐席请求外呼中
         */
        agentInfo.setBeforeState(agentInfo.getAgentState());
        agentInfo.setBeforeTime(agentInfo.getStateTime());
        agentInfo.setStateTime(Instant.now().toEpochMilli());
        agentInfo.setAgentState(AgentState.TRANSFER_CALL);
        agentInfo.setCallId(callInfo.getCallId());
        agentInfo.setDeviceId(deviceId);
        /**
         * 广播坐席状态
         */
        sendAgentStateMessage(agentInfo);
    }
}
