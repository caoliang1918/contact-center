package org.zhongweixian.cc.websocket.handler;

import org.apache.commons.lang3.StringUtils;
import org.cti.cc.entity.RouteGetway;
import org.cti.cc.enums.ErrorCode;
import org.cti.cc.enums.NextType;
import org.cti.cc.po.*;
import org.springframework.stereotype.Component;
import org.zhongweixian.cc.configration.HandlerType;
import org.zhongweixian.cc.util.RandomUtil;
import org.zhongweixian.cc.websocket.event.WsConsultEvent;
import org.zhongweixian.cc.websocket.handler.base.WsBaseHandler;
import org.zhongweixian.cc.websocket.response.WsConsultResponse;
import org.zhongweixian.cc.websocket.response.WsResponseEntity;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by caoliang on 2022/2/8.
 * <p>
 * 咨询
 */
@Component
@HandlerType("WS_CONSULT")
public class WsConsultHandler extends WsBaseHandler<WsConsultEvent> {
    @Override
    public void handleEvent(WsConsultEvent event) {
        AgentInfo agentInfo = cacheService.getAgentInfo(event.getAgentKey());
        CallInfo callInfo = cacheService.getCallInfo(agentInfo.getCallId());

        if (agentInfo.getCallId() == null || callInfo == null) {
            sendMessage(event, new WsResponseEntity<String>(ErrorCode.CALL_NOT_EXIST, event.getCmd(), event.getAgentKey()));
            return;
        }

        /**
         * 咨询类型  1:技能组, 2:坐席 3:ivr ,4:外线
         */
        switch (event.getType()) {
            case 1:
                //咨询技能组，随机找一个坐席

                break;
            case 2:
                AgentInfo otherAgent = cacheService.getAgentInfo(event.getConsultValue());
                logger.info("callId:{}, consult agent:{}", callInfo.getCallId(), otherAgent.getAgentKey());
                if (otherAgent == null || otherAgent.getLogoutTime() > 0) {
                    //坐席不在线
                    sendMessage(event, new WsResponseEntity<String>(ErrorCode.AGENT_NOT_ONLINE, event.getCmd(), event.getAgentKey()));
                    return;
                }

                if (otherAgent.getAgentState().name().contains("CALL")) {
                    //坐席处于通话中
                    sendMessage(event, new WsResponseEntity<String>(ErrorCode.AGENT_CALLING, event.getCmd(), event.getAgentKey()));
                    return;
                }
                //咨询坐席
                consultAgent(callInfo, agentInfo, otherAgent, event);
                break;

            case 3:

                break;

            case 4:
                //咨询外线号码
                consultOutCall(agentInfo, callInfo, event.getConsultValue(), event);
                break;
        }
    }

