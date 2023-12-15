package org.voice9.cc.websocket.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.voice9.core.po.*;
import org.apache.commons.lang3.StringUtils;
import com.voice9.core.entity.RouteGetway;
import com.voice9.core.enums.CallType;
import com.voice9.core.enums.Direction;
import com.voice9.core.enums.ErrorCode;
import com.voice9.core.enums.NextType;
import org.springframework.stereotype.Component;
import org.voice9.cc.configration.HandlerType;
import org.voice9.cc.util.RandomUtil;
import org.voice9.cc.websocket.handler.base.WsBaseHandler;
import org.voice9.cc.websocket.response.WsCallEntity;
import org.voice9.cc.websocket.response.WsResponseEntity;
import org.voice9.cc.websocket.event.WsMakeCallEvent;

import java.time.Instant;

/**
 * Created by caoliang on 2020/10/30
 * <p>
 * 坐席发起外呼
 */
@Component
@HandlerType("WS_MAKE_CALL")
public class WsMakeCallHandler extends WsBaseHandler<WsMakeCallEvent> {

    @Override
    public void handleEvent(WsMakeCallEvent event) {
        if (StringUtils.isBlank(event.getCalled())) {
            sendMessage(event, new WsResponseEntity<>(ErrorCode.CALL_NUMBER_ERROR, AgentState.OUT_CALL.name(), event.getAgentKey()));
            return;
        }
        /**
         * 呼叫类型没有匹配上
         */
        if (event.getCallType() == null) {
            logger.warn("agent:{} callType error", event.getAgentKey());
            sendMessage(event, new WsResponseEntity<>(ErrorCode.CALLTYPE_ERROR, AgentState.OUT_CALL.name(), event.getAgentKey()));
            event.getChannel().close();
            return;
        }
        if (event.getFollowData() != null) {
            if (JSONObject.toJSONString(event.getFollowData()).length() > 2048) {
                sendMessage(event, new WsResponseEntity<>(ErrorCode.CALLTYPE_ERROR, AgentState.OUT_CALL.name(), event.getAgentKey()));
                return;
            }
        }
        AgentInfo agentInfo = getAgent(event);
        if (agentInfo.getAgentState().name().contains("CALL") || agentInfo.getAgentState() == AgentState.TALKING) {
            sendMessage(event, new WsResponseEntity<>(ErrorCode.AGENT_CALLING, event.getCmd(), event.getAgentKey()));
            return;
        }

        /**
         * 1 sip
         * 2 webrtc
         * 3 phone
         */
        Integer loginType = agentInfo.getLoginType();
        String caller = null;
        String callerDisplay = null;
        String calledDisplay = null;
        switch (loginType) {
            case 1:
            case 2:
                caller = agentInfo.getSips().get(0);
                break;
            case 3:
                caller = agentInfo.getSipPhone();
                break;
            default:
                break;
        }
        callerDisplay = agentInfo.getAgentId();

        /**
         * 坐席所在的主技能组
         */
        GroupInfo groupInfo = getGroup(agentInfo.getGroupId());
        if (groupInfo == null || groupInfo.getStatus() == 0) {
            sendMessage(event, new WsResponseEntity<>(ErrorCode.AGENT_GROUP_NULL, AgentState.OUT_CALL.name(), event.getAgentKey()));
            return;
        }

        /**
         * 被叫显号
         */
        calledDisplay = getCalledDisplay(event, groupInfo, agentInfo);

        Long callId = snowflakeIdWorker.nextId();
        Long now = Instant.now().toEpochMilli();
        CallInfo callInfo = CallInfo.CallInfoBuilder.builder().withCallId(callId).withAgentKey(agentInfo.getAgentKey()).withAgentName(agentInfo.getAgentName()).withLoginType(agentInfo.getLoginType()).withCompanyId(agentInfo.getCompanyId()).withGroupId(agentInfo.getGroupId()).withCaller(caller).withCalled(event.getCalled()).withCallerDisplay(callerDisplay).withCalledDisplay(calledDisplay).withDirection(Direction.OUTBOUND).withCallType(event.getCallType()).withCallTime(now).withCtiHost(agentInfo.getHost()).withFollowData(event.getFollowData()).build();

        switch (event.getCallType()) {
            case INNER_CALL:
                AgentInfo calledAgent = cacheService.getAgentInfo(event.getCalled());
                if (calledAgent == null || calledAgent.getLogoutTime() > 0L) {
                    sendMessage(event, new WsResponseEntity<>(ErrorCode.AGENT_NOT_ONLINE, AgentState.INNER_CALL.name(), event.getAgentKey()));
                    return;
                }
                //坐席不在READY、NOT_READY
                if (calledAgent.getAgentState() != AgentState.READY && calledAgent.getAgentState() != AgentState.NOT_READY) {
                    sendMessage(event, new WsResponseEntity<>(ErrorCode.AGENT_BUSY, AgentState.INNER_CALL.name(), event.getAgentKey()));
                    return;
                }
                //内呼
                innerCall(agentInfo, callInfo, callerDisplay, caller, event);
                break;
            case OUTBOUNT_CALL:
                //外呼
                outboundCall(agentInfo, callInfo, callerDisplay, caller, event);
                break;
            default:
                break;
        }
    }


