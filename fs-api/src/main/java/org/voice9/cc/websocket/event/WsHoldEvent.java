package org.voice9.cc.websocket.event;

import org.voice9.cc.websocket.event.base.WsBaseEvent;

/**
 * Create by caoliang on 2020/12/7
 * <p>
 * 呼叫保持
 */
public class WsHoldEvent extends WsBaseEvent {

    private Long callId;

    public Long getCallId() {
        return callId;
    }

    public void setCallId(Long callId) {
        this.callId = callId;
    }
}
