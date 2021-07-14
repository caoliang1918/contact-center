package org.zhongweixian.cc.fs.handler;

import org.cti.cc.entity.RouteGetway;
import org.cti.cc.entity.VdnPhone;
import org.cti.cc.enums.CallType;
import org.cti.cc.enums.NextType;
import org.cti.cc.po.*;
import org.springframework.stereotype.Component;
import org.zhongweixian.cc.configration.HandlerType;
import org.zhongweixian.cc.fs.event.FsAnswerEvent;
import org.zhongweixian.cc.fs.handler.base.BaseEventHandler;

import java.time.Instant;

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
        NextCommand nextCommand = deviceInfo.getNextCommand();
        logger.info("channel answer callId:{}, device:{}, nextCommand:{}", callInfo.getCallId(), event.getDeviceId(), nextCommand.getNextType());

        //接听时间也是振铃结束时间
        deviceInfo.setAnswerTime(event.getTimestamp() / 1000);
        deviceInfo.setRingEndTime(event.getTimestamp() / 1000);
        if (callInfo.getAnswerCount() == null) {
            callInfo.setAnswerCount(1);
        } else {
            callInfo.setAnswerCount(callInfo.getAnswerCount() + 1);
        }
        if (nextCommand == null) {
            return;
        }
        deviceInfo.setNextCommand(null);
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
                fromDevice.setNextCommand(new NextCommand(NextType.NEXT_TRANSFER_SUCCESS, event.getDeviceId() + ":" + callInfo.getDeviceList().get(1)));
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
                callBridge(event.getHostname(), nextCommand.getNextValue(), event.getDeviceId());
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
        fsListen.makeCall(routeGetway, callInfo.getCalledDisplay(), called, deviceId);
        DeviceInfo deviceInfo1 = new DeviceInfo();
        //1:坐席,2:客户,3:外线
        deviceInfo1.setDeviceType(callInfo.getCallType() == CallType.INNER_CALL ? 1 : 2);
        //1:呼入,2:外呼,3:内呼,4:转接,5:咨询,6:监听,7:强插
        deviceInfo1.setCdrType(callInfo.getCallType() == CallType.INNER_CALL ? 3 : 2);
        deviceInfo1.setCallId(callInfo.getCallId());
        deviceInfo1.setCalled(called);
        deviceInfo1.setDisplay(called);
        deviceInfo1.setCaller(callInfo.getCalledDisplay());
        deviceInfo1.setDeviceId(deviceId);
        deviceInfo1.setCallTime(Instant.now().toEpochMilli());
        deviceInfo1.setAgentKey(callInfo.getAgentKey());
        deviceInfo1.setNextCommand(new NextCommand(NextType.NEXT_CALL_BRIDGE, deviceInfo.getDeviceId()));
        callInfo.getDeviceInfoMap().put(deviceId, deviceInfo1);
        cacheService.addDevice(deviceId, callInfo.getCallId());
    }

    /**
     *
     */
    private void transferCall() {

    }


    /**
     * 呼入电话进入vdn
     *
     * @param event
     * @param callInfo
     * @param deviceInfo
     */
    private void matchVdnCode(FsAnswerEvent event, CallInfo callInfo, DeviceInfo deviceInfo) {
        VdnPhone vdnPhone = cacheService.getVdnPhone(callInfo.getCalled());
        logger.info("inbount caller:{} called:{} for vdnId:{}", event.getCaller(), event.getCalled(), vdnPhone.getVdnId());
        CompanyInfo companyInfo = cacheService.getCompany(vdnPhone.getCompanyId());
        if (companyInfo == null || companyInfo.getStatus() == 0) {
            hangupCall(callInfo.getMedia(), callInfo.getCallId(), deviceInfo.getDeviceId());
            return;
        }
        vdnHandler.hanlder(vdnPhone.getVdnId(), callInfo, deviceInfo);
        return;
    }

}