    /**
     * 获取被叫显号
     *
     * @param groupInfo
     * @param agentInfo
     * @return
     */
    private String getCalledDisplay(WsMakeCallEvent event, GroupInfo groupInfo, AgentInfo agentInfo) {
        String calledDisplay = null;
        /**
         * sdk传过来优先使用sdk的显号
         */
        if (StringUtils.isNoneBlank(event.getDisplay())) {
            return event.getDisplay();
        }
        if (!StringUtils.isBlank(agentInfo.getDisplay())) {
            return agentInfo.getDisplay();
        }
        return RandomUtil.getRandom(groupInfo.getCalledDisplays());
    }

    /**
     * 坐席内呼
     *
     * @param agentInfo
     * @param callInfo
     * @param callerDisplay
     * @param caller
     * @param event
     */
    private void innerCall(AgentInfo agentInfo, CallInfo callInfo, String callerDisplay, String caller, WsMakeCallEvent event) {
        callInfo.setCallType(CallType.INNER_CALL);
        String deviceId = getDeviceId();
        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.setCaller(agentInfo.getAgentId());
        deviceInfo.setDisplay(callerDisplay);
        deviceInfo.setCalled(caller);
        deviceInfo.setCallTime(callInfo.getCallTime());
        deviceInfo.setCallId(callInfo.getCallId());
        deviceInfo.setDeviceId(deviceId);
        deviceInfo.setDeviceType(1);
        deviceInfo.setCdrType(3);
        deviceInfo.setAgentKey(agentInfo.getAgentKey());
        deviceInfo.setAgentName(agentInfo.getAgentName());
        callInfo.getNextCommands().add(new NextCommand(deviceId, NextType.NEXT_CALL_OTHER, null));

        CompanyInfo companyInfo = cacheService.getCompany(agentInfo.getCompanyId());
        callInfo.setHiddenCustomer(companyInfo.getHiddenCustomer());
        callInfo.setCdrNotifyUrl(companyInfo.getNotifyUrl());

        callInfo.getDeviceList().add(deviceId);
        callInfo.getDeviceInfoMap().put(deviceId, deviceInfo);
        cacheService.addCallInfo(callInfo);
        cacheService.addDevice(deviceId, callInfo.getCallId());

        RouteGetway routeGetway = cacheService.getRouteGetway(callInfo.getCompanyId(), caller);
        if (routeGetway == null) {
            logger.error("make call:{} origin route error", callInfo.getCallId());
            agentInfo.setBeforeTime(agentInfo.getStateTime());
            agentInfo.setBeforeState(agentInfo.getAgentState());
            agentInfo.setStateTime(Instant.now().getEpochSecond());
            agentInfo.setAgentState(AgentState.AFTER);
            syncAgentStateMessage(agentInfo);
            agentInfo.setCallId(null);

            /**
             * 通知ws坐席请求外呼
             */
            sendMessage(event, new WsResponseEntity<>(ErrorCode.CALL_ROUTE_ERROR, AgentState.INNER_CALL.name(), event.getAgentKey()));
            return;
        }
        logger.info("agent:{} makecall, callId:{}, caller:{} called:{}", event.getAgentKey(), callInfo.getCallId(), callerDisplay, caller);
        fsListen.makeCall(routeGetway, callerDisplay, caller, callInfo.getCallId(), deviceId, null, null);


        /**
         * 通知ws坐席请求外呼
         */
        sendMessage(event, new WsResponseEntity<>(AgentState.INNER_CALL.name(), event.getAgentKey()));


        /**
         * 坐席请求外呼中
         */
        agentInfo.setBeforeState(agentInfo.getAgentState());
        agentInfo.setBeforeTime(agentInfo.getStateTime());
        agentInfo.setStateTime(Instant.now().getEpochSecond());
        agentInfo.setAgentState(AgentState.INNER_CALL);
        agentInfo.setCallId(callInfo.getCallId());
        agentInfo.setDeviceId(deviceId);
        /**
         * 广播坐席状态
         */
        syncAgentStateMessage(agentInfo);
    }


