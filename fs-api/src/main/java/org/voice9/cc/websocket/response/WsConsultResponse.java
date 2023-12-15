package org.voice9.cc.websocket.response;

/**
 * Created by caoliang on 2022/2/20
 */
public class WsConsultResponse {

    /**
     *
     */
    private Long callId;


    /**
     * 当前被咨询坐席
     */
    private String consultAgent;

    public Long getCallId() {
        return callId;
    }

    public void setCallId(Long callId) {
        this.callId = callId;
    }

    public String getConsultAgent() {
        return consultAgent;
    }

    public void setConsultAgent(String consultAgent) {
        this.consultAgent = consultAgent;
    }
}
