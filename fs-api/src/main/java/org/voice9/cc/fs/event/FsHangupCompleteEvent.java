package org.voice9.cc.fs.event;

import com.alibaba.fastjson.annotation.JSONField;
import org.voice9.cc.fs.event.base.FsBaseEvent;

/**
 * Created by caoliang on 2020/10/27
 */
public class FsHangupCompleteEvent extends FsBaseEvent {

    /**
     * 主叫
     */
    @JSONField(name = "Caller-Caller-ID-Number")
    private String caller;

    /**
     * 被叫
     */
    @JSONField(name = "Caller-Destination-Number")
    private String called;

    /**
     * 挂机原因
     */
    @JSONField(name = "Hangup-Cause")
    private String hangupCause;

    /**
     * 接听标识
     */
    @JSONField(name = "Answer-State")
    private String answerState;


    /**
     * sip信令协议
     */
    @JSONField(name = "variable_sip_via_protocol")
    private String sipProtocol;


    /**
     * sip呼叫地址
     */
    @JSONField(name = "variable_channel_name")
    private String channelName;

    /**
     * sip状态
     */
    @JSONField(name = "variable_sip_term_status")
    private String sipStatus;

    @JSONField(name = "variable_local_media_ip")
    private String localMediaIp;

    @JSONField(name = "variable_sip_from_uri")
    private String fromUri;

    @JSONField(name = "variable_sip_to_uri")
    private String toUri;

    public String getCaller() {
        return caller;
    }

    public void setCaller(String caller) {
        this.caller = caller;
    }

    public String getCalled() {
        return called;
    }

    public void setCalled(String called) {
        this.called = called;
    }

    public String getHangupCause() {
        return hangupCause;
    }

    public void setHangupCause(String hangupCause) {
        this.hangupCause = hangupCause;
    }

    @Override
    public String getAnswerState() {
        return answerState;
    }

    @Override
    public void setAnswerState(String answerState) {
        this.answerState = answerState;
    }

    public String getSipProtocol() {
        return sipProtocol;
    }

    public void setSipProtocol(String sipProtocol) {
        this.sipProtocol = sipProtocol;
    }

    @Override
    public String getChannelName() {
        return channelName;
    }

    @Override
    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getSipStatus() {
        return sipStatus;
    }

    public void setSipStatus(String sipStatus) {
        this.sipStatus = sipStatus;
    }

    public String getLocalMediaIp() {
        return localMediaIp;
    }

    public void setLocalMediaIp(String localMediaIp) {
        this.localMediaIp = localMediaIp;
    }

    public String getFromUri() {
        return fromUri;
    }

    public void setFromUri(String fromUri) {
        this.fromUri = fromUri;
    }

    public String getToUri() {
        return toUri;
    }

    public void setToUri(String toUri) {
        this.toUri = toUri;
    }

    @Override
    public String toString() {
        return "FsHangupCompleteEvent{" +
                "caller='" + caller + '\'' +
                ", called='" + called + '\'' +
                ", hangupCause='" + hangupCause + '\'' +
                ", answerState='" + answerState + '\'' +
                ", sipProtocol='" + sipProtocol + '\'' +
                ", channelName='" + channelName + '\'' +
                ", sipStatus='" + sipStatus + '\'' +
                ", localMediaIp='" + localMediaIp + '\'' +
                ", fromUri='" + fromUri + '\'' +
                ", toUri='" + toUri + '\'' +
                ", eventName='" + eventName + '\'' +
                ", coreUuid='" + coreUuid + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
