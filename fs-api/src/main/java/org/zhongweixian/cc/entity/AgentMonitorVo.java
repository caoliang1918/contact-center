package org.zhongweixian.cc.entity;

import org.cti.cc.po.AgentState;

/**
 * Created by caoliang on 2020/11/6
 */
public class AgentMonitorVo {

    /**
     * 坐席
     */
    private String agentKey;

    /**
     * 当前状态
     */
    private AgentState agentState;

    /**
     * 当前状态时间
     */
    private Long cts;

    /**
     * 当前通话id
     */
    private Long callId;

    public String getAgentKey() {
        return agentKey;
    }

    public void setAgentKey(String agentKey) {
        this.agentKey = agentKey;
    }

    public AgentState getAgentState() {
        return agentState;
    }

    public void setAgentState(AgentState agentState) {
        this.agentState = agentState;
    }

    public Long getCts() {
        return cts;
    }

    public void setCts(Long cts) {
        this.cts = cts;
    }

    public Long getCallId() {
        return callId;
    }

    public void setCallId(Long callId) {
        this.callId = callId;
    }
}
