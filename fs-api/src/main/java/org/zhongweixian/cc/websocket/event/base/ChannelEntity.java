package org.zhongweixian.cc.websocket.event.base;

import io.netty.channel.Channel;

/**
 * Created by caoliang on 2020/11/5
 */
public class ChannelEntity {
    private Channel channel;

    private String client;

    private boolean authorization;

    private Long cts;

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public boolean isAuthorization() {
        return authorization;
    }

    public void setAuthorization(boolean authorization) {
        this.authorization = authorization;
    }

    public Long getCts() {
        return cts;
    }

    public void setCts(Long cts) {
        this.cts = cts;
    }
}
