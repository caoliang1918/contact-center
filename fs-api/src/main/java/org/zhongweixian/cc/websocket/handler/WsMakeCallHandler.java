package org.zhongweixian.cc.websocket.handler;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.cti.cc.entity.RouteGetway;
import org.cti.cc.enums.Direction;
import org.cti.cc.enums.ErrorCode;
import org.cti.cc.enums.NextType;
import org.cti.cc.po.*;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.zhongweixian.cc.configration.HandlerType;
import org.zhongweixian.cc.util.RandomUtil;
import org.zhongweixian.cc.websocket.event.WsMakeCallEvent;
import org.zhongweixian.cc.websocket.handler.base.WsBaseHandler;
import org.zhongweixian.cc.websocket.response.WsResponseEntity;

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
        /**
         * 呼叫类型没有匹配上
         */
        if (event.getCallType() == null) {
            logger.warn("agent:{} callType error", event.getAgentKey());
            sendMessgae(event, new WsResponseEntity<>(ErrorCode.CALLTYPE_ERROR, AgentState.OUT_CALL.name(), event.getAgentKey()));
            event.getChannel().close();
            return;
        }
        if (event.getFollowData() != null) {
            if (JSONObject.toJSONString(event.getFollowData()).length() > 2048) {
                sendMessgae(event, new WsResponseEntity<>(ErrorCode.CALLTYPE_ERROR, AgentState.OUT_CALL.name(), event.getAgentKey()));
                return;
            }
        }
        AgentInfo agentInfo = getAgent(event);
        if (agentInfo.getAgentState().name().contains("CALL") || agentInfo.getAgentState() == AgentState.TALKING) {

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
        /**
         * 坐席所在的主技能组
         */
        GroupInfo groupInfo = getGroup(agentInfo.getGroupId());
        if (StringUtils.isNoneBlank(event.getDisplay())) {
            callerDisplay = event.getDisplay();
        } else {
            callerDisplay = agentInfo.getAgentId();
        }

        /**
         * 被叫显号
         */
        calledDisplay = getCalledDisplay(event, groupInfo, agentInfo);

        Long callId = snowflakeIdWorker.nextId();
        Long now = Instant.now().toEpochMilli();
        CallInfo callInfo = CallInfo.CallInfoBuilder.builder()
                .withCallId(callId)
                .withAgentKey(agentInfo.getAgentKey())
                .withLoginType(agentInfo.getLoginType())
                .withCompanyId(agentInfo.getCompanyId())
                .withGroupId(agentInfo.getGroupId())
                .withCaller(caller)
                .withCalled(event.getCalled())
                .withCallerDisplay(callerDisplay)
                .withCalledDisplay(calledDisplay)
                .withDirection(Direction.OUTBOUND)
                .withCallType(event.getCallType())
                .withCallTime(now)
                .withFollowData(event.getFollowData())
                .build();

        switch (event.getCallType()) {
            case INNER_CALL:
                AgentInfo calledAgent = cacheService.getAgentInfo(event.getCalled());
                if (calledAgent == null || calledAgent.getLogoutTime() > 0L) {
                    sendMessgae(event, new WsResponseEntity<>(ErrorCode.AGENT_NOT_ONLINE, AgentState.INNER_CALL.name(), event.getAgentKey()));
                    return;
                }
                //坐席不在READY、NOT_READY
                if (calledAgent.getAgentState() != AgentState.READY && calledAgent.getAgentState() != AgentState.NOT_READY) {
                    sendMessgae(event, new WsResponseEntity<>(ErrorCode.AGENT_BUSY, AgentState.INNER_CALL.name(), event.getAgentKey()));
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
        //技能组控制
        if (groupInfo.getControlFlag() == 1) {
            if (CollectionUtils.isEmpty(groupInfo.getCalledDisplays())) {
                return agentInfo.getAgentId();
            }
            calledDisplay = RandomUtil.getRandom(groupInfo.getCalledDisplays());
        } else if (StringUtils.isBlank(agentInfo.getDiaplay())) {
            //坐席控制
            if (!StringUtils.isBlank(agentInfo.getDiaplay())) {
                calledDisplay = agentInfo.getDiaplay();
            } else {
                return agentInfo.getAgentId();
            }
        }
        return calledDisplay;
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
        String deviceId = getDeviceId();
        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.setCaller(agentInfo.getAgentId());
        deviceInfo.setDisplay(callerDisplay);
        deviceInfo.setCalled(caller);
        deviceInfo.setCallTime(callInfo.getCallTime());
        deviceInfo.setCallId(callInfo.getCallId());
        deviceInfo.setDeviceId(deviceId);
        deviceInfo.setDeviceType(1);
        deviceInfo.setCdrType(2);
        deviceInfo.setAgentKey(agentInfo.getAgentKey());
        deviceInfo.setNextCommand(new NextCommand(NextType.NEXT_CALL_OTHER));


        callInfo.getDeviceList().add(deviceId);
        callInfo.getDeviceInfoMap().put(deviceId, deviceInfo);
        callInfo.setHiddenCustomer(0);
        cacheService.addCallInfo(callInfo);
        cacheService.addDevice(deviceId, callInfo.getCallId());

        RouteGetway routeGetway = cacheService.getRouteGetway(callInfo.getCompanyId(), caller);
        if (routeGetway == null) {
            logger.error("make call:{} origin route error", callInfo.getCallId());
            agentInfo.setBeforeTime(agentInfo.getStateTime());
            agentInfo.setBeforeState(agentInfo.getAgentState());
            agentInfo.setStateTime(Instant.now().toEpochMilli());
            agentInfo.setAgentState(AgentState.AFTER);
            sendAgentStateMessage(agentInfo);
            agentInfo.setCallId(null);

            /**
             * 通知ws坐席请求外呼
             */
            sendMessgae(event, new WsResponseEntity<>(ErrorCode.CALL_ROUTE_ERROR, AgentState.INNER_CALL.name(), event.getAgentKey()));
            return;
        }
        logger.info("agent:{} makecall, callId:{}, caller:{} called:{}", event.getAgentKey(), callInfo.getCallId(), callerDisplay, caller);
        fsListen.makeCall(routeGetway, callerDisplay, caller, deviceId);


        /**
         * 通知ws坐席请求外呼
         */
        sendMessgae(event, new WsResponseEntity<>(AgentState.INNER_CALL.name(), event.getAgentKey()));


        /**
         * 坐席请求外呼中
         */
        agentInfo.setBeforeState(agentInfo.getAgentState());
        agentInfo.setBeforeTime(agentInfo.getStateTime());
        agentInfo.setStateTime(Instant.now().toEpochMilli());
        agentInfo.setAgentState(AgentState.INNER_CALL);
        agentInfo.setCallId(callInfo.getCallId());
        agentInfo.setDeviceId(deviceId);
        /**
         * 广播坐席状态
         */
        sendAgentStateMessage(agentInfo);
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
        deviceInfo.setNextCommand(new NextCommand(NextType.NEXT_CALL_OTHER));

        if (StringUtils.isNoneBlank(event.getUuid1())) {
            callInfo.setUuid1(event.getUuid1());
        }
        if (StringUtils.isNoneBlank(event.getUuid2())) {
            callInfo.setUuid2(event.getUuid2());
        }
        callInfo.getDeviceList().add(deviceId);
        callInfo.getDeviceInfoMap().put(deviceId, deviceInfo);
        callInfo.setHiddenCustomer(agentInfo.getHiddenCustomer());
        callInfo.setCdrNotifyUrl(agentInfo.getCdrNotifyUrl());

        cacheService.addCallInfo(callInfo);
        cacheService.addDevice(deviceId, callInfo.getCallId());

        RouteGetway routeGetway = cacheService.getRouteGetway(callInfo.getCompanyId(), caller);
        if (routeGetway == null) {
            logger.error("agent:{} make call:{} origin route error", agentInfo.getAgentKey(), callInfo.getCallId());
            agentInfo.setBeforeTime(agentInfo.getStateTime());
            agentInfo.setBeforeState(agentInfo.getAgentState());
            agentInfo.setStateTime(Instant.now().toEpochMilli());
            agentInfo.setAgentState(AgentState.AFTER);
            sendAgentStateMessage(agentInfo);
            agentInfo.setCallId(null);

            /**
             * 通知ws坐席请求外呼
             */
            sendMessgae(event, new WsResponseEntity<>(ErrorCode.CALL_ROUTE_ERROR, AgentState.OUT_CALL.name(), event.getAgentKey()));
            return;
        }
        logger.info("agent:{} makecall, callId:{}, caller:{} called:{}", event.getAgentKey(), callInfo.getCallId(), callerDisplay, caller);
        fsListen.makeCall(routeGetway, callerDisplay, caller, deviceId);

        /**
         * 通知ws坐席请求外呼
         */
        sendMessgae(event, new WsResponseEntity<>(AgentState.OUT_CALL.name(), event.getAgentKey()));

        /**
         * 坐席请求外呼中
         */
        agentInfo.setBeforeState(agentInfo.getAgentState());
        agentInfo.setBeforeTime(agentInfo.getStateTime());
        agentInfo.setStateTime(Instant.now().toEpochMilli());
        agentInfo.setAgentState(AgentState.OUT_CALL);
        agentInfo.setCallId(callInfo.getCallId());
        agentInfo.setDeviceId(deviceId);
        /**
         * 广播坐席状态
         */
        sendAgentStateMessage(agentInfo);
    }
}
