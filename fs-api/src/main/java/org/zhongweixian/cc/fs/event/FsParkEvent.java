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

    /**
     * 是否挂机,OK
     */
    @JSONField(name = "variable_sip_hangup_phrase")
    private String hangup;

    /**
     * 当前状态
     */
    @JSONField(name = "Channel-Call-State")
    private String state;

    /**
     * 桥接之后的对端deviceId
     */
    @JSONField(name = "variable_last_bridge_to")
    private String lastBridgeTo;


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

    public String getHangup() {
        return hangup;
    }

    public void setHangup(String hangup) {
        this.hangup = hangup;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLastBridgeTo() {
        return lastBridgeTo;
    }

    public void setLastBridgeTo(String lastBridgeTo) {
        this.lastBridgeTo = lastBridgeTo;
    }

    @Override
    public String toString() {
        return "FsParkEvent{" +
                "direction='" + direction + '\'' +
                ", caller='" + caller + '\'' +
                ", called='" + called + '\'' +
                ", state='" + state + '\'' +
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