    /**
     * 坐席外呼
     *
     * @param agentInfo
     * @param callInfo
     * @param callerDisplay
     * @param caller
     * @param event
     */
    private void outboundCall(AgentInfo agentInfo, CallInfo callInfo, String callerDisplay, String caller, WsMakeCallEvent event) {
        String deviceId = getDeviceId();
        callInfo.setCallType(CallType.OUTBOUNT_CALL);
        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.setCaller(agentInfo.getAgentId());
        deviceInfo.setDisplay(callerDisplay);
        deviceInfo.setCalled(caller);
        deviceInfo.setCallTime(callInfo.getCallTime());
        deviceInfo.setCallId(callInfo.getCallId());
        deviceInfo.setDeviceId(deviceId);
        deviceInfo.setCdrType(2);
        deviceInfo.setDeviceType(1);
        deviceInfo.setAgentKey(agentInfo.getAgentKey());
        deviceInfo.setAgentName(agentInfo.getAgentName());

        if (StringUtils.isNoneBlank(event.getUuid1())) {
            callInfo.setUuid1(event.getUuid1());
        }
        if (StringUtils.isNoneBlank(event.getUuid2())) {
            callInfo.setUuid2(event.getUuid2());
        }
        callInfo.getDeviceList().add(deviceId);
        callInfo.getDeviceInfoMap().put(deviceId, deviceInfo);
        callInfo.setHiddenCustomer(agentInfo.getHiddenCustomer());
        callInfo.setAgentName(agentInfo.getAgentName());

        //被叫号码归属地
        callInfo.setNumberLocation("");

        //话单推送地址
        CompanyInfo companyInfo = cacheService.getCompany(agentInfo.getCompanyId());
        callInfo.setHiddenCustomer(companyInfo.getHiddenCustomer());
        callInfo.setCdrNotifyUrl(companyInfo.getNotifyUrl());

        callInfo.getNextCommands().add(new NextCommand(deviceId, NextType.NEXT_CALL_OTHER, null));
        cacheService.addCallInfo(callInfo);
        cacheService.addDevice(deviceId, callInfo.getCallId());

        RouteGetway routeGetway = cacheService.getRouteGetway(callInfo.getCompanyId(), caller);
        if (routeGetway == null) {
            logger.error("agent:{} make call:{} origin route error", agentInfo.getAgentKey(), callInfo.getCallId());
            agentInfo.setBeforeTime(agentInfo.getStateTime());
            agentInfo.setBeforeState(agentInfo.getAgentState());
            agentInfo.setStateTime(Instant.now().getEpochSecond());
            agentInfo.setAgentState(AgentState.AFTER);
            syncAgentStateMessage(agentInfo);
            agentInfo.setCallId(null);

            /**
             * 通知ws坐席请求外呼
             */
            sendMessage(event, new WsResponseEntity<>(ErrorCode.CALL_ROUTE_ERROR, AgentState.OUT_CALL.name(), event.getAgentKey()));
            return;
        }
        logger.info("agent:{} makecall, callId:{}, caller:{} called:{}", event.getAgentKey(), callInfo.getCallId(), callInfo.getCaller(), hiddenNumber(callInfo.getCalled()));
        fsListen.makeCall(event.getMedia(), routeGetway, callerDisplay, caller, callInfo.getCallId(), deviceId, null, null);

        /**
         * 通知ws坐席请求外呼
         */
        WsCallEntity entity = new WsCallEntity();
        entity.setCallId(callInfo.getCallId());
        if (callInfo.getHiddenCustomer() == 1) {
            entity.setCalled(hiddenNumber(callInfo.getCalled()));
        } else {
            entity.setCalled(callInfo.getCalled());
        }
        entity.setCallType(CallType.OUTBOUNT_CALL);
        entity.setGroupId(callInfo.getGroupId());
        sendMessage(event, new WsResponseEntity<>(AgentState.OUT_CALL.name(), event.getAgentKey(), JSON.toJSONString(entity)));

        /**
         * 坐席请求外呼中
         */
        agentInfo.setBeforeState(agentInfo.getAgentState());
        agentInfo.setBeforeTime(agentInfo.getStateTime());
        agentInfo.setStateTime(Instant.now().getEpochSecond());
        agentInfo.setAgentState(AgentState.OUT_CALL);
        agentInfo.setCallId(callInfo.getCallId());
        agentInfo.setDeviceId(deviceId);
        /**
         * 广播坐席状态
         */
        syncAgentStateMessage(agentInfo);
    }
}
