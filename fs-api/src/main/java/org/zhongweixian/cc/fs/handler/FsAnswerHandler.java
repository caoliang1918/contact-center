package org.zhongweixian.cc.fs.handler;

import io.netty.util.Timeout;
import io.netty.util.TimerTask;
import org.apache.commons.lang3.StringUtils;
import org.cti.cc.constant.Constant;
import org.cti.cc.entity.CallDetail;
import org.cti.cc.entity.RouteGetway;
import org.cti.cc.entity.VdnPhone;
import org.cti.cc.enums.CallType;
import org.cti.cc.enums.Direction;
import org.cti.cc.enums.NextType;
import org.cti.cc.po.*;
import org.cti.cc.util.DateTimeUtil;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.zhongweixian.cc.configration.HandlerType;
import org.zhongweixian.cc.fs.event.FsAnswerEvent;
import org.zhongweixian.cc.fs.handler.base.BaseEventHandler;
import org.zhongweixian.cc.websocket.response.WsCallEntity;
import org.zhongweixian.cc.websocket.response.WsResponseEntity;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

/**
 * Created by caoliang on 2020/8/23
 * <p>
 * 设备应答处理类
 */
@Component
@HandlerType("CHANNEL_ANSWER")
public class FsAnswerHandler extends BaseEventHandler<FsAnswerEvent> {

    @Override
    public void handleEvent(FsAnswerEvent event) {
        CallInfo callInfo = cacheService.getCallInfo(event.getDeviceId());
        if (callInfo == null) {
            return;
        }
        DeviceInfo deviceInfo = callInfo.getDeviceInfoMap().get(event.getDeviceId());
        NextCommand nextCommand = callInfo.getNextCommands().size() == 0 ? null : callInfo.getNextCommands().get(0);
        logger.info("channel answer callId:{}, deviceId:{}, deviceType:{}", callInfo.getCallId(), event.getDeviceId(), deviceInfo.getDeviceType());

        //接听时间也是振铃结束时间
        deviceInfo.setAnswerTime(event.getTimestamp() / 1000);
        deviceInfo.setRingEndTime(event.getTimestamp() / 1000);

        callInfo.setAnswerCount(callInfo.getAnswerCount() + 1);
        if (nextCommand == null) {
            return;
        }
        callInfo.getNextCommands().remove(nextCommand);
        if (StringUtils.isBlank(callInfo.getMediaHost())) {
            callInfo.setMediaHost(event.getRemoteAddress());
        }

        logger.info("callId:{}, deviceId:{}, nextCommand:{}", deviceInfo.getCallId(), deviceInfo.getDeviceId(), nextCommand);
        switch (nextCommand.getNextType()) {
            case NEXT_VDN:
                //进vdn
                matchVdnCode(event, callInfo, deviceInfo);
                break;

            case NEXT_CALL_OTHER:
                //呼叫另外一侧
                callOther(callInfo, deviceInfo);
                break;

            case NEXT_TRANSFER_CALL:
                //转接电话
                transferCall(callInfo, nextCommand, event);
                break;

            case NEXT_CALL_BRIDGE:
                //桥接
                callBridge(callInfo, deviceInfo, nextCommand, event);
                break;

            case NEXT_CONSULT_AGENT:
                //咨询坐席
                consultAgent(callInfo, deviceInfo, nextCommand, event);
                break;

            case NEXT_CONSULT_CALLOUT:
                //咨询外线
                consultCallout(callInfo, deviceInfo, nextCommand, event);
                break;

            case NEXT_INSERT_CALL:
                //强插
                callInfo.setConference(getDeviceId());
                insertCall(callInfo, deviceInfo, nextCommand, event);
                break;
            case NEXT_LISTEN_CALL:
                //班长监听
                monitorListen(callInfo, deviceInfo, nextCommand, event);
                break;

            case NEXT_WHISPER_CALL:
                //班长耳语
                monitorWhisper(callInfo, deviceInfo, nextCommand, event);

                break;

            default:
                logger.warn("can not match command :{}, callId:{}", nextCommand.getNextType(), callInfo.getCallId());
                break;
        }
        cacheService.addCallInfo(callInfo);
    }


