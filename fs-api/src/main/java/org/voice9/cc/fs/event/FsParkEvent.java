package org.voice9.cc.fs.event;

import com.alibaba.fastjson.annotation.JSONField;
import org.voice9.cc.fs.event.base.FsBaseEvent;

/**
 * Created by caoliang on 2020/10/16
 * 振铃事件
 */
public class FsParkEvent extends FsBaseEvent {

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

    /**
     * 硬话机发起呼叫时携带
     */
    @JSONField(name = "variable_sip_via_port")
    private String sipPort;

    @JSONField(name = "variable_sip_contact_port")
    private String sipContactPort;



    @JSONField(name = "variable_sip_contact_uri")
    private String contactUri;

    /**
     * sip信令协议
     */
    @JSONField(name = "variable_sip_via_protocol")
    private String sipProtocol;


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

    public String getSipPort() {
        return sipPort;
    }

    public void setSipPort(String sipPort) {
        this.sipPort = sipPort;
    }

    public String getContactUri() {
        return contactUri;
    }

    public void setContactUri(String contactUri) {
        this.contactUri = contactUri;
    }

    public String getSipProtocol() {
        return sipProtocol;
    }

    public void setSipProtocol(String sipProtocol) {
        this.sipProtocol = sipProtocol;
    }

    public String getSipContactPort() {
        return sipContactPort;
    }

    public void setSipContactPort(String sipContactPort) {
        this.sipContactPort = sipContactPort;
    }

    @Override
    public String toString() {
        return "FsParkEvent{" +
                "caller='" + caller + '\'' +
                ", called='" + called + '\'' +
                ", hangup='" + hangup + '\'' +
                ", state='" + state + '\'' +
                ", lastBridgeTo='" + lastBridgeTo + '\'' +
                ", sipPort='" + sipPort + '\'' +
                ", sipContactPort='" + sipContactPort + '\'' +
                ", contactUri='" + contactUri + '\'' +
                ", sipProtocol='" + sipProtocol + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", localAddress='" + localAddress + '\'' +
                ", remoteAddress='" + remoteAddress + '\'' +
                ", direction='" + direction + '\'' +
                '}';
    }
}
