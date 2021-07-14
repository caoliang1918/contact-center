package org.zhongweixian.cc.websocket.event;

import org.zhongweixian.cc.websocket.event.base.WsBaseEvent;

/**
 * Create by caoliang on 2020/11/9
 * <p>
 * 强制坐席退出系统
 */
public class WsForceLogoutEvent extends WsBaseEvent {

    private String agentkey;

    public String getAgentkey() {
        return agentkey;
    }

    public void setAgentkey(String agentkey) {
        this.agentkey = agentkey;
    }
}
