package org.voice9.cc.fs.event;

import com.alibaba.fastjson2.annotation.JSONField;
import org.voice9.cc.fs.event.base.FsBaseEvent;

/**
 * Created by caoliang on 2024/6/24
 */
public class FsCustomEvent extends FsBaseEvent {

    @JSONField(name = "Action")
    private String action;

    @JSONField(name = "Event-Subclass")
    private String subclass;

    @JSONField(name = "username")
    private String username;


    @JSONField(name = "Conference-Name")
    private String conferenceId;

    @JSONField(name = "Member-ID")
    private String memberId;


    @JSONField(name = "contact")
    private String contact;


    @JSONField(name = "profile-name")
    private String profileName;

    @JSONField(name = "auth-result")
    private String authResult;

    @JSONField(name = "from-user")
    private String fromUser;

    @JSONField(name = "network-ip")
    private String sipIp;

    /**
     * 注册地址
     */
    @JSONField(name = "realm")
    private String realm;

    @JSONField(name = "call-id")
    private String callid;

    @JSONField(name = "network-port")
    private String networkPort;

    @JSONField(name = "status")
    private String status;

    @JSONField(name = "expires")
    private String expires;


    public String getSubclass() {
        return subclass;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setSubclass(String subclass) {
        this.subclass = subclass;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getConferenceId() {
        return conferenceId;
    }

    public void setConferenceId(String conferenceId) {
        this.conferenceId = conferenceId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getAuthResult() {
        return authResult;
    }

    public void setAuthResult(String authResult) {
        this.authResult = authResult;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public String getSipIp() {
        return sipIp;
    }

    public void setSipIp(String sipIp) {
        this.sipIp = sipIp;
    }

    public String getRealm() {
        return realm;
    }

    public void setRealm(String realm) {
        this.realm = realm;
    }

    public String getCallid() {
        return callid;
    }

    public void setCallid(String callid) {
        this.callid = callid;
    }

    public String getNetworkPort() {
        return networkPort;
    }

    public void setNetworkPort(String networkPort) {
        this.networkPort = networkPort;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getExpires() {
        return expires;
    }

    public void setExpires(String expires) {
        this.expires = expires;
    }

    @Override
    public String toString() {
        return "FsCustomEvent{" +
                "action='" + action + '\'' +
                ", subclass='" + subclass + '\'' +
                ", username='" + username + '\'' +
                ", conferenceId='" + conferenceId + '\'' +
                ", memberId='" + memberId + '\'' +
                ", eventName='" + eventName + '\'' +
                ", deviceId='" + deviceId + '\'' +
                '}';
    }
}