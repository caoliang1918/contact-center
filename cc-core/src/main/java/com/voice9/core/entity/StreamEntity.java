package com.voice9.core.entity;

/**
 * Created by caoliang on 2021/12/6
 */
public class StreamEntity {

    /**
     * 通话的唯一标识
     */
    private Long callId;

    /**
     * 主叫地址
     */
    private String callerAddress;

    /**
     * 被叫地址
     */
    private String calledAddress;

    /**
     *
     */
    private String device1;

    /**
     *
     */
    private String device2;

    public Long getCallId() {
        return callId;
    }

    public void setCallId(Long callId) {
        this.callId = callId;
    }

    public String getCallerAddress() {
        return callerAddress;
    }

    public void setCallerAddress(String callerAddress) {
        this.callerAddress = callerAddress;
    }

    public String getCalledAddress() {
        return calledAddress;
    }

    public void setCalledAddress(String calledAddress) {
        this.calledAddress = calledAddress;
    }

    public String getDevice1() {
        return device1;
    }

    public void setDevice1(String device1) {
        this.device1 = device1;
    }

    public String getDevice2() {
        return device2;
    }

    public void setDevice2(String device2) {
        this.device2 = device2;
    }
}
