package org.cti.cc.po;

import org.cti.cc.enums.NextType;

/**
 * Created by caoliang on 2020/12/21
 */
public class NextCommand {

    private Long callId;

    private NextType nextType;

    private String nextValue;

    public NextCommand(NextType nextType) {
        this.nextType = nextType;
    }

    public NextCommand(NextType nextType, String nextValue) {
        this.nextType = nextType;
        this.nextValue = nextValue;
    }

    public Long getCallId() {
        return callId;
    }

    public void setCallId(Long callId) {
        this.callId = callId;
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
