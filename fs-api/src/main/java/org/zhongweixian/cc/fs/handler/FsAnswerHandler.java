package org.zhongweixian.cc.fs.handler;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.cti.cc.entity.CallDetail;
import org.cti.cc.entity.RouteGetway;
import org.cti.cc.entity.VdnPhone;
import org.cti.cc.enums.CallType;
import org.cti.cc.enums.NextType;
import org.cti.cc.po.*;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.zhongweixian.cc.configration.HandlerType;
import org.zhongweixian.cc.fs.event.FsAnswerEvent;
import org.zhongweixian.cc.fs.handler.base.BaseEventHandler;

import java.time.Instant;
import java.util.Date;

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
        NextCommand nextCommand = callInfo.getEaryCommand();
        logger.info("channel answer callId:{}, device:{}", callInfo.getCallId(), event.getDeviceId());

        //接听时间也是振铃结束时间
        deviceInfo.setAnswerTime(event.getTimestamp() / 1000);
        deviceInfo.setRingEndTime(event.getTimestamp() / 1000);

        callInfo.setAnswerCount(callInfo.getAnswerCount() + 1);
        if (nextCommand == null) {
            return;
        }
        callInfo.getNextCommands().remove(nextCommand);
        switch (nextCommand.getNextType()) {
            case NEXT_VDN:
                matchVdnCode(event, callInfo, deviceInfo);
                break;
            case NEXT_CALL_OTHER:
                callOther(callInfo, deviceInfo);
                break;
            case NEXT_TRANSFER_CALL:
                /**
                 * 转接电话 deviceInfo为被转接设备
                 */
                String fromDeviceId = nextCommand.getNextValue();
                // deviceInfo.setNextCommand(new NextCommand(NextType.NEXT_TRANSFER_BRIDGE, callInfo.getDeviceList().get(1)));
                DeviceInfo fromDevice = callInfo.getDeviceInfoMap().get(fromDeviceId);
                callInfo.getNextCommands().add(new NextCommand(event.getDeviceId(), NextType.NEXT_TRANSFER_SUCCESS, event.getDeviceId() + ":" + callInfo.getDeviceList().get(1)));
                // fromDevice.setNextCommand(new NextCommand(NextType.NEXT_TRANSFER_SUCCESS, event.getDeviceId() + ":" + callInfo.getDeviceList().get(1)));
                logger.info("转接电话中 callId:{} from:{} to:{} ", callInfo.getCallId(), fromDeviceId, event.getDeviceId());
                try {
                    transferCall(callInfo.getMedia(), callInfo.getDeviceList().get(1), event.getDeviceId());
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }
                //挂掉原有的电话
                hangupCall(callInfo.getMedia(), callInfo.getCallId(), fromDeviceId);
                break;
            case NEXT_CALL_BRIDGE:
                logger.info("开始桥接电话: callId:{} caller:{} called:{} device1:{}, device2:{}", callInfo.getCallId(), callInfo.getCaller(), callInfo.getCalled(), nextCommand.getNextValue(), event.getDeviceId());
                callBridge(event.getRemoteAddress(), event.getDeviceId(), nextCommand.getNextValue());
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
                break;
            default:
                logger.warn("can not match :{}, callId:{}", nextCommand.getNextType(), callInfo.getCallId());
                break;
        }
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
            String record = recordPath +
                    DateFormatUtils.format(new Date(), "yyyyMMdd") + "/" + callInfo.getCallId() + "_" + deviceInfo.getDeviceId() + "." + recordFile;
            super.record(callInfo.getMedia(), callInfo.getCallId(), callInfo.getDeviceList().get(0), record);
            deviceInfo.setRecord(record);
        }
        String deviceId = getDeviceId();
        logger.info("呼另外一侧电话: callId:{}  display:{}  called:{}  deviceId:{} ", callInfo.getCallId(), callInfo.getCalledDisplay(), callInfo.getCalled(), deviceId);
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
            logger.warn("callId:{} origin error, called:{}", callInfo.getCallId(), callInfo.getCalled());
            hangupCall(callInfo.getMedia(), callInfo.getCallId(), deviceInfo.getDeviceId());
            return;
        }
        fsListen.makeCall(callInfo.getMedia(), routeGetway, callInfo.getCalledDisplay(), called, deviceId);
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
        callInfo.getNextCommands().add(new NextCommand(deviceInfo.getDeviceId(), NextType.NEXT_CALL_BRIDGE, deviceInfo.getDeviceId()));
        callInfo.getDeviceInfoMap().put(deviceId, deviceInfo1);
        cacheService.addDevice(deviceId, callInfo.getCallId());
        cacheService.addCallInfo(callInfo);
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
            hangupCall(callInfo.getMedia(), callInfo.getCallId(), deviceInfo.getDeviceId());
            return;
        }
        vdnHandler.hanlder(callInfo, deviceInfo, vdnPhone.getVdnId());
        return;
    }

}
