package org.zhongweixian.cc.fs.handler;

import org.apache.commons.lang3.StringUtils;
import org.cti.cc.entity.CallDetail;
import org.cti.cc.entity.CallLog;
import org.cti.cc.entity.VdnPhone;
import org.cti.cc.enums.CallType;
import org.cti.cc.enums.Direction;
import org.cti.cc.enums.NextType;
import org.cti.cc.po.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.zhongweixian.cc.configration.HandlerType;
import org.zhongweixian.cc.fs.event.FsParkEvent;
import org.zhongweixian.cc.fs.handler.base.BaseEventHandler;
import org.zhongweixian.cc.websocket.response.WsCallEntity;
import org.zhongweixian.cc.websocket.response.WsResponseEntity;

import java.time.Instant;

/**
 * Created by caoliang on 2020/8/23
 * <p>
 * 设备话机振铃
 */
@Component
@HandlerType("CHANNEL_PARK")
public class FsParkHandler extends BaseEventHandler<FsParkEvent> {
    private Logger logger = LoggerFactory.getLogger(FsParkHandler.class);


    @Override
    public void handleEvent(FsParkEvent event) {
        CallInfo callInfo = cacheService.getCallInfo(event.getDeviceId());
        if (callInfo == null && Direction.INBOUND.name().equals(event.getDirection().toUpperCase())) {
            inboundCall(event);
            return;
        }
        if (callInfo == null) {
            return;
        }
        callInfo.setMedia(event.getHostname());
        DeviceInfo deviceInfo = callInfo.getDeviceInfoMap().get(event.getDeviceId());
        if (deviceInfo == null) {
            return;
        }
        if (StringUtils.isBlank(callInfo.getAgentKey())) {
            return;
        }
        deviceInfo.setRingStartTime(event.getTimestamp() / 1000);
        logger.info("callId:{}, device:{} park", callInfo.getCallId(), event.getDeviceId());
        Integer deviceType = deviceInfo.getDeviceType();
        WsCallEntity ringEntity = new WsCallEntity();
        AgentInfo agentInfo = cacheService.getAgentInfo(callInfo.getAgentKey());
        ringEntity.setCaller(callInfo.getCaller());

        ringEntity.setCalled(callInfo.getCalled());
        ringEntity.setGroupId(callInfo.getGroupId());
        ringEntity.setCallId(callInfo.getCallId());
        ringEntity.setDirection(callInfo.getDirection());

        Direction direction = callInfo.getDirection();
        if (direction == Direction.OUTBOUND) {
            if (1 == deviceType) {
                CallType callType = callInfo.getCallType();
                if (callType == CallType.OUTBOUNT_CALL) {
                    //外呼坐席振铃
                    ringEntity.setAgentState(AgentState.OUT_CALLER_RING);
                    if (agentInfo.getHiddenCustomer() == 1) {
                        ringEntity.setCalled(hiddenNumber(ringEntity.getCalled()));
                    }
                    sendAgentMessage(callInfo.getAgentKey(), new WsResponseEntity<WsCallEntity>(AgentState.OUT_CALLER_RING.name(), callInfo.getAgentKey(), ringEntity));
                } else if (callType == CallType.INNER_CALL) {
                    //内呼坐席振铃
                    ringEntity.setAgentState(AgentState.IN_CALL_RING);
                    sendAgentMessage(deviceInfo.getAgentKey(), new WsResponseEntity<WsCallEntity>(AgentState.IN_CALL_RING.name(), callInfo.getAgentKey(), ringEntity));
                }
            } else {
                //外呼被叫振铃
                ringEntity.setAgentState(AgentState.OUT_CALLED_RING);
                if (agentInfo.getHiddenCustomer() == 1) {
                    ringEntity.setCalled(hiddenNumber(ringEntity.getCalled()));
                }
                sendAgentMessage(callInfo.getAgentKey(), new WsResponseEntity<WsCallEntity>(AgentState.OUT_CALLED_RING.name(), callInfo.getAgentKey(), ringEntity));
            }
        } else if (direction == Direction.INBOUND) {
            if (agentInfo == null) {
                return;
            }
            //呼入振铃
            ringEntity.setAgentState(AgentState.IN_CALL_RING);
            if (agentInfo.getHiddenCustomer() == 1) {
                ringEntity.setCaller(hiddenNumber(ringEntity.getCaller()));
            }
            sendAgentMessage(callInfo.getAgentKey(), new WsResponseEntity<WsCallEntity>(AgentState.IN_CALL_RING.name(), callInfo.getAgentKey(), ringEntity));

            agentInfo.setBeforeState(agentInfo.getAgentState());
            agentInfo.setBeforeTime(agentInfo.getStateTime());
            agentInfo.setStateTime(Instant.now().toEpochMilli());
            agentInfo.setAgentState(AgentState.IN_CALL_RING);
            agentService.sendAgentStateMessage(agentInfo);
        }
    }


