package org.voice9.cc.command;

import com.voice9.core.entity.CallDetail;
import com.voice9.core.entity.RouteGetway;
import com.voice9.core.enums.NextType;
import com.voice9.core.po.CallInfo;
import com.voice9.core.po.DeviceInfo;
import com.voice9.core.po.NextCommand;
import org.springframework.stereotype.Component;
import org.voice9.cc.command.base.BaseHandler;

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

        String deviceId = getDeviceId();
        RouteGetway routeGetway = cacheService.getRouteGetway(callInfo.getCompanyId(), callInfo.getCalled());
        if (routeGetway == null) {
            logger.warn("callId:{} origin error, called:{}", callInfo.getCallId(), callInfo.getCalled());
            fsListen.hangupCall(callInfo.getMediaHost(), callInfo.getCallId(), thisDeviceId);
            return;
        }

        DeviceInfo device = DeviceInfo.DeviceInfoBuilder.builder().withDeviceId(deviceId).withDeviceType(3).withCdrType(2).withCaller(callInfo.getCaller()).withCalled(callInfo.getCalled()).withCallId(callInfo.getCallId()).withCallTime(Instant.now().toEpochMilli()).withDisplay(callInfo.getCaller()).build();
        callInfo.getDeviceList().add(deviceId);
        callInfo.getDeviceInfoMap().put(deviceId, device);

        CallDetail transferCall = new CallDetail();
        transferCall.setCallId(callInfo.getCallId());
        transferCall.setStartTime(Instant.now().toEpochMilli());
        transferCall.setDetailIndex(callInfo.getCallDetails().size() + 1);
        transferCall.setTransferType(5);
        callInfo.getCallDetails().add(transferCall);
        callInfo.getNextCommands().add(new NextCommand(thisDeviceId, NextType.NEXT_CALL_BRIDGE, deviceId));
        fsListen.makeCall(routeGetway, callInfo.getCaller(), callInfo.getCalled(), callInfo.getCallId(), deviceId,null, null);

    }
}