    /**
     * 呼叫另外一侧
     *
     * @param callInfo
     * @param deviceInfo
     */
    private void callOther(CallInfo callInfo, DeviceInfo deviceInfo) {
        GroupInfo groupInfo = cacheService.getGroupInfo(callInfo.getGroupId());
        if (groupInfo != null && groupInfo.getRecordType() == 1) {
            //振铃录音
            String record = recordPath + DateTimeUtil.format() + Constant.SK + callInfo.getCallId() + Constant.UNDER_LINE + deviceInfo.getDeviceId() + Constant.UNDER_LINE + Instant.now().getEpochSecond() + Constant.POINT + recordFile;
            super.record(callInfo.getMediaHost(), callInfo.getCallId(), callInfo.getDeviceList().get(0), record);
            deviceInfo.setRecord(record);
            deviceInfo.setRecordStartTime(deviceInfo.getAnswerTime());
        }
        String deviceId = getDeviceId();
        logger.info("呼另外一侧电话: callId:{}  display:{}  called:{}  deviceId:{} ", callInfo.getCallId(), callInfo.getCalledDisplay(), hiddenNumber(callInfo.getCalled()), deviceId);
        callInfo.getDeviceList().add(deviceId);
        String called = callInfo.getCalled();

        //坐席内呼
        if (callInfo.getCallType() == CallType.INNER_CALL) {
            AgentInfo agentInfo = cacheService.getAgentInfo(called);
            called = agentInfo.getCalled();
            agentInfo.setCallId(callInfo.getCallId());
            agentInfo.setDeviceId(deviceId);
        }
        RouteGetway routeGetway = cacheService.getRouteGetway(callInfo.getCompanyId(), called);
        if (routeGetway == null) {
            logger.warn("callId:{} routeGetway error, called:{}", callInfo.getCallId(), callInfo.getCalled());
            hangupCall(callInfo.getMediaHost(), callInfo.getCallId(), deviceInfo.getDeviceId());
            return;
        }
        DeviceInfo deviceInfo1 = new DeviceInfo();
        //1:坐席,2:客户,3:外线
        deviceInfo1.setDeviceType(callInfo.getCallType() == CallType.INNER_CALL ? 1 : 2);
        //1:呼入,2:外呼,3:内呼,4:转接,5:咨询,6:监听,7:强插
        deviceInfo1.setCdrType(callInfo.getCallType() == CallType.INNER_CALL ? 3 : 2);
        deviceInfo1.setCallId(callInfo.getCallId());
        deviceInfo1.setCalled(called);
        deviceInfo1.setDisplay(callInfo.getCalledDisplay());
        deviceInfo1.setCaller(callInfo.getCalledDisplay());
        deviceInfo1.setDeviceId(deviceId);
        deviceInfo1.setCallTime(Instant.now().toEpochMilli());
        deviceInfo1.setAgentKey(callInfo.getAgentKey());
        callInfo.getNextCommands().add(new NextCommand(deviceInfo.getDeviceId(), NextType.NEXT_CALL_BRIDGE, deviceInfo1.getDeviceId()));
        callInfo.getDeviceInfoMap().put(deviceId, deviceInfo1);
        cacheService.addDevice(deviceId, callInfo.getCallId());

        /**
         * 呼叫外线，设置超时时间
         */
        fsListen.makeCall(callInfo.getMediaHost(), routeGetway, callInfo.getCalledDisplay(), called, callInfo.getCallId(), deviceId, groupInfo.getCallTimeOut());
        timeoutTask(callInfo.getCallId(), deviceId, groupInfo.getCallTimeOut());
    }


