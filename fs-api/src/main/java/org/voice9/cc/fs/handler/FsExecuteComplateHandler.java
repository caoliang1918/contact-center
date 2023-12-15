package org.voice9.cc.fs.handler;

import org.apache.commons.lang3.StringUtils;
import com.voice9.core.po.CallInfo;
import org.springframework.stereotype.Component;
import org.voice9.cc.configration.HandlerType;
import org.voice9.cc.fs.event.FsExecuteComplateEvent;
import org.voice9.cc.fs.handler.base.BaseEventHandler;

/**
 * Created by caoliang on 2020/11/6
 */
@Component
@HandlerType("CHANNEL_EXECUTE_COMPLETE")
public class FsExecuteComplateHandler extends BaseEventHandler<FsExecuteComplateEvent> {

    @Override
    public void handleEvent(FsExecuteComplateEvent event) {
        if (StringUtils.isBlank(event.getApplication())) {
            return;
        }
        CallInfo callInfo = cacheService.getCallInfo(event.getDeviceId());
        if (callInfo == null) {
            return;
        }

        switch (event.getApplication()) {
            case "playback":
                if ("FILE PLAYED".equals(event.getResponse())) {
                    logger.info("deviceId:{}, playback:{} success", event.getDeviceId(), event.getApplicationData());
                    //正常排队放音在放完之后，需要循环放音
                    if (callInfo.getQueueStartTime() != null && callInfo.getQueueEndTime() == null) {
                        fsListen.playfile(callInfo.getMediaHost(), event.getDeviceId(), "queue.wav");
                        return;
                    }

                } else if ("FILE NOT FOUND".equals(event.getResponse())) {
                    logger.error("deviceId:{}  file:{} not found", event.getDeviceId(), event.getApplicationData());
                    hangupCall(event.getRemoteAddress(), callInfo.getCallId(), event.getDeviceId());
                    return;
                }
                break;

            case "play_and_get_digits":
                logger.info("deviceId:{}, get dtmf:{}", event.getDeviceId(), event.getDtmf());
                break;

            case "break":
                break;

            default:
                break;
        }
        logger.debug("execute:{} data:{} resposne:{}", event.getApplication(), event.getApplicationData(), event.getResponse());
    }
}
