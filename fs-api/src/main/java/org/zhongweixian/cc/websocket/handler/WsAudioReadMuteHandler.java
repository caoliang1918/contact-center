package org.zhongweixian.cc.websocket.handler;

import org.cti.cc.po.AgentInfo;
import org.cti.cc.po.CallInfo;
import org.springframework.stereotype.Component;
import org.zhongweixian.cc.configration.HandlerType;
import org.zhongweixian.cc.websocket.event.WsAudioReadMuteEvent;
import org.zhongweixian.cc.websocket.handler.base.WsBaseHandler;

/**
 * Created by caoliang on 2021/7/16
 * <p>
 * 坐席静音
 */
@Component
@HandlerType("WS_AUDIO_READ_MUTE")
public class WsAudioReadMuteHandler extends WsBaseHandler<WsAudioReadMuteEvent> {

    @Override
    public void handleEvent(WsAudioReadMuteEvent event) {
        AgentInfo agentInfo = getAgent(event);
        if (agentInfo == null || agentInfo.getCallId() == null) {
            return;
        }
        String deviceId = agentInfo.getDeviceId();
        CallInfo callInfo = cacheService.getCallInfo(agentInfo.getCallId());
        if (deviceId == null || callInfo == null) {

            return;
        }
        this.audioReadMute(callInfo.getMedia(), callInfo.getCallId(), deviceId);
    }
}
