package org.voice9.cc.fs.handler;

import com.voice9.core.po.CallInfo;
import org.voice9.cc.fs.handler.base.BaseEventHandler;
import org.voice9.cc.fs.event.FsPlaybackStartEvent;

/**
 * Created by caoliang on 2020/8/23
 */
//@Component
//@HandlerType("PLAYBACK_START")
public class FsPlaybackStartHandler extends BaseEventHandler<FsPlaybackStartEvent> {
    @Override
    public void handleEvent(FsPlaybackStartEvent event) {
        CallInfo callInfo = cacheService.getCallInfo(event.getDeviceId());
        if (callInfo == null) {
            return;
        }
        logger.info("callId:{} deviceId:{} 放音成功:{}", callInfo.getCallId(), event.getDeviceId(), event.toString());
    }
}
