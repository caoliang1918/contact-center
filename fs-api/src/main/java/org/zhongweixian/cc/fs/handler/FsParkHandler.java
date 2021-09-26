package org.zhongweixian.cc.fs.handler;

import org.apache.commons.lang3.StringUtils;
import org.cti.cc.constant.FsConstant;
import org.cti.cc.entity.Agent;
import org.cti.cc.entity.CallDetail;
import org.cti.cc.entity.CallLog;
import org.cti.cc.entity.VdnPhone;
import org.cti.cc.enums.CallType;
import org.cti.cc.enums.CauseEnums;
import org.cti.cc.enums.Direction;
import org.cti.cc.enums.NextType;
import org.cti.cc.po.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.zhongweixian.cc.configration.HandlerType;
import org.zhongweixian.cc.fs.event.FsParkEvent;
import org.zhongweixian.cc.fs.handler.base.BaseEventHandler;
import org.zhongweixian.cc.util.RandomUtil;
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

    /**
     * 硬话机外呼走的profile
     */
    @Value("${sip.outbound.profile:internal}")
    private String outboundProfile;

    @Value("${a:0}")
    private int a;

    @Override
    public void handleEvent(FsParkEvent event) {
        CallInfo callInfo = cacheService.getCallInfo(event.getDeviceId());
        if (callInfo == null && Direction.INBOUND.name().equals(event.getDirection().toUpperCase()) && a == 1) {
            if (StringUtils.isNotBlank(event.getSipPort()) && FsConstant.INTERNAL.equals(outboundProfile)) {
                //硬话机外呼
                sipOutbound(event);
                return;
            }
            //呼入
            inboundCall(event);
            return;
        }
        if (callInfo == null) {
            return;
        }
        DeviceInfo deviceInfo = callInfo.getDeviceInfoMap().get(event.getDeviceId());
        if (deviceInfo == null) {
            return;
        }
        if (deviceInfo.getAnswerTime() != null && deviceInfo.getDeviceType() == 2) {
            //呼入坐席挂机,用户收到park，则不处理
            return;
        }
        if (event.getHangup() != null) {
            return;
        }
        callInfo.setMedia(event.getHostname());
        if (StringUtils.isBlank(callInfo.getAgentKey())) {
            return;
        }


        Integer deviceType = deviceInfo.getDeviceType();
        WsCallEntity ringEntity = new WsCallEntity();
        AgentInfo agentInfo = cacheService.getAgentInfo(callInfo.getAgentKey());
        ringEntity.setCaller(callInfo.getCaller());

        ringEntity.setCalled(callInfo.getCalled());
        ringEntity.setGroupId(callInfo.getGroupId());
        ringEntity.setCallId(callInfo.getCallId());
        ringEntity.setDirection(callInfo.getDirection());
        if (deviceInfo.getState() != null) {
            switch (deviceInfo.getState()) {
                case "HOLD":
                    ringEntity.setAgentState(AgentState.HOLD);
                    agentInfo.setAgentState(AgentState.HOLD);
                    break;
            }
            sendAgentStateMessage(cacheService.getAgentInfo(deviceInfo.getAgentKey()), new WsResponseEntity<WsCallEntity>(ringEntity.getAgentState().name(), callInfo.getAgentKey(), ringEntity));
            agentInfo.setBeforeState(agentInfo.getAgentState());
            agentInfo.setBeforeTime(agentInfo.getStateTime());
            agentInfo.setStateTime(Instant.now().toEpochMilli());
            agentService.sendAgentStateMessage(agentInfo);
            return;

        }
        if (deviceInfo.getRingStartTime() != null) {
            return;
        }
        deviceInfo.setRingStartTime(event.getTimestamp() / 1000);
        logger.info("callId:{}, device:{} park", callInfo.getCallId(), event.getDeviceId());

        Direction direction = callInfo.getDirection();
        if (direction == Direction.OUTBOUND) {
            if (1 == deviceType) {
                CallType callType = callInfo.getCallType();
                if (callType == CallType.OUTBOUNT_CALL) {
                    if (deviceInfo.getCdrType() == 4) {
                        //外呼转接坐席
                        ringEntity.setAgentState(AgentState.TRANSFER_CALL_RING);
                        if (agentInfo.getHiddenCustomer() == 1) {
                            ringEntity.setCalled(hiddenNumber(ringEntity.getCalled()));
                        }
                        sendAgentStateMessage(cacheService.getAgentInfo(deviceInfo.getAgentKey()), new WsResponseEntity<WsCallEntity>(AgentState.TRANSFER_CALL_RING.name(), callInfo.getAgentKey(), ringEntity));
                    } else {
                        //外呼坐席振铃
                        ringEntity.setAgentState(AgentState.OUT_CALLER_RING);
                        if (agentInfo.getHiddenCustomer() == 1) {
                            ringEntity.setCalled(hiddenNumber(ringEntity.getCalled()));
                        }
                        sendAgentStateMessage(cacheService.getAgentInfo(deviceInfo.getAgentKey()), new WsResponseEntity<WsCallEntity>(AgentState.OUT_CALLER_RING.name(), callInfo.getAgentKey(), ringEntity));
                    }
                } else if (callType == CallType.INNER_CALL) {
                    //内呼坐席振铃
                    ringEntity.setAgentState(AgentState.IN_CALL_RING);
                    sendAgentStateMessage(cacheService.getAgentInfo(deviceInfo.getAgentKey()), new WsResponseEntity<WsCallEntity>(AgentState.IN_CALL_RING.name(), callInfo.getAgentKey(), ringEntity));
                }
            } else {
                //外呼被叫振铃
                ringEntity.setAgentState(AgentState.OUT_CALLED_RING);
                if (agentInfo.getHiddenCustomer() == 1) {
                    ringEntity.setCalled(hiddenNumber(ringEntity.getCalled()));
                }
                sendAgentStateMessage(cacheService.getAgentInfo(callInfo.getAgentKey()), new WsResponseEntity<WsCallEntity>(AgentState.OUT_CALLED_RING.name(), callInfo.getAgentKey(), ringEntity));
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
            sendAgentStateMessage(cacheService.getAgentInfo(deviceInfo.getAgentKey()), new WsResponseEntity<WsCallEntity>(AgentState.IN_CALL_RING.name(), deviceInfo.getAgentKey(), ringEntity));
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
        logger.info("inbount callId:{} park, caller:{}, called:{}, deviceId:{}, uri:{}", callId, event.getCaller(), event.getCalled(), event.getDeviceId(), event.getContactUri());
        VdnPhone vdnPhone = cacheService.getVdnPhone(event.getCalled());
        if (vdnPhone == null) {
            logger.error("inbount callId:{} called:{} is not match for vdn", callId, event.getCalled());
            Long uuid = snowflakeIdWorker.nextId();
            CallLog callLog = new CallLog();
            callLog.setCallId(callId);
            callLog.setCts(event.getTimestamp() / 1000);
            callLog.setUts(callLog.getCts());
            callLog.setEndTime(callLog.getCts());
            callLog.setCaller(event.getCaller());
            callLog.setCalled(event.getCalled());
            callLog.setCallType(CallType.INBOUND_CALL.name());
            callLog.setMedia(event.getHostname());
            callLog.setAnswerCount(1);
            callLog.setAnswerFlag(3);
            callLog.setDirection(Direction.INBOUND.name());
            callLog.setHangupDir(3);
            callLog.setHangupCause(CauseEnums.VDN_ERROR.name());
            callCdrService.saveOrUpdateCallLog(callLog);
            hangupCall(event.getHostname(), uuid, event.getDeviceId());
            return;
        }

        CallInfo callInfo = CallInfo.CallInfoBuilder.builder()
                .withCallId(callId)
                .withCallType(CallType.INBOUND_CALL)
                .withDirection(Direction.INBOUND)
                .withCallTime(Instant.now().toEpochMilli())
                //用户号码
                .withCaller(event.getCaller())
                //接入号码
                .withCallerDisplay(event.getCalled())
                .withCompanyId(vdnPhone.getCompanyId())
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

        cacheService.addCallInfo(callInfo);
        cacheService.addDevice(deviceId, callId);
        super.answer(event.getHostname(), deviceId);
    }

    /**
     * 硬话机外呼
     *
     * @param event
     */
    private void sipOutbound(FsParkEvent event) {
        //通过sip号码获取绑定的坐席
        Agent agent = agentService.getAgentBySip(event.getCaller());
        Long callId = snowflakeIdWorker.nextId();
        if (agent == null || agent.getGroupId() == null) {
            logger.warn("sipOutbound callId:{}  sip:{} called:{}", callId, event.getCaller(), event.getCalled());
            hangupCall(event.getHostname(), callId, event.getDeviceId());
            return;
        }
        //获取显号
        GroupInfo groupInfo = cacheService.getGroupInfo(agent.getGroupId());
        if (groupInfo == null && CollectionUtils.isEmpty(groupInfo.getCalledDisplays())) {
            logger.warn("callId:{}, agent:{}, group is null", callId, agent.getAgentKey());
            hangupCall(event.getHostname(), callId, event.getDeviceId());
            return;
        }
        AgentInfo agentInfo = cacheService.getAgentInfo(agent.getAgentKey());
        if (agentInfo == null) {
            agentInfo = agentService.getAgentInfo(agent.getAgentKey());
            cacheService.addAgentInfo(agentInfo);
        }
        logger.info("sipOutbound callId:{}  sip:{} called:{}", callId, event.getCaller(), event.getCalled());
        String calledDisplay = RandomUtil.getRandom(groupInfo.getCalledDisplays());

        CallInfo callInfo = CallInfo.CallInfoBuilder.builder()
                .withCallId(callId)
                .withCallType(CallType.OUTBOUNT_CALL)
                .withDirection(Direction.OUTBOUND)
                .withCallTime(Instant.now().toEpochMilli())
                .withCaller(event.getCaller())
                .withCalled(event.getCalled())
                .withCompanyId(agent.getCompanyId())
                .withMedia(event.getHostname())
                .withCallerDisplay(agent.getAgentId())
                .withCalledDisplay(calledDisplay)
                .withGroupId(groupInfo.getId())
                .withAgentKey(agent.getAgentKey())
                .build();

        DeviceInfo deviceInfo = DeviceInfo.DeviceInfoBuilder.builder()
                .withCallId(callId)
                .withDeviceId(event.getDeviceId())
                .withAgentKey(agent.getAgentKey())
                .withCaller(event.getCaller())
                .withCalled(event.getCalled())
                .withCallTime(callInfo.getCallTime())
                .withDeviceType(1)
                .withCdrType(8)
                .withNextCmd(new NextCommand(NextType.NEXT_CALL_OTHER))
                .build();

        CompanyInfo companyInfo = cacheService.getCompany(agent.getCompanyId());
        callInfo.setHiddenCustomer(companyInfo.getHiddenCustomer());
        callInfo.setCdrNotifyUrl(companyInfo.getNotifyUrl());
        callInfo.getDeviceInfoMap().put(event.getDeviceId(), deviceInfo);
        callInfo.getDeviceList().add(event.getDeviceId());

        cacheService.addCallInfo(callInfo);
        cacheService.addDevice(event.getDeviceId(), callId);
        super.answer(event.getHostname(), event.getDeviceId());

    }
}