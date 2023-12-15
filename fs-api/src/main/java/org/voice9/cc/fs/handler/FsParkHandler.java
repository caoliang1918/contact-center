package org.voice9.cc.fs.handler;

import com.voice9.core.constant.FsConstant;
import com.voice9.core.entity.Agent;
import com.voice9.core.entity.CallLog;
import com.voice9.core.entity.VdnPhone;
import com.voice9.core.enums.CallType;
import com.voice9.core.enums.CauseEnums;
import com.voice9.core.enums.Direction;
import com.voice9.core.enums.NextType;
import com.voice9.core.po.*;
import com.voice9.core.util.DateTimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.voice9.cc.fs.handler.base.BaseEventHandler;
import org.voice9.cc.util.RandomUtil;
import org.voice9.cc.configration.HandlerType;
import org.voice9.cc.fs.event.FsParkEvent;
import org.voice9.cc.websocket.response.WsCallEntity;
import org.voice9.cc.websocket.response.WsResponseEntity;

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
        if (FsConstant.OK.equals(event.getHangup())) {
            return;
        }
        logger.info("channel park:{}", event);
        CallInfo callInfo = cacheService.getCallInfo(event.getDeviceId());
        if (callInfo == null && Direction.INBOUND.name().equals(event.getDirection().toUpperCase())) {
            if (event.getSipPort() == null) {
                //呼入
                inboundCall(event);
                return;
            }
            if (!event.getSipContactPort().equals(event.getSipPort())) {
                //硬话机外呼
                sipOutboundCall(event);
                return;
            }
            return;
        }
        if (callInfo == null || callInfo.getHangupDir() != null) {
            return;
        }
        DeviceInfo deviceInfo = callInfo.getDeviceInfoMap().get(event.getDeviceId());
        if (deviceInfo == null) {
            return;
        }
        if (event.getHangup() != null) {
            return;
        }

        if (deviceInfo.getAnswerTime() != null && deviceInfo.getState() != null) {
            return;
        }

        AgentInfo agentInfo = cacheService.getAgentInfo(deviceInfo.getAgentKey());
        if (agentInfo == null) {
            return;
        }

        WsCallEntity ringEntity = new WsCallEntity();
        ringEntity.setCaller(callInfo.getCaller());
        ringEntity.setCalled(callInfo.getCalled());
        ringEntity.setGroupId(callInfo.getGroupId());
        ringEntity.setCallId(callInfo.getCallId());
        if (deviceInfo.getState() != null) {
            switch (deviceInfo.getState()) {
                case "HOLD":
                    ringEntity.setAgentState(AgentState.HOLD);
                    agentInfo.setAgentState(AgentState.HOLD);
                    sendWsMessage(cacheService.getAgentInfo(deviceInfo.getAgentKey()), new WsResponseEntity<WsCallEntity>(AgentState.HOLD.name(), callInfo.getAgentKey(), ringEntity));
                case "INSERT":
                    ringEntity.setAgentState(AgentState.INSERT);
                    agentInfo.setAgentState(AgentState.INSERT);
                    sendWsMessage(cacheService.getAgentInfo(deviceInfo.getAgentKey()), new WsResponseEntity<WsCallEntity>(AgentState.OUT_CALLER_RING.name(), callInfo.getAgentKey(), ringEntity));
                case "LISTEN":
                    ringEntity.setAgentState(AgentState.LISTEN);
                    agentInfo.setAgentState(AgentState.LISTEN);
                    sendWsMessage(cacheService.getAgentInfo(deviceInfo.getAgentKey()), new WsResponseEntity<WsCallEntity>(AgentState.OUT_CALLER_RING.name(), callInfo.getAgentKey(), ringEntity));
                default:
                    break;
            }
            return;

        }
        if (deviceInfo.getRingStartTime() != null) {
            return;
        }
        deviceInfo.setRingStartTime(event.getTimestamp() / 1000);
        Direction direction = callInfo.getDirection();
        logger.info("callId:{} device:{} park deviceType:{} cdrType:{} direction:{} sipProtocol:{}", deviceInfo.getCallId(), deviceInfo.getDeviceId(), deviceInfo.getDeviceType(), deviceInfo.getCdrType(), direction, event.getSipProtocol());

        ringEntity.setDirection(callInfo.getDirection());
        if (direction == Direction.OUTBOUND) {
            outboundCall(callInfo, deviceInfo, agentInfo, ringEntity);
            return;
        } else if (direction == Direction.INBOUND) {
            if (agentInfo == null) {
                return;
            }
            //呼入振铃
            agentInfo.setBeforeState(agentInfo.getAgentState());
            agentInfo.setBeforeTime(agentInfo.getStateTime());
            agentInfo.setStateTime(Instant.now().getEpochSecond());
            agentInfo.setAgentState(AgentState.IN_CALL_RING);
            ringEntity.setAgentState(AgentState.IN_CALL_RING);
            if (agentInfo.getHiddenCustomer() == 1) {
                ringEntity.setCaller(hiddenNumber(ringEntity.getCaller()));
            }
            sendWsMessage(agentInfo, new WsResponseEntity<WsCallEntity>(AgentState.IN_CALL_RING.name(), deviceInfo.getAgentKey(), ringEntity));
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
        //客户号码归属地
        String numberLocaton = "";
        logger.info("inbount callId:{} park, caller:{}, called:{}, deviceId:{}, uri:{}", callId, event.getCaller(), event.getCalled(), event.getDeviceId(), event.getContactUri());
        VdnPhone vdnPhone = cacheService.getVdnPhone(event.getCalled());
        if (vdnPhone == null) {
            logger.error("inbount callId:{} called:{} is not match for vdn", callId, event.getCalled());
            Long uuid = snowflakeIdWorker.nextId();
            CallLog callLog = new CallLog();
            callLog.setCallId(callId);
            callLog.setCts(Instant.now().getEpochSecond());
            callLog.setUts(callLog.getCts());
            callLog.setEndTime(callLog.getCts());
            callLog.setCaller(event.getCaller());
            callLog.setCalled(event.getCalled());
            callLog.setNumberLocation(numberLocaton);
            callLog.setCallType(CallType.INBOUND_CALL.name());
            callLog.setMediaHost(event.getRemoteAddress());
            callLog.setAnswerCount(1);
            callLog.setAnswerFlag(3);
            callLog.setDirection(Direction.INBOUND.name());
            callLog.setHangupDir(3);
            callLog.setHangupCode(CauseEnums.VDN_ERROR.getHuangupCode());
            callLog.setMonthTime(DateTimeUtil.getNowMonth());
            callCdrService.saveOrUpdateCallLog(callLog);
            hangupCall(event.getRemoteAddress(), uuid, event.getDeviceId());
            return;
        }

        CallInfo callInfo = CallInfo.CallInfoBuilder.builder().withCallId(callId).withCallType(CallType.INBOUND_CALL).withDirection(Direction.INBOUND).withCallTime(Instant.now().toEpochMilli()).withNumberLocation(numberLocaton)
                //.withCaller(event.getCaller())
                //接入号码
                .withCallerDisplay(event.getCalled()).withCompanyId(vdnPhone.getCompanyId()).withMediaHost(event.getRemoteAddress()).withCtiHost(event.getLocalAddress()).build();

        DeviceInfo deviceInfo = DeviceInfo.DeviceInfoBuilder.builder().withCallId(callId).withDeviceId(deviceId).withCaller(event.getCaller()).withCalled(event.getCalled()).withCallerLocation(numberLocaton).withCallTime(callInfo.getCallTime()).withDeviceType(2).withCdrType(1).build();

        CompanyInfo companyInfo = cacheService.getCompany(vdnPhone.getCompanyId());
        callInfo.setHiddenCustomer(companyInfo.getHiddenCustomer());
        callInfo.setCdrNotifyUrl(companyInfo.getNotifyUrl());
        callInfo.getDeviceInfoMap().put(deviceId, deviceInfo);
        callInfo.getDeviceList().add(deviceId);
        callInfo.getNextCommands().add(new NextCommand(deviceId, NextType.NEXT_VDN, null));

        cacheService.addCallInfo(callInfo);
        cacheService.addDevice(deviceId, callId);
        super.answer(event.getRemoteAddress(), deviceId);
    }


    /**
     * 外呼
     *
     * @param callInfo
     * @param deviceInfo
     * @param agentInfo
     * @param ringEntity
     */
    private void outboundCall(CallInfo callInfo, DeviceInfo deviceInfo, AgentInfo agentInfo, WsCallEntity ringEntity) {
        if (1 == deviceInfo.getDeviceType()) {
            CallType callType = callInfo.getCallType();
            if (callType == CallType.OUTBOUNT_CALL) {
                if (deviceInfo.getCdrType() == 4) {
                    //外呼转接坐席
                    ringEntity.setAgentState(AgentState.TRANSFER_CALL_RING);
                    if (agentInfo.getHiddenCustomer() == 1) {
                        ringEntity.setCalled(hiddenNumber(ringEntity.getCalled()));
                    }
                    sendWsMessage(cacheService.getAgentInfo(deviceInfo.getAgentKey()), new WsResponseEntity<WsCallEntity>(AgentState.TRANSFER_CALL_RING.name(), deviceInfo.getAgentKey(), ringEntity));
                } else if (deviceInfo.getCdrType() == 5) {
                    //外呼转接坐席
                    ringEntity.setAgentState(AgentState.CONSULT_CALL_RING);
                    if (agentInfo.getHiddenCustomer() == 1) {
                        ringEntity.setCalled(hiddenNumber(ringEntity.getCalled()));
                    }
                    sendWsMessage(cacheService.getAgentInfo(deviceInfo.getAgentKey()), new WsResponseEntity<WsCallEntity>(AgentState.CONSULT_CALL_RING.name(), deviceInfo.getAgentKey(), ringEntity));

                } else if (deviceInfo.getCdrType() == 2) {
                    //先判断坐席是否接通，如果已经接通，则返回当前接通后的状态
                    if (deviceInfo.getAnswerTime() != null && deviceInfo.getAnswerTime() > 0) {
                        AgentState agentState = agentInfo.getAgentState();
                        switch (agentState) {
                            case CONSULT:
                                ringEntity.setAgentState(AgentState.TRANSFER_CALL);
                                if (agentInfo.getHiddenCustomer() == 1) {
                                    ringEntity.setCalled(hiddenNumber(ringEntity.getCalled()));
                                }
                                sendWsMessage(cacheService.getAgentInfo(deviceInfo.getAgentKey()), new WsResponseEntity<WsCallEntity>(AgentState.TALKING.name(), callInfo.getAgentKey(), ringEntity));
                                break;

                            case TRANSFER:

                                break;
                        }
                        return;
                    }
                    //外呼坐席振铃
                    ringEntity.setAgentState(AgentState.OUT_CALLER_RING);
                    if (agentInfo.getHiddenCustomer() == 1) {
                        ringEntity.setCalled(hiddenNumber(ringEntity.getCalled()));
                    }
                    sendWsMessage(cacheService.getAgentInfo(deviceInfo.getAgentKey()), new WsResponseEntity<WsCallEntity>(AgentState.OUT_CALLER_RING.name(), callInfo.getAgentKey(), ringEntity));
                }
            } else if (callType == CallType.INNER_CALL) {
                //内呼坐席振铃
                ringEntity.setAgentState(AgentState.IN_CALL_RING);
                sendWsMessage(cacheService.getAgentInfo(deviceInfo.getAgentKey()), new WsResponseEntity<WsCallEntity>(AgentState.IN_CALL_RING.name(), callInfo.getAgentKey(), ringEntity));
            }
        } else {
            //外呼被叫振铃
            ringEntity.setAgentState(AgentState.OUT_CALLED_RING);
            if (agentInfo.getHiddenCustomer() == 1) {
                ringEntity.setCalled(hiddenNumber(ringEntity.getCalled()));
            }
            sendWsMessage(cacheService.getAgentInfo(callInfo.getAgentKey()), new WsResponseEntity<WsCallEntity>(AgentState.OUT_CALLED_RING.name(), callInfo.getAgentKey(), ringEntity));
        }
        cacheService.addCallInfo(callInfo);
    }

    /**
     * 硬话机外呼
     *
     * @param event
     */
    private void sipOutboundCall(FsParkEvent event) {
        //通过sip号码获取绑定的坐席
        Agent agent = agentService.getAgentBySip(event.getCaller());
        Long callId = snowflakeIdWorker.nextId();
        if (agent == null || agent.getGroupId() == null) {
            logger.warn("sipOutbound callId:{}  sip:{} called:{}", callId, event.getCaller(), event.getCalled());
            hangupCall(event.getRemoteAddress(), callId, event.getDeviceId());
            return;
        }
        //获取显号
        GroupInfo groupInfo = cacheService.getGroupInfo(agent.getGroupId());
        if (groupInfo == null && CollectionUtils.isEmpty(groupInfo.getCalledDisplays())) {
            logger.warn("callId:{}, agent:{}, group is null", callId, agent.getAgentKey());
            hangupCall(event.getRemoteAddress(), callId, event.getDeviceId());
            return;
        }
        AgentInfo agentInfo = cacheService.getAgentInfo(agent.getAgentKey());
        if (agentInfo == null) {
            agentInfo = agentService.getAgentInfo(agent.getAgentKey());
            cacheService.addAgentInfo(agentInfo);
        }
        logger.info("sipOutbound callId:{}  sip:{} called:{}", callId, event.getCaller(), event.getCalled());
        String calledDisplay = RandomUtil.getRandom(groupInfo.getCalledDisplays());

        CallInfo callInfo = CallInfo.CallInfoBuilder.builder().withCallId(callId).withCallType(CallType.SIP_OUTBOUND_CALL).withDirection(Direction.OUTBOUND).withCallTime(Instant.now().toEpochMilli()).withCaller(event.getCaller()).withCalled(event.getCalled()).withCompanyId(agent.getCompanyId()).withMediaHost(event.getRemoteAddress()).withCallerDisplay(agent.getAgentId()).withCalledDisplay(calledDisplay).withGroupId(groupInfo.getId()).withAgentKey(agent.getAgentKey()).build();

        DeviceInfo deviceInfo = DeviceInfo.DeviceInfoBuilder.builder().withCallId(callId).withDeviceId(event.getDeviceId()).withAgentKey(agent.getAgentKey()).withCaller(event.getCaller()).withCalled(event.getCalled()).withCallTime(callInfo.getCallTime()).withDeviceType(1).withCdrType(2).build();

        CompanyInfo companyInfo = cacheService.getCompany(agent.getCompanyId());
        callInfo.setHiddenCustomer(companyInfo.getHiddenCustomer());
        callInfo.setCdrNotifyUrl(companyInfo.getNotifyUrl());
        callInfo.getDeviceInfoMap().put(event.getDeviceId(), deviceInfo);
        callInfo.getDeviceList().add(event.getDeviceId());

        callInfo.getNextCommands().add(new NextCommand(deviceInfo.getDeviceId(), NextType.NEXT_CALL_OTHER, null));
        cacheService.addCallInfo(callInfo);
        cacheService.addDevice(event.getDeviceId(), callId);
        super.answer(event.getRemoteAddress(), event.getDeviceId());
    }
}