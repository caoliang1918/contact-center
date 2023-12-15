package com.voice9.core.po;

import com.voice9.core.enums.NextType;

import java.io.Serializable;

/**
 * Created by caoliang on 2020/12/21
 */
public class NextCommand implements Serializable {

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

    public NextCommand(  NextType nextType ) {
        this.nextType = nextType;
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

    @Override
    public String toString() {
        return "NextCommand{" +
                "deviceId='" + deviceId + '\'' +
                ", nextType=" + nextType +
                ", nextValue='" + nextValue + '\'' +
                '}';
    }
}