    /**
     * 呼入电话进入vdn
     *
     * @param event
     * @param callInfo
     * @param deviceInfo
     */
    private void matchVdnCode(FsAnswerEvent event, CallInfo callInfo, DeviceInfo deviceInfo) {
        VdnPhone vdnPhone = cacheService.getVdnPhone(callInfo.getCallerDisplay());
        logger.info("inbount caller:{} called:{} for vdnId:{}", event.getCaller(), event.getCalled(), vdnPhone.getVdnId());
        CompanyInfo companyInfo = cacheService.getCompany(vdnPhone.getCompanyId());
        if (companyInfo == null || companyInfo.getStatus() == 0) {
            logger.info("vdnPhone is not match:{}  {} ", event.getCaller(), event.getCalled());
            hangupCall(callInfo.getMediaHost(), callInfo.getCallId(), deviceInfo.getDeviceId());
            return;
        }
        vdnHandler.hanlder(callInfo, deviceInfo, vdnPhone.getVdnId());
        return;
    }

    /**
     * 转接电话
     *
     * @param callInfo
     * @param nextCommand
     * @param event
     */
    private void transferCall(CallInfo callInfo, NextCommand nextCommand, FsAnswerEvent event) {
        /**
         * 转接电话 deviceInfo为被转接设备
         */
        String fromDeviceId = nextCommand.getDeviceId();
        callInfo.getNextCommands().add(new NextCommand(event.getDeviceId(), NextType.NEXT_TRANSFER_SUCCESS, callInfo.getDeviceList().get(1)));
        logger.info("转接电话中 callId:{} from:{} to:{} ", callInfo.getCallId(), fromDeviceId, event.getDeviceId());
        try {
            transferCall(callInfo.getMediaHost(), event.getDeviceId(), nextCommand.getNextValue());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        //挂掉原有的电话
        hangupCall(callInfo.getMediaHost(), callInfo.getCallId(), fromDeviceId);
        callInfo.getNextCommands().add(new NextCommand(NextType.NORNAL));
    }

    /**
     * 桥接电话
     *
     * @param callInfo
     * @param deviceInfo
     * @param nextCommand
     * @param event
     */
    private void callBridge(CallInfo callInfo, DeviceInfo deviceInfo, NextCommand nextCommand, FsAnswerEvent event) {
        logger.info("开始桥接电话: callId:{} caller:{} called:{} device1:{}, device2:{}", callInfo.getCallId(), callInfo.getCaller(), hiddenNumber(callInfo.getCalled()), nextCommand.getDeviceId(), nextCommand.getNextValue());
        DeviceInfo deviceInfo1 = callInfo.getDeviceInfoMap().get(nextCommand.getDeviceId());
        DeviceInfo deviceInfo2 = callInfo.getDeviceInfoMap().get(nextCommand.getNextValue());
        if (deviceInfo1.getBridgeTime() == null) {
            deviceInfo1.setBridgeTime(event.getTimestamp() / 1000);
        }
        if (deviceInfo2.getBridgeTime() == null) {
            deviceInfo2.setBridgeTime(event.getTimestamp() / 1000);
        }
        bridgeCall(callInfo.getMediaHost(), callInfo.getCallId(), nextCommand.getDeviceId(), nextCommand.getNextValue());
        /**
         * 呼入电话，坐席接听后，需要桥接
         */
        if (callInfo.getCallType() == CallType.INBOUND_CALL) {
            if (callInfo.getQueueStartTime() != null && callInfo.getQueueEndTime() == null && deviceInfo.getDeviceType() == 1) {
                callInfo.setQueueEndTime(deviceInfo.getAnswerTime());
                if (!CollectionUtils.isEmpty(callInfo.getCallDetails())) {
                    CallDetail callDetail = callInfo.getCallDetails().get(callInfo.getCallDetails().size() - 1);
                    if (callDetail != null) {
                        callDetail.setEndTime(deviceInfo.getAnswerTime());
                    }
                }
                //更新坐席应答次数
                AgentInfo agentInfo = cacheService.getAgentInfo(deviceInfo.getAgentKey());
                agentInfo.setTotalAnswerTimes(agentInfo.getTotalRingTimes() + 1);
            }
        }
    }

    /**
     * 咨询坐席
     *
     * @param callInfo
     * @param deviceInfo
     * @param nextCommand
     * @param event
     */
    private void consultAgent(CallInfo callInfo, DeviceInfo deviceInfo, NextCommand nextCommand, FsAnswerEvent event) {
        /**
         * 咨询坐席
         */
        GroupInfo groupInfo = cacheService.getGroupInfo(callInfo.getGroupId());
        if (groupInfo != null && groupInfo.getRecordType() == 1) {
            //振铃录音
            String record = recordPath + DateTimeUtil.format() + Constant.SK + callInfo.getCallId() + Constant.UNDER_LINE + deviceInfo.getDeviceId() + Constant.UNDER_LINE + Instant.now().getEpochSecond() + Constant.POINT + recordFile;
            super.record(callInfo.getMediaHost(), callInfo.getCallId(), deviceInfo.getDeviceId(), record);
            deviceInfo.setRecord(record);
            deviceInfo.setRecordTime(event.getTimestamp() / 1000);
            callInfo.getDeviceInfoMap().put(deviceInfo.getDeviceId(), deviceInfo);
        }

        //发起咨询坐席先断开
        bridgeBreak(callInfo.getMediaHost(), nextCommand.getDeviceId());

        logger.info("开始桥接电话: callId:{} caller:{} called:{} device1:{}, device2:{}", callInfo.getCallId(), callInfo.getCaller(), callInfo.getCalled(), nextCommand.getDeviceId(), event.getDeviceId());
        bridgeCall(event.getRemoteAddress(), callInfo.getCallId(), nextCommand.getDeviceId(), event.getDeviceId());

        if (StringUtils.isBlank(callInfo.getConference())) {
            //客户保持音
            holdPlay(callInfo.getMediaHost(), nextCommand.getNextValue(), "/app/clpms/sounds/hold.wav");
            DeviceInfo consultDevice = callInfo.getDeviceInfoMap().get(nextCommand.getNextValue());
            consultDevice.setState(AgentState.HOLD.name());
        }
    }

    /**
     * 咨询外线
     *
     * @param callInfo
     * @param deviceInfo
     * @param nextCommand
     * @param event
     */
    private void consultCallout(CallInfo callInfo, DeviceInfo deviceInfo, NextCommand nextCommand, FsAnswerEvent event) {
        //发起咨询坐席先断开
        bridgeBreak(callInfo.getMediaHost(), nextCommand.getDeviceId());
        logger.info("开始桥接电话: callId:{} caller:{} called:{} device1:{}, device2:{}", callInfo.getCallId(), callInfo.getCaller(), callInfo.getCalled(), nextCommand.getDeviceId(), event.getDeviceId());
        bridgeCall(event.getRemoteAddress(), callInfo.getCallId(), nextCommand.getDeviceId(), event.getDeviceId());

        if (StringUtils.isBlank(callInfo.getConference())) {
            //客户保持音
            holdPlay(callInfo.getMediaHost(), nextCommand.getNextValue(), "/app/clpms/sounds/hold.wav");
            DeviceInfo consultDevice = callInfo.getDeviceInfoMap().get(nextCommand.getNextValue());
            consultDevice.setState(AgentState.HOLD.name());
        }
    }

    /**
     * 强插
     *
     * @param callInfo
     * @param deviceInfo1
     * @param nextCommand
     * @param event
     */
    private void insertCall(CallInfo callInfo, DeviceInfo deviceInfo1, NextCommand nextCommand, FsAnswerEvent event) {
        /**
         * 1、原坐席迁出
         * 2、所有设备拉入会议
         */
        bridgeBreak(callInfo.getMediaHost(), nextCommand.getDeviceId());
        callInfo.getDeviceList().forEach(deviceId -> {
            DeviceInfo deviceInfo = callInfo.getDeviceInfoMap().get(deviceId);
            deviceInfo.setConference(callInfo.getConference());
            joinConference(callInfo.getMediaHost(), callInfo.getCallId(), deviceInfo.getDeviceId(), callInfo.getConference());
        });
        WsCallEntity callEntity = new WsCallEntity();
        callEntity.setCallId(callInfo.getCallId());
        callEntity.setAgentState(AgentState.INSERT);
        sendWsMessage(cacheService.getAgentInfo(deviceInfo1.getAgentKey()), new WsResponseEntity<WsCallEntity>(AgentState.TALKING.name(), callInfo.getAgentKey(), callEntity));
    }

    /**
     * 监听通话
     *
     * @param callInfo
     * @param deviceInfo
     * @param nextCommand
     * @param event
     */
    private void monitorListen(CallInfo callInfo, DeviceInfo deviceInfo, NextCommand nextCommand, FsAnswerEvent event) {
        //班长监听
        fsListen.listen(callInfo.getMediaHost(), event.getDeviceId(), nextCommand.getDeviceId());

        //录音
        GroupInfo groupInfo = cacheService.getGroupInfo(callInfo.getGroupId());
        if (groupInfo != null && groupInfo.getRecordType() == 1) {
            //振铃录音
            String record = recordPath + DateTimeUtil.format() + Constant.SK + callInfo.getCallId() + Constant.UNDER_LINE + deviceInfo.getDeviceId() + Constant.UNDER_LINE + Instant.now().getEpochSecond() + Constant.POINT + recordFile;
            super.record(callInfo.getMediaHost(), callInfo.getCallId(), deviceInfo.getDeviceId(), record);
            deviceInfo.setRecord(record);
            deviceInfo.setRecordTime(event.getTimestamp() / 1000);
        }

        WsCallEntity callEntity = new WsCallEntity();
        callEntity.setAgentState(AgentState.LISTEN);
        callEntity.setCallId(callInfo.getCallId());
        sendWsMessage(cacheService.getAgentInfo(deviceInfo.getAgentKey()), new WsResponseEntity<WsCallEntity>(AgentState.TALKING.name(), callInfo.getAgentKey(), callEntity));
    }

    /**
     * 班长耳语
     *
     * @param callInfo
     * @param deviceInfo
     * @param nextCommand
     * @param event
     */
    private void monitorWhisper(CallInfo callInfo, DeviceInfo deviceInfo, NextCommand nextCommand, FsAnswerEvent event) {
        //班长耳语
        fsListen.whisper(callInfo.getMediaHost(), event.getDeviceId(), nextCommand.getDeviceId());

        //录音
        GroupInfo groupInfo = cacheService.getGroupInfo(callInfo.getGroupId());
        if (groupInfo != null && groupInfo.getRecordType() == 1) {
            //振铃录音
            String record = recordPath + DateTimeUtil.format() + Constant.SK + callInfo.getCallId() + Constant.UNDER_LINE + deviceInfo.getDeviceId() + Constant.UNDER_LINE + Instant.now().getEpochSecond() + Constant.POINT + recordFile;
            super.record(callInfo.getMediaHost(), callInfo.getCallId(), deviceInfo.getDeviceId(), record);
            deviceInfo.setRecord(record);
            deviceInfo.setRecordTime(event.getTimestamp() / 1000);
        }

        WsCallEntity callEntity = new WsCallEntity();
        callEntity.setAgentState(AgentState.WHISPER);
        callEntity.setCallId(callInfo.getCallId());
        sendWsMessage(cacheService.getAgentInfo(deviceInfo.getAgentKey()), new WsResponseEntity<WsCallEntity>(AgentState.TALKING.name(), callInfo.getAgentKey(), callEntity));
    }


    /**
     * 呼叫超时主动挂机
     *
     * @param callId
     * @param deviceId
     * @param timeouts
     */
    private void timeoutTask(Long callId, String deviceId, Integer timeouts) {
        hashedWheelTimer.newTimeout(new TimerTask() {
            @Override
            public void run(Timeout timeout) throws Exception {
                CallInfo callInfo = cacheService.getCallInfo(callId);
                if (callInfo == null || !callInfo.getDeviceList().contains(deviceId)) {
                    return;
                }
                DeviceInfo deviceInfo = callInfo.getDeviceInfoMap().get(deviceId);
                if (deviceInfo != null && deviceInfo.getAnswerTime() == null) {
                    logger.info("call:{} deviceId:{}  {}s timeout", callId, deviceId, timeouts);
                    if (callInfo.getDirection() == Direction.OUTBOUND) {
                        callInfo.setNextCommands(null);
                        cacheService.addCallInfo(callInfo);
                    }
                    hangupCall(callInfo.getMediaHost(), callId, deviceId);
                }
            }
        }, timeouts, TimeUnit.SECONDS);
    }
}
