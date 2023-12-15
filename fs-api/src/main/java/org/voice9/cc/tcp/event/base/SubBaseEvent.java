package org.voice9.cc.tcp.event.base;

import com.alibaba.fastjson.JSONObject;
import io.netty.channel.Channel;


/**
 * Create by caoliang on 2020/10/29
 */
public class SubBaseEvent {

    private String domain;

    private Channel channel;

    private Long timestamp;

    private Long callId;

    private JSONObject userData;

    private JSONObject followData;

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Long getCallId() {
        return callId;
    }

    public void setCallId(Long callId) {
        this.callId = callId;
    }

    public JSONObject getUserData() {
        return userData;
    }

    public void setUserData(JSONObject userData) {
        this.userData = userData;
    }

    public JSONObject getFollowData() {
        return followData;
    }

    public void setFollowData(JSONObject followData) {
        this.followData = followData;
    }
}
