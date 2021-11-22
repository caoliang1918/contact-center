package org.zhongweixian.cc.websocket.event;

import org.zhongweixian.cc.websocket.event.base.WsBaseEvent;

/**
 * Created by caoliang on 2021/11/19
 * <p>
 * 强插
 */
public class WsInsertEvent extends WsBaseEvent {

    /**
     * 强插的callId;
     */
    private Long callId;

    /**
     * 被强插坐席的设备id
     */
    private String deviceId;

    /**
     * 强插的坐席
     */
    private String agent;

    public Long getCallId() {
        return callId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
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
