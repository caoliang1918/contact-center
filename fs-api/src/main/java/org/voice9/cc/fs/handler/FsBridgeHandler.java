package org.voice9.cc.fs.handler;

import com.voice9.core.po.*;
import org.apache.commons.lang3.StringUtils;
import com.voice9.core.constant.Constant;
import com.voice9.core.entity.CallLog;
import com.voice9.core.enums.CallType;
import com.voice9.core.enums.Direction;
import com.voice9.core.util.DateTimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.voice9.cc.fs.handler.base.BaseEventHandler;
import org.voice9.cc.configration.HandlerType;
import org.voice9.cc.fs.event.FsBridgeEvent;
import org.voice9.cc.websocket.response.WsCallEntity;
import org.voice9.cc.websocket.response.WsResponseEntity;

import java.time.Instant;

/**
 * Created by caoliang on 2020/8/23
 */
@Component
@HandlerType("CHANNEL_BRIDGE")
public class FsBridgeHandler extends BaseEventHandler<FsBridgeEvent> {
    private Logger logger = LoggerFactory.getLogger(FsBridgeHandler.class);

    @Override
    public void handleEvent(FsBridgeEvent event) {
        CallInfo callInfo = cacheService.getCallInfo(event.getDeviceId());
        if (callInfo == null) {
            return;
        }
        if (callInfo.getAnswerTime() == null || callInfo.getAnswerTime() == 0L) {
            callInfo.setAnswerTime(event.getTimestamp() / 1000);
            CallLog callLog = new CallLog();
            BeanUtils.copyProperties(callInfo, callLog);
            callLog.setDirection(callInfo.getDirection().name());
            callLog.setCts(callInfo.getCallTime() / 1000);
            if (callInfo.getHiddenCustomer() == 1) {
                //隐藏客户侧号码
                if (Direction.INBOUND == callInfo.getDirection()) {
                    callLog.setCaller(hiddenNumber(callInfo.getCaller()));
                } else if (Direction.OUTBOUND == callInfo.getDirection()) {
                    callLog.setCalled(hiddenNumber(callInfo.getCalled()));
                }
            }
            callCdrService.saveOrUpdateCallLog(callLog);
            cacheService.addCallInfo(callInfo);
        }
        logger.info("桥接成功 callId:{}, device:{}, otherDevice:{}", callInfo.getCallId(), event.getDeviceId(), event.getOtherUniqueId());
        DeviceInfo deviceInfo1 = callInfo.getDeviceInfoMap().get(event.getDeviceId());
        DeviceInfo deviceInfo2 = callInfo.getDeviceInfoMap().get(event.getOtherUniqueId());
        if (deviceInfo1 != null && deviceInfo1.getBridgeTime() == null) {
            deviceInfo1.setBridgeTime(event.getTimestamp() / 1000);
        }
        if (deviceInfo2 != null && deviceInfo2.getBridgeTime() == null) {
            deviceInfo2.setBridgeTime(event.getTimestamp() / 1000);
        }
        if (!callInfo.getAnswerTime().equals(event.getTimestamp() / 1000)) {

        }

        GroupInfo groupInfo = cacheService.getGroupInfo(callInfo.getGroupId());
        if (groupInfo.getRecordType() > 0) {
            /**
             * 手动外呼：接通录音时在此录音
             * 呼入: 在此录音
             */
            if (callInfo.getCallType() == CallType.OUTBOUNT_CALL && groupInfo.getRecordType() == 2) {
                String record = recordPath + DateTimeUtil.format() + Constant.SK + callInfo.getCallId() + Constant.UNDER_LINE + callInfo.getDeviceList().get(0) + Constant.UNDER_LINE + Instant.now().getEpochSecond() + Constant.POINT + recordFile;
                super.record(event.getRemoteAddress(), callInfo.getCallId(), callInfo.getDeviceList().get(0), record);
                deviceInfo1.setRecord(record);
            } else if (callInfo.getCallType() == CallType.INBOUND_CALL) {
                String record = recordPath + DateTimeUtil.format() + Constant.SK + callInfo.getCallId() + Constant.UNDER_LINE + event.getDeviceId() + Constant.UNDER_LINE + Instant.now().getEpochSecond() + Constant.POINT + recordFile;
                super.record(event.getRemoteAddress(), callInfo.getCallId(), event.getDeviceId(), record);
                deviceInfo1.setRecord(record);
            }
        }

        if (StringUtils.isBlank(callInfo.getAgentKey())) {
            return;
        }

        if (deviceInfo1.getCdrType() == 5) {
            sendAgentState(callInfo, deviceInfo1.getAgentKey(), AgentState.CONSULTED_TALKING);
            if (deviceInfo2.getCdrType() == 2 && deviceInfo2.getDeviceType() == 1) {
                sendAgentState(callInfo, deviceInfo2.getAgentKey(), AgentState.CONSULT_TALKING);
            }
            return;
        }
        if (deviceInfo2.getCdrType() == 5) {
            sendAgentState(callInfo, deviceInfo2.getAgentKey(), AgentState.CONSULTED_TALKING);
            if (deviceInfo1.getCdrType() == 2 && deviceInfo1.getDeviceType() == 1) {
                sendAgentState(callInfo, deviceInfo1.getAgentKey(), AgentState.CONSULT_TALKING);
            }
            return;
        }
        sendAgentState(callInfo, callInfo.getAgentKey(), AgentState.TALKING);
    }


    /**
     * 电话桥接成功，给坐席sdk推送状态
     *
     * @param callInfo
     * @param agentKey
     * @param agentState
     */
    private void sendAgentState(CallInfo callInfo, String agentKey, AgentState agentState) {
        /**
         * 发送ws消息
         */
        WsCallEntity callEntity = new WsCallEntity();
        callEntity.setCallId(callInfo.getCallId());
        callEntity.setCallType(callInfo.getCallType());
        callEntity.setAgentState(agentState);
        callEntity.setCaller(callInfo.getCaller());
        callEntity.setCalled(callInfo.getCalled());
        callEntity.setDirection(callInfo.getDirection());
        AgentInfo agentInfo = cacheService.getAgentInfo(agentKey);
        if (agentInfo == null) {
            return;
        }
        if (agentInfo.getHiddenCustomer() == 1) {
            if (callInfo.getDirection() == Direction.OUTBOUND) {
                callEntity.setCalled(hiddenNumber(callInfo.getCalled()));
            } else if (callInfo.getDirection() == Direction.INBOUND) {
                callEntity.setCaller(hiddenNumber(callInfo.getCaller()));
            }
        }
        agentInfo.setBeforeState(agentInfo.getAgentState());
        agentInfo.setBeforeTime(agentInfo.getStateTime());
        agentInfo.setStateTime(Instant.now().getEpochSecond());
        agentInfo.setAgentState(agentState);
        sendWsMessage(agentInfo, new WsResponseEntity<WsCallEntity>(AgentState.TALKING.name(), agentInfo.getAgentKey(), callEntity));
    }
}
