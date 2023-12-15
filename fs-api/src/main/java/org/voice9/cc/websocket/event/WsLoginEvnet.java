package org.voice9.cc.websocket.event;

import org.voice9.cc.websocket.event.base.WsBaseEvent;

import java.util.List;

/**
 * Create by caoliang on 2020/10/26
 * <p>
 * 坐席登录
 */
public class WsLoginEvnet extends WsBaseEvent {

    /**
     * 密码模式
     */
    private String passwd;

    /**
     * 坐席名称
     */
    private String agentName;

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


    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
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

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    @Override
    public String toString() {
        return "WsLoginEvnet{" +
                ", loginType='" + loginType + '\'' +
                ", workType='" + workType + '\'' +
                ", sip='" + sip + '\'' +
                '}';
    }
}
