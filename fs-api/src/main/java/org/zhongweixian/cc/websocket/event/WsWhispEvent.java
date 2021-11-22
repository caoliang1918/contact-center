package org.zhongweixian.cc.websocket.event;

import org.zhongweixian.cc.websocket.event.base.WsBaseEvent;

/**
 * Created by caoliang on 2021/11/19
 * <p>
 * 班长耳语
 */
public class WsWhispEvent extends WsBaseEvent {

    /**
     *
     */
    private Long callId;

    /**
     *
     */
    private String agent;

    public Long getCallId() {
        return callId;
    }

    public void setCallId(Long callId) {
        this.callId = callId;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }
}
