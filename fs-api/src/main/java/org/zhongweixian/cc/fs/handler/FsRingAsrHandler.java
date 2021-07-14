package org.zhongweixian.cc.fs.handler;

import org.cti.cc.po.CallInfo;
import org.cti.cc.po.DeviceInfo;
import org.springframework.stereotype.Component;
import org.zhongweixian.cc.configration.HandlerType;
import org.zhongweixian.cc.fs.event.FsRingAsrEvent;
import org.zhongweixian.cc.fs.handler.base.BaseEventHandler;

/**
 * Created by caoliang on 2020/8/23
 */
@Component
@HandlerType("RING_ASR")
public class FsRingAsrHandler extends BaseEventHandler<FsRingAsrEvent> {

    @Override
    public void handleEvent(FsRingAsrEvent event) {
        CallInfo callInfo = cacheService.getCallInfo(event.getDeviceId());
        if (callInfo == null) {
            return;
        }
        DeviceInfo deviceInfo = callInfo.getDeviceInfoMap().get(event.getDeviceId());

    }

}
