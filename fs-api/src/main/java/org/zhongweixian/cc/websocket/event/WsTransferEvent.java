package org.zhongweixian.cc.websocket.event;

import org.zhongweixian.cc.websocket.event.base.WsBaseEvent;

/**
 * Create by caoliang on 2020/11/9
 * <p>
 * 转接
 */
public class WsTransferEvent extends WsBaseEvent {

    /**
     * 转接类型 1:坐席,2:技能组,3:ivr,4:外线
     */
    private int transferType;

    /**
     *
     */
    private String transferValue;

    /**
     *
     */
    private String deviceId;

    public int getTransferType() {
        return transferType;
    }

    public void setTransferType(int transferType) {
        this.transferType = transferType;
    }

    public String getTransferValue() {
        return transferValue;
    }

    public void setTransferValue(String transferValue) {
        this.transferValue = transferValue;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}
