package org.zhongweixian.cc.websocket.event;

import org.zhongweixian.cc.websocket.event.base.WsBaseEvent;

/**
 * Create by caoliang on 2020/11/9
 * <p>
 * 坐席发送dtmf
 */
public class WsDtmfEvent extends WsBaseEvent {

    private Long callId;

    private String dtmf;

    public Long getCallId() {
        return callId;
    }

    public void setCallId(Long callId) {
        this.callId = callId;
    }

    public String getDtmf() {
        return dtmf;
    }

    public void setDtmf(String dtmf) {
        this.dtmf = dtmf;
    }
}
