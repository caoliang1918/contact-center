package org.voice9.cc.websocket.response;

import com.voice9.core.enums.CallType;
import com.voice9.core.po.AgentState;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caoliang on 2022/2/20
 */
public class WsConferenceResponse {

    /**
     * 通话标识
     */
    private Long callId;


    private CallType callType;


    private AgentState agentState;

    /**
     * 会议号
     */
    private String conference;

    /**
     * 会议中的被咨询者(这里不包含客户)
     */
    private List<String>  conferenceList = new ArrayList<>();

    public Long getCallId() {
        return callId;
    }

    public void setCallId(Long callId) {
        this.callId = callId;
    }

    public String getConference() {
        return conference;
    }

    public void setConference(String conference) {
        this.conference = conference;
    }

    public List<String> getConferenceList() {
        return conferenceList;
    }

    public void setConferenceList(List<String> conferenceList) {
        this.conferenceList = conferenceList;
    }

    public CallType getCallType() {
        return callType;
    }

    public void setCallType(CallType callType) {
        this.callType = callType;
    }

    public AgentState getAgentState() {
        return agentState;
    }

    public void setAgentState(AgentState agentState) {
        this.agentState = agentState;
    }
}
