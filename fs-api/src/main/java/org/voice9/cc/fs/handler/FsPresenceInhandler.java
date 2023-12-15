package org.voice9.cc.fs.handler;

import com.voice9.core.constant.FsConstant;
import com.voice9.core.po.AgentInfo;
import com.voice9.core.po.CallInfo;
import org.springframework.stereotype.Component;
import org.voice9.cc.configration.HandlerType;
import org.voice9.cc.fs.event.FsPresenceInEvent;
import org.voice9.cc.fs.handler.base.BaseEventHandler;
import org.voice9.cc.websocket.response.WsConferenceResponse;

/**
 * Created by caoliang on 2022/2/26
 */
@Component
@HandlerType("PRESENCE_IN")
public class FsPresenceInhandler extends BaseEventHandler<FsPresenceInEvent> {

    @Override
    public void handleEvent(FsPresenceInEvent event) {
        if (!FsConstant.CONFIRMED.equals(event.getAnswerState()) || !FsConstant.CONFERENCE_SEND_PRESENCE.equals(event.getEventCallFunction())) {
            return;
        }
        CallInfo callInfo = cacheService.getCallInfo(event.getDeviceId());
        if (callInfo == null) {
            return;
        }
        WsConferenceResponse response = new WsConferenceResponse();
        callInfo.getDeviceInfoMap().forEach((k, v) -> {
            if (v.getDeviceType() == 1) {
                AgentInfo agentInfo = cacheService.getAgentInfo(v.getAgentKey());
                // sendAgentStateMessage(agentInfo, new WsResponseEntity<WsConferenceResponse>(AgentState.TALKING.name(), agentInfo.getAgentKey(), response));
            }

        });

    }
}
