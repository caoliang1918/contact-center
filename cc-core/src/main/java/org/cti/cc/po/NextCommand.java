package org.cti.cc.po;

import org.cti.cc.enums.NextType;

/**
 * Created by caoliang on 2020/12/21
 */
public class NextCommand {

    /**
     * 记录执行设备
     */
    private String deviceId;

    /**
     * 下一步执行命令
     */
    private NextType nextType;

    /**
     * 执行参数
     */
    private String nextValue;



    public NextCommand(String deviceId, NextType nextType, String nextValue) {
        this.deviceId = deviceId;
        this.nextType = nextType;
        this.nextValue = nextValue;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public NextType getNextType() {
        return nextType;
    }

    public void setNextType(NextType nextType) {
        this.nextType = nextType;
    }

    public String getNextValue() {
        return nextValue;
    }

    public void setNextValue(String nextValue) {
        this.nextValue = nextValue;
    }
}
