package org.voice9.cc.entity;

import com.voice9.core.po.AgentState;

import java.util.Set;

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

    /**
     * 坐席主技能组
     */
    private Long groupId;

    /**
     * 坐席所在的技能组
     */
    private Set<Long> groupIds;


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

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Set<Long> getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(Set<Long> groupIds) {
        this.groupIds = groupIds;
    }
}