    /**
     * 咨询坐席
     *
     * @param callInfo
     * @param agentInfo
     * @param otherAgent
     * @param event
     */
    private void consultAgent(CallInfo callInfo, AgentInfo agentInfo, AgentInfo otherAgent, WsConsultEvent event) {
        List<String> list = new ArrayList<>(callInfo.getDeviceList());
        list.remove(agentInfo.getDeviceId());


        String called = otherAgent.getCalled();
        /**
         *  1、先呼通另外一个坐席
         *  2、桥接坐席
         *  3、桥接成功之后客户保持
         */
        DeviceInfo deviceInfo = DeviceInfo.DeviceInfoBuilder.builder().withCallId(callInfo.getCallId()).withCalled(called).withCdrType(5).withDeviceType(1).withAgentKey(otherAgent.getAgentKey()).withDisplay(agentInfo.getAgentId()).withCallTime(Instant.now().toEpochMilli()).build();

        String deviceId = getDeviceId();
        deviceInfo.setDeviceId(deviceId);
        callInfo.getDeviceList().add(deviceId);
        callInfo.getDeviceInfoMap().put(deviceId, deviceInfo);


        RouteGetway routeGetway = cacheService.getRouteGetway(callInfo.getCompanyId(), called);
        if (routeGetway == null) {
            logger.error("agent:{} make call:{} origin route error", agentInfo.getAgentKey(), callInfo.getCallId());
            /**
             * 通知ws坐席咨询失败
             */
            sendMessage(event, new WsResponseEntity<>(ErrorCode.CONSULT_ERROR, AgentState.OUT_CALL.name(), event.getAgentKey()));
            return;
        }


        logger.info("agent:{} makecall, callId:{}, caller:{} called:{}", event.getAgentKey(), callInfo.getCallId(), agentInfo.getAgentId(), called);
        fsListen.makeCall(routeGetway, agentInfo.getAgentId(), called, callInfo.getCallId(), deviceId, null);
        callInfo.getNextCommands().add(new NextCommand(agentInfo.getDeviceId(), NextType.NEXT_CONSULT_AGENT, list.get(0)));


        cacheService.addCallInfo(callInfo);
        cacheService.addDevice(deviceId, callInfo.getCallId());

        otherAgent.setBeforeState(otherAgent.getAgentState());
        otherAgent.setBeforeTime(otherAgent.getStateTime());
        otherAgent.setAgentState(AgentState.CONSULT_CALL_RING);
        otherAgent.setStateTime(Instant.now().getEpochSecond());

        otherAgent.setCallId(callInfo.getCallId());
        otherAgent.setDeviceId(deviceId);
        cacheService.addAgentInfo(otherAgent);

        //添加被咨询坐席
        agentInfo.setConsultDeviceId(otherAgent.getDeviceId());
        cacheService.addAgentInfo(agentInfo);


        WsConsultResponse response = new WsConsultResponse();
        response.setConsultAgent(event.getConsultValue());
        response.setCallId(callInfo.getCallId());
        sendMessage(event, new WsResponseEntity<WsConsultResponse>(event.getCmd(), event.getAgentKey(), response));
    }

    /**
     * 咨询外线号码
     *
     * @param agentInfo
     * @param callInfo
     * @param called
     * @param event
     */
    private void consultOutCall(AgentInfo agentInfo, CallInfo callInfo, String called, WsConsultEvent event) {
        List<String> list = new ArrayList<>(callInfo.getDeviceList());
        list.remove(agentInfo.getDeviceId());

        String deviceId = getDeviceId();
        DeviceInfo deviceInfo = DeviceInfo.DeviceInfoBuilder.builder().withCallId(callInfo.getCallId()).withCalled(called).withAgentKey(StringUtils.EMPTY).withCallTime(Instant.now().toEpochMilli()).withCdrType(5).withDeviceId(deviceId).withDeviceType(3).build();

        RouteGetway routeGetway = cacheService.getRouteGetway(callInfo.getCompanyId(), called);
        if (routeGetway == null) {
            logger.warn("callId:{} origin error, called:{}", callInfo.getCallId(), callInfo.getCalled());
            hangupCall(callInfo.getMediaHost(), callInfo.getCallId(), deviceInfo.getDeviceId());
            return;
        }

        //技能组外呼显号
        GroupInfo groupInfo = cacheService.getGroupInfo(agentInfo.getGroupId());
        String calledDisplay = RandomUtil.getRandom(groupInfo.getCalledDisplays());
        callInfo.getDeviceList().add(deviceId);
        callInfo.getDeviceInfoMap().put(deviceId, deviceInfo);
        callInfo.getNextCommands().add(new NextCommand(agentInfo.getDeviceId(), NextType.NEXT_CONSULT_CALLOUT, list.get(0)));
        cacheService.addCallInfo(callInfo);
        cacheService.addDevice(deviceId, callInfo.getCallId());


        //记录被咨询设备
        agentInfo.setConsultDeviceId(deviceId);
        cacheService.addAgentInfo(agentInfo);

        fsListen.makeCall(callInfo.getMediaHost(), routeGetway, calledDisplay, called, callInfo.getCallId(), deviceId, null);
    }

}
