package org.voice9.cc.fs.handler;

import com.voice9.core.po.CallInfo;
import com.voice9.core.po.DeviceInfo;
import org.springframework.stereotype.Component;
import org.voice9.cc.configration.HandlerType;
import org.voice9.cc.fs.event.FsRecordStartEvent;
import org.voice9.cc.fs.handler.base.BaseEventHandler;

/**
 * Created by caoliang on 2021/8/9
 * <p>
 * 录音开始
 */
@Component
@HandlerType("RECORD_START")
public class FsRecordStartHandler extends BaseEventHandler<FsRecordStartEvent> {

    @Override
    public void handleEvent(FsRecordStartEvent event) {
        CallInfo callInfo = cacheService.getCallInfo(event.getDeviceId());
        if (callInfo == null) {
            return;
        }
        DeviceInfo deviceInfo = callInfo.getDeviceInfoMap().get(event.getDeviceId());
        deviceInfo.setRecordTime(event.getTimestamp() / 1000);
        if (callInfo.getRecordTime() == null) {
            callInfo.setRecordTime(deviceInfo.getRecordTime());
        }
    }
}
