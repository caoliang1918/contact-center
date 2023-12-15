package org.voice9.cc.websocket.event;

import org.voice9.cc.websocket.event.base.WsBaseEvent;

/**
 * Create by caoliang on 2020/11/9
 */
public class WsAnswerEvent extends WsBaseEvent {

    private Long callId;

    private Boolean answer;

    public Long getCallId() {
        return callId;
    }

    public void setCallId(Long callId) {
        this.callId = callId;
    }

    public Boolean getAnswer() {
        return answer;
    }

    public void setAnswer(Boolean answer) {
        this.answer = answer;
    }
}
