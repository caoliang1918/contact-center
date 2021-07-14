package org.zhongweixian.cc.fs.handler;

import org.cti.cc.po.CallInfo;
import org.springframework.stereotype.Component;
import org.zhongweixian.cc.configration.HandlerType;
import org.zhongweixian.cc.fs.event.FsPlaybackStart;
import org.zhongweixian.cc.fs.handler.base.BaseEventHandler;

/**
 * Created by caoliang on 2020/8/23
 */
@Component
@HandlerType("PLAYBACK_START")
public class FsPlaybackStartHandler extends BaseEventHandler<FsPlaybackStart> {
    @Override
    public void handleEvent(FsPlaybackStart event) {
        CallInfo callInfo = cacheService.getCallInfo(event.getDeviceId());
        if (callInfo == null) {
            return;
        }
        logger.info("callId:{} deviceId:{} 放音成功:{}", callInfo.getCallId(), event.getDeviceId(), event.toString());
    }
}
