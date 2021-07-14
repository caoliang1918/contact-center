package org.zhongweixian.cc.fs.event;

import com.alibaba.fastjson.annotation.JSONField;
import org.zhongweixian.cc.fs.event.base.FsBaseEvent;

/**
 * Created by caoliang on 2020/10/16
 * 振铃事件
 */
public class FsParkEvent extends FsBaseEvent {

    @JSONField(name = "variable_direction")
    private String direction;

    /**
     * 主叫号码
     */
    @JSONField(name = "variable_sip_from_user")
    private String caller;

    /**
     * 被叫号码
     */
    @JSONField(name = "Caller-Destination-Number")
    private String called;


    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getCalled() {
        return called;
    }

    public void setCalled(String called) {
        this.called = called;
    }

    public String getCaller() {
        return caller;
    }

    public void setCaller(String caller) {
        this.caller = caller;
    }

    @Override
    public String toString() {
        return "FsParkEvent{" +
                "direction='" + direction + '\'' +
                ", called='" + called + '\'' +
                ", caller='" + caller + '\'' +
                ", eventName='" + eventName + '\'' +
                ", context=" + context +
                ", coreUuid='" + coreUuid + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", timestamp=" + timestamp +
                ", channelName='" + channelName + '\'' +
                ", hostname='" + hostname + '\'' +
                '}';
    }
}
