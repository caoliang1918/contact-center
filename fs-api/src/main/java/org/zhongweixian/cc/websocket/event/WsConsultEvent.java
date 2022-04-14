package org.zhongweixian.cc.websocket.event;

import org.zhongweixian.cc.websocket.event.base.WsBaseEvent;

/**
 * Create by caoliang on 2020/11/9
 * <p>
 * 咨询
 */
public class WsConsultEvent extends WsBaseEvent {

    /**
     * 咨询的callId
     */
    private Long callId;


    /**
     * 咨询类型  1:技能组, 2:坐席 3:ivr ,4:外线号码
     */
    private Integer type;


    /**
     * 咨询对象
     */
    private String consultValue;

    public Long getCallId() {
        return callId;
    }

    public void setCallId(Long callId) {
        this.callId = callId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getConsultValue() {
        return consultValue;
    }

    public void setConsultValue(String consultValue) {
        this.consultValue = consultValue;
    }

    @Override
    public String toString() {
        return "WsConsultEvent{" +
                "callId=" + callId +
                ", type=" + type +
                ", consultValue='" + consultValue + '\'' +
                ", agentKey='" + agentKey + '\'' +
                ", cmd='" + cmd + '\'' +
                '}';
    }
}
