package org.zhongweixian.cc.websocket.event;

import org.zhongweixian.cc.websocket.event.base.WsBaseEvent;

/**
 * Create by caoliang on 2020/11/9
 * <p>
 * 班长监听
 */
public class WsListenEvent extends WsBaseEvent {

    /**
     * 被监听坐席
     */
    private String monitorAgent;

    public String getMonitorAgent() {
        return monitorAgent;
    }

    public void setMonitorAgent(String monitorAgent) {
        this.monitorAgent = monitorAgent;
    }
}
