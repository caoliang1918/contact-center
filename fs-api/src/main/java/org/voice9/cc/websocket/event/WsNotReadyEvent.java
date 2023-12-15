package org.voice9.cc.websocket.event;

import org.voice9.cc.websocket.event.base.WsBaseEvent;

/**
 * Create by caoliang on 2020/11/1
 * <p>
 * 忙碌
 */
public class WsNotReadyEvent extends WsBaseEvent {

    /**
     *  忙碌类型码
     */
    private Integer notReadyCode;


    public Integer getNotReadyCode() {
        return notReadyCode;
    }

    public void setNotReadyCode(Integer notReadyCode) {
        this.notReadyCode = notReadyCode;
    }
}
