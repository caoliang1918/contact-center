package org.zhongweixian.cc.websocket.event;

import org.zhongweixian.cc.websocket.event.base.WsBaseEvent;

/**
 * Created by caoliang on 2021/11/19
 * <p>
 * 强插
 */
public class WsInsertEvent extends WsBaseEvent {


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
