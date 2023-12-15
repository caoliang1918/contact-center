package org.voice9.cc.fs.handler;

import com.voice9.core.po.CallInfo;
import com.voice9.core.po.DeviceInfo;
import com.voice9.core.po.NextCommand;
import org.springframework.stereotype.Component;
import org.voice9.cc.fs.handler.base.BaseEventHandler;
import org.voice9.cc.configration.HandlerType;
import org.voice9.cc.fs.event.FsPlaybackStopEvent;

/**
 * Created by caoliang on 2020/8/23
 * <p>
 * 放音结束
 */
@Component
@HandlerType("PLAYBACK_STOP")
public class FsPlaybackStopHandler extends BaseEventHandler<FsPlaybackStopEvent> {

    @Override
    public void handleEvent(FsPlaybackStopEvent event) {
        CallInfo callInfo = cacheService.getCallInfo(event.getDeviceId());
        if (callInfo == null) {
            return;
        }
        DeviceInfo deviceInfo = callInfo.getDeviceInfoMap().get(event.getDeviceId());
        if (deviceInfo.getEndTime() != null) {
            return;
        }
        NextCommand nextCommand = callInfo.getNextCommands().size() == 0 ? null : callInfo.getNextCommands().get(0);
        if (deviceInfo == null || nextCommand == null) {
            return;
        }
        doNextCommand(callInfo, deviceInfo, nextCommand);
        logger.info("callId:{} playstop, nextType:{}", callInfo.getCallId(), nextCommand.getNextType());
    }

}
