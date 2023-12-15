package org.voice9.cc.tcp.event;

import org.voice9.cc.tcp.event.base.SubBaseEvent;

/**
 * Created by caoliang on 2021/8/8.
 */
public class SubLoginEvent extends SubBaseEvent {


    private Integer stationType;

    public Integer getStationType() {
        return stationType;
    }

    public void setStationType(Integer stationType) {
        this.stationType = stationType;
    }
}
