package org.zhongweixian.cc.websocket.event;

import org.zhongweixian.cc.websocket.event.base.WsBaseEvent;

import java.util.List;

/**
 * Created by caoliang on 2022/2/10
 *
 * 坐席重连
 */
public class WsReconnectEvent extends WsBaseEvent {

    /**
     * 通话中的callid
     */
    private Long callId;


    /**
     * 1：坐席sip号
     * 2：webrtc
     * 3：坐席手机号
     */
    private Integer loginType;

    /**
     * 预测、手动
     */
    private Integer workType;

    /**
     * 坐席端或者sdk传过来的sip或者手机号
     */
    private String sip;

    /**
     * 坐席签入技能组
     */
    private List<Long> groupIds;

    public Long getCallId() {
        return callId;
    }

    public void setCallId(Long callId) {
        this.callId = callId;
    }

    public Integer getLoginType() {
        return loginType;
    }

    public void setLoginType(Integer loginType) {
        this.loginType = loginType;
    }

    public Integer getWorkType() {
        return workType;
    }

    public void setWorkType(Integer workType) {
        this.workType = workType;
    }

    public String getSip() {
        return sip;
    }

    public void setSip(String sip) {
        this.sip = sip;
    }

    public List<Long> getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(List<Long> groupIds) {
        this.groupIds = groupIds;
    }

    @Override
    public String toString() {
        return "WsReconnectEvent{" +
                "callId=" + callId +
                ", loginType=" + loginType +
                ", workType=" + workType +
                ", sip='" + sip + '\'' +
                ", groupIds=" + groupIds +
                ", agentKey='" + agentKey + '\'' +
                ", cmd='" + cmd + '\'' +
                ", time=" + time +
                '}';
    }
}
