package org.zhongweixian.cc.websocket.event.base;

import io.netty.channel.Channel;

/**
 * Create by caoliang on 2020/10/26
 */
public class WsBaseEvent {

    private String agentKey;

    private String cmd;

    private Long time;

    private Channel channel;

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
