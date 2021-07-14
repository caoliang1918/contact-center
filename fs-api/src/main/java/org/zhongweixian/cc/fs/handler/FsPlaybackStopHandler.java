package org.zhongweixian.cc.fs.handler;

import org.cti.cc.po.CallInfo;
import org.cti.cc.po.DeviceInfo;
import org.cti.cc.po.NextCommand;
import org.springframework.stereotype.Component;
import org.zhongweixian.cc.configration.HandlerType;
import org.zhongweixian.cc.fs.event.FsPlaybackStop;
import org.zhongweixian.cc.fs.handler.base.BaseEventHandler;

/**
 * Created by caoliang on 2020/8/23
 */
@Component
@HandlerType("PLAYBACK_STOP")
public class FsPlaybackStopHandler extends BaseEventHandler<FsPlaybackStop> {

    @Override
    public void handleEvent(FsPlaybackStop event) {
        CallInfo callInfo = cacheService.getCallInfo(event.getDeviceId());
        if (callInfo == null) {
            return;
        }
        DeviceInfo deviceInfo = callInfo.getDeviceInfoMap().get(event.getDeviceId());
        NextCommand nextCommand = deviceInfo.getNextCommand();
        if (deviceInfo == null || nextCommand == null) {
            return;
        }
        doNextCommand(callInfo, deviceInfo);
        logger.info("callId:{} playstop, nextType:{}", callInfo.getCallId(), nextCommand.getNextType());
    }

}
