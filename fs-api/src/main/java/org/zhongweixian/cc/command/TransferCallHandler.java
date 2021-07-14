package org.zhongweixian.cc.command;

import org.cti.cc.entity.CallDetail;
import org.cti.cc.entity.RouteGetway;
import org.cti.cc.enums.CallType;
import org.cti.cc.enums.NextType;
import org.cti.cc.po.CallInfo;
import org.cti.cc.po.DeviceInfo;
import org.cti.cc.po.NextCommand;
import org.springframework.stereotype.Component;
import org.zhongweixian.cc.command.base.BaseHandler;

import java.time.Instant;

/**
 * Create by caoliang on 2020/12/3
 * <p>
 * 转外呼
 */
@Component
public class TransferCallHandler extends BaseHandler {


    /**
     * 转外呼
     *
     * @param callInfo
     * @param called
     * @param thisDeviceId
     */
    public void hanlder(CallInfo callInfo, String called, String thisDeviceId) {
        logger.info("callId:{} transfer to {}", callInfo.getCallId(), called);
        /**
         * 转外呼
         */
        callInfo.setCalled(called);
        callInfo.setCallType(CallType.TRANSFER_OUT_CALL);

        String deviceId = getDeviceId();
        RouteGetway routeGetway = cacheService.getRouteGetway(callInfo.getCompanyId(), callInfo.getCalled());
        if (routeGetway == null) {
            logger.warn("callId:{} origin error, called:{}", callInfo.getCallId(), callInfo.getCalled());
            fsListen.hangupCall(callInfo.getMedia(), callInfo.getCallId(), thisDeviceId);
            return;
        }

        DeviceInfo device = DeviceInfo.DeviceInfoBuilder.builder()
                .withDeviceId(deviceId)
                .withDeviceType(3)
                .withCdrType(2)
                .withCaller(callInfo.getCaller())
                .withCalled(callInfo.getCalled())
                .withCallId(callInfo.getCallId())
                .withCallTime(Instant.now().toEpochMilli())
                .withDisplay(callInfo.getCaller())
                .withNextCmd(new NextCommand(NextType.NEXT_CALL_BRIDGE, deviceId))
                .build();
        callInfo.getDeviceList().add(deviceId);
        callInfo.getDeviceInfoMap().put(deviceId, device);

        CallDetail transferCall = new CallDetail();
        transferCall.setCallId(callInfo.getCallId());
        transferCall.setStartTime(Instant.now().toEpochMilli());
        transferCall.setDetailIndex(callInfo.getCallDetails().size() + 1);
        transferCall.setTransferType(5);
        callInfo.getCallDetails().add(transferCall);
        fsListen.makeCall(routeGetway, callInfo.getCaller(), callInfo.getCalled(), deviceId);

    }
}
