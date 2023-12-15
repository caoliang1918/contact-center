package org.voice9.cc.fs.event.base;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.voice9.cc.fs.esl.internal.Context;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by caoliang on 2020/8/29
 */
public class FsBaseEvent {

    @JSONField(name = "Event-Name")
    protected String eventName;

    @JsonIgnore
    protected Context context;

    @JSONField(name = "Core-UUID")
    protected String coreUuid;

    @JSONField(name = "Unique-ID")
    protected String deviceId;

    @JSONField(name = "Event-Date-Timestamp")
    protected Long timestamp;

    @JSONField(name = "Answer-State")
    private String answerState;

    @JSONField(name = "Caller-Channel-Name")
    protected String channelName;

    protected String localAddress;

    protected String remoteAddress;

    @JSONField(name = "variable_sofia_profile_name")
    protected String profile;

    @JSONField(name = "Call-Direction")
    protected String direction;

    protected Map<String, String> map = new HashMap<>();

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getCoreUuid() {
        return coreUuid;
    }

    public void setCoreUuid(String coreUuid) {
        this.coreUuid = coreUuid;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getAnswerState() {
        return answerState;
    }

    public void setAnswerState(String answerState) {
        this.answerState = answerState;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getLocalAddress() {
        return localAddress;
    }

    public void setLocalAddress(String localAddress) {
        this.localAddress = localAddress;
    }

    public String getRemoteAddress() {
        return remoteAddress;
    }

    public void setRemoteAddress(String remoteAddress) {
        this.remoteAddress = remoteAddress;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        return "BaseEvent{" +
                "eventName='" + eventName + '\'' +
                ", context=" + context +
                ", coreUuid='" + coreUuid + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", timestamp=" + timestamp +
                ", answerState='" + answerState + '\'' +
                ", channelName='" + channelName + '\'' +
                ", remoteAddress='" + remoteAddress + '\'' +
                ", map=" + map +
                '}';
    }
}
