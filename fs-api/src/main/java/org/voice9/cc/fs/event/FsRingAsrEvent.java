package org.voice9.cc.fs.event;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by caoliang on 2020/10/20
 */
public class FsRingAsrEvent extends FsBridgeEvent {

    @JSONField(name = "RING-CAUSE")
    private String ringCause;

    public String getRingCause() {
        return ringCause;
    }

    public void setRingCause(String ringCause) {
        this.ringCause = ringCause;
    }
}
