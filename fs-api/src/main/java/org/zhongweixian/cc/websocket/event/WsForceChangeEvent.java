package org.zhongweixian.cc.websocket.event;

import org.cti.cc.po.AgentState;
import org.zhongweixian.cc.websocket.event.base.WsBaseEvent;

/**
 * Create by caoliang on 2020/11/9
 * <p>
 * 强制坐席改变状态
 */
public class WsForceChangeEvent extends WsBaseEvent {

    private String agentkey;

    private AgentState agentState;

    public String getAgentkey() {
        return agentkey;
    }

    public void setAgentkey(String agentkey) {
        this.agentkey = agentkey;
    }
}
