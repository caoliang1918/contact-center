package org.voice9.cc.fs.handler;

import com.voice9.core.po.CallInfo;
import com.voice9.core.po.DeviceInfo;
import org.springframework.stereotype.Component;
import org.voice9.cc.configration.HandlerType;
import org.voice9.cc.fs.event.FsRingAsrEvent;
import org.voice9.cc.fs.handler.base.BaseEventHandler;

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
