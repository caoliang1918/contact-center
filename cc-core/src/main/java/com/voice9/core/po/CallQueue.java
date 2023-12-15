package com.voice9.core.po;

/**
 * Create by caoliang on 2020/11/10
 */
public class CallQueue implements Comparable<Long> {

    private Long id;

    private Long callId;

    public CallQueue(Long id, Long callId) {
        this.id = id;
        this.callId = callId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCallId() {
        return callId;
    }

    public void setCallId(Long callId) {
        this.callId = callId;
    }

    @Override
    public int compareTo(Long o) {
        return this.id.compareTo(o);
    }
}
