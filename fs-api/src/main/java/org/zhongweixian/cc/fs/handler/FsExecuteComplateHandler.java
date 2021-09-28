package org.zhongweixian.cc.fs.handler;

import org.apache.commons.lang3.StringUtils;
import org.cti.cc.po.CallInfo;
import org.springframework.stereotype.Component;
import org.zhongweixian.cc.configration.HandlerType;
import org.zhongweixian.cc.fs.event.FsExecuteComplateEvent;
import org.zhongweixian.cc.fs.handler.base.BaseEventHandler;

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
                    logger.info("callId:{}, deviceId:{}, playback:{} success", callInfo.getCallId(), event.getDeviceId(), event.getApplicationData());
                } else if ("FILE NOT FOUND".equals(event.getResponse())) {
                    logger.error("callId:{}, deviceId:{}  file:{} not found", callInfo.getCallId(), event.getDeviceId(), event.getApplicationData());
                    hangupCall(event.getHostname(), callInfo.getCallId(), event.getDeviceId());
                    return;
                }
                break;

            case "play_and_get_digits":
                logger.info("callId:{}, deviceId:{}, get dtmf:{}", callInfo.getCallId(), event.getDeviceId(), event.getDtmf());
                break;

            case "break":
                break;

            default:
                break;
        }

        logger.info("callId:{}  execute:{} data:{} resposne:{}", callInfo.getCallId(), event.getApplication(), event.getApplicationData(), event.getResponse());
    }
}
