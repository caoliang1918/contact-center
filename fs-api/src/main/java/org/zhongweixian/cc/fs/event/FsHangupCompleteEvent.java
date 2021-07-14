package org.zhongweixian.cc.fs.event;

import com.alibaba.fastjson.annotation.JSONField;
import org.zhongweixian.cc.fs.event.base.FsBaseEvent;

/**
 * Created by caoliang on 2020/10/27
 */
public class FsHangupCompleteEvent extends FsBaseEvent {

    /**
     * 另外一侧
     */
    @JSONField(name = "Other-Leg-Unique-ID")
    private String otherDeviceId;

    /**
     * 挂机原因
     */
    @JSONField(name = "Hangup-Cause")
    private String cause;

    /**
     * 挂机方向
     */
    @JSONField(name = "variable_sip_hangup_disposition")
    private String hangup;

    /**
     * 显号(不一定用到)
     */
    @JSONField(name = "variable_sip_from_display")
    private String display;

    public String getOtherDeviceId() {
        return otherDeviceId;
    }

    public void setOtherDeviceId(String otherDeviceId) {
        this.otherDeviceId = otherDeviceId;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getHangup() {
        return hangup;
    }

    public void setHangup(String hangup) {
        this.hangup = hangup;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    @Override
    public String toString() {
        return "FsHangupCompleteEvent{" +
                "otherDeviceId='" + otherDeviceId + '\'' +
                ", cause='" + cause + '\'' +
                ", hangup='" + hangup + '\'' +
                ", display='" + display + '\'' +
                ", eventName='" + eventName + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", timestamp=" + timestamp +
                ", hostname='" + hostname + '\'' +
                '}';
    }
}