    /**
     * 呼入电话
     *
     * @param event
     */
    private void inboundCall(FsParkEvent event) {
        Long callId = snowflakeIdWorker.nextId();
        String deviceId = event.getDeviceId();
        logger.info("inbount callId:{} park, caller:{}, called:{}, deviceId:{}", callId, event.getCaller(), event.getCalled(), event.getDeviceId());
        VdnPhone vdnPhone = cacheService.getVdnPhone(event.getCalled());
        if (vdnPhone == null) {
            logger.error("inbount callId:{} called:{} is not match for vdn", callId, event.getCalled());
            Long uuid = snowflakeIdWorker.nextId();
            hangupCall(event.getHostname(), uuid, event.getDeviceId());
            /*CallLog callLog = new CallLog();
            callLog.setCallId(callId);
            callLog.setCts(event.getTimestamp() / 1000);
            callLog.setUts(callLog.getCts());
            callLog.setEndTime(callLog.getCts());
            callLog.setCaller(event.getCaller());
            callLog.setCalled(event.getCalled());
            callLog.setCallType(CallType.INBOUND_CALL.name());

            CompanyInfo companyInfo = cacheService.getCompany(callLog.getCompanyId());
            if (companyInfo.getHiddenCustomer() == 2) {
                //隐藏客户侧号码
                callLog.setCaller(hiddenNumber(callLog.getCaller()));
            }
            callCdrService.saveOrUpdateCallLog(callLog);*/
            return;
        }

        CallInfo callInfo = CallInfo.CallInfoBuilder.builder()
                .withCallId(callId)
                .withCallType(CallType.INBOUND_CALL)
                .withDirection(Direction.INBOUND)
                .withCallTime(Instant.now().toEpochMilli())
                .withCaller(event.getCaller())
                .withCalled(event.getCalled())
                .withCompanyId(vdnPhone.getCompanyId())
                .withAnswerCount(1)
                .withMedia(event.getHostname())
                .build();

        DeviceInfo deviceInfo = DeviceInfo.DeviceInfoBuilder.builder()
                .withCallId(callId)
                .withDeviceId(deviceId)
                .withCaller(event.getCaller())
                .withCalled(event.getCalled())
                .withCallTime(callInfo.getCallTime())
                .withDeviceType(2)
                .withCdrType(1)
                .withNextCmd(new NextCommand(NextType.NEXT_VDN))
                .build();

        CompanyInfo companyInfo = cacheService.getCompany(vdnPhone.getCompanyId());
        callInfo.setHiddenCustomer(companyInfo.getHiddenCustomer());
        callInfo.setCdrNotifyUrl(companyInfo.getNotifyUrl());
        callInfo.getDeviceInfoMap().put(deviceId, deviceInfo);
        callInfo.getDeviceList().add(deviceId);


        /**
         * 记录电话流程
         */
        CallDetail callDetail = new CallDetail();
        callDetail.setCallId(callId);
        callDetail.setDetailIndex(1);
        callDetail.setTransferType(1);
        callDetail.setTransferId(vdnPhone.getVdnId());
        callDetail.setStartTime(Instant.now().toEpochMilli());
        callInfo.getCallDetails().add(callDetail);
        cacheService.addCallInfo(callInfo);
        cacheService.addDevice(deviceId, callId);
        this.answer(event.getHostname(), deviceId);
    }
}