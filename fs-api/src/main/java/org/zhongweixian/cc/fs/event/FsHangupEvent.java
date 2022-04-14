package org.zhongweixian.cc.fs.event;

import com.alibaba.fastjson.annotation.JSONField;
import org.zhongweixian.cc.fs.event.base.FsBaseEvent;

/**
 * Created by caoliang on 2020/8/31
 */
public class FsHangupEvent extends FsBaseEvent {

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
     * sip信令协议
     */
    @JSONField(name = "variable_sip_via_protocol")
    private String sipProtocol;

    @JSONField(name = "variable_rtp_use_codec_name")
    private String rtpUseCodec;

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


    public String getSipProtocol() {
        return sipProtocol;
    }

    public void setSipProtocol(String sipProtocol) {
        this.sipProtocol = sipProtocol;
    }

    public String getRtpUseCodec() {
        return rtpUseCodec;
    }

    public void setRtpUseCodec(String rtpUseCodec) {
        this.rtpUseCodec = rtpUseCodec;
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
        return "FsHangupEvent{" +
                "caller='" + caller + '\'' +
                ", called='" + called + '\'' +
                ", hangupCause='" + hangupCause + '\'' +
                ", sipProtocol='" + sipProtocol + '\'' +
                ", rtpUseCodec='" + rtpUseCodec + '\'' +
                ", sipStatus='" + sipStatus + '\'' +
                ", localMediaIp='" + localMediaIp + '\'' +
                ", fromUri='" + fromUri + '\'' +
                ", toUri='" + toUri + '\'' +
                ", eventName='" + eventName + '\'' +
                ", coreUuid='" + coreUuid + '\'' +
                ", deviceId='" + deviceId + '\'' +
                '}';
    }
}
