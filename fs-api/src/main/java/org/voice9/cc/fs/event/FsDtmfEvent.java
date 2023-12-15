package org.voice9.cc.fs.event;

import com.alibaba.fastjson.annotation.JSONField;
import org.voice9.cc.fs.event.base.FsBaseEvent;

/**
 * Created by caoliang on 2020/10/31
 */
public class FsDtmfEvent extends FsBaseEvent {

    @JSONField(name = "Caller-Username")
    private String caller;

    @JSONField(name = "DTMF-Source")
    private String dtmfSource;

    @JSONField(name = "DTMF-Digit")
    private String dtmf;

    @JSONField(name = "DTMF-Duration")
    private String duration;


    public String getCaller() {
        return caller;
    }

    public void setCaller(String caller) {
        this.caller = caller;
    }

    public String getDtmfSource() {
        return dtmfSource;
    }

    public void setDtmfSource(String dtmfSource) {
        this.dtmfSource = dtmfSource;
    }

    public String getDtmf() {
        return dtmf;
    }

    public void setDtmf(String dtmf) {
        this.dtmf = dtmf;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "FsDtmfEvent{" +
                "caller='" + caller + '\'' +
                ", dtmfSource='" + dtmfSource + '\'' +
                ", dtmf='" + dtmf + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }
}
