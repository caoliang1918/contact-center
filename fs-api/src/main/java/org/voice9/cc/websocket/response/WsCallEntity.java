package org.voice9.cc.websocket.response;

import com.voice9.core.enums.CallType;
import com.voice9.core.enums.Direction;
import com.voice9.core.po.AgentState;

/**
 * Created by caoliang on 2020/11/15
 *
 * 呼入来电、外呼主被叫振铃
 */
public class WsCallEntity {
    /**
     *
     */
    private Long callId;

    /**
     *
     */
    private Direction direction;

    /**
     *
     */
    private CallType callType;

    /**
     *
     */
    private String caller;

    /**
     *
     */
    private String called;

    /**
     *
     */
    private AgentState agentState;


    /**
     *
     */
    private Long groupId;

    public Long getCallId() {
        return callId;
    }

    public void setCallId(Long callId) {
        this.callId = callId;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public CallType getCallType() {
        return callType;
    }

    public void setCallType(CallType callType) {
        this.callType = callType;
    }

    public String getCaller() {
        return caller;
    }

    public void setCaller(String caller) {
        this.caller = caller;
    }

    public String getCalled() {
        return called;
    }

    public void setCalled(String called) {
        this.called = called;
    }

    public AgentState getAgentState() {
        return agentState;
    }

    public void setAgentState(AgentState agentState) {
        this.agentState = agentState;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    @Override
    public String toString() {
        return "WsCallRingEntity{" +
                "callId=" + callId +
                ", diration=" + direction +
                ", callType=" + callType +
                ", caller='" + caller + '\'' +
                ", called='" + called + '\'' +
                ", agentState=" + agentState +
                ", groupId=" + groupId +
                '}';
    }
}
