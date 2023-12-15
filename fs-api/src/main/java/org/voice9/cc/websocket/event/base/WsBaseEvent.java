package org.voice9.cc.websocket.event.base;

import io.netty.channel.Channel;

/**
 * Create by caoliang on 2020/10/26
 */
public class WsBaseEvent {

    protected String agentKey;

    protected String cmd;

    protected Long time;

    protected Channel channel;

    public String getAgentKey() {
        return agentKey;
    }

    public void setAgentKey(String agentKey) {
        this.agentKey = agentKey;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    @Override
    public String toString() {
        return "WsBaseEvent{" +
                "agentKey='" + agentKey + '\'' +
                ", cmd='" + cmd + '\'' +
                ", time=" + time +
                ", channel=" + channel +
                '}';
    }
}
