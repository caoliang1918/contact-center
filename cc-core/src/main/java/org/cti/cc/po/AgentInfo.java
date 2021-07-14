package org.cti.cc.po;

import org.cti.cc.entity.Agent;
import org.cti.cc.entity.Skill;

import java.util.List;

/**
 * Create by caoliang on 2020/10/28
 */
public class AgentInfo extends Agent {

    /**
     * 登录服务地址
     */
    private String host;

    /**
     * 客户端地址
     */
    private String remoteAddress;

    /**
     * 所属技能组
     */
    private List<Long> groupIds;

    /**
     * 坐席sip
     */
    private List<String> sips;

    /**
     * 坐席技能
     */
    private List<Skill> skills;

    /**
     * 通话id
     */
    private Long callId;

    /**
     * 设备id
     */
    private String deviceId;

    /**
     *
     */
    private Long loginTime = 0L;

    /**
     *
     */
    private AgentState agentState;

    /**
     * 1：坐席sip号
     * 2：webrtc
     * 3：坐席手机号
     */
    private Integer loginType;

    /**
     *
     */
    private Integer workType;


    /**
     *
     */
    private Long stateTime = 0L;

    /**
     *
     */
    private AgentState beforeState;

    /**
     *
     */
    private Long beforeTime = 0L;

    /**
     *
     */
    private Long logoutTime = 0L;

    /**
     * 最大空闲时长
     */
    private Long maxFreeTime = 0L;

    /**
     * 累计空闲
     */
    private Long totalFreeTime = 0L;

    /**
     * 最大通话时长
     */
    private Long maxTalkTime = 0L;


    /**
     * 当日累计通话时长
     */
    private Long totalTalkTime = 0L;

    /**
     * 振铃次数
     */
    private Long totalRingTime = 0L;

    /**
     * 当日累计接听次数
     */
    private Long totalAnswerTime = 0L;

    /**
     * 隐藏客户号码(0:不隐藏;1:隐藏)
     */
    private Integer hiddenCustomer;

    /**
     * 话单通知地址
     */
    private String cdrNotifyUrl;

    /**
     * 坐席状态通知地址
     */
    private String stateNotifyUrl;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getRemoteAddress() {
        return remoteAddress;
    }

    public void setRemoteAddress(String remoteAddress) {
        this.remoteAddress = remoteAddress;
    }

    public List<Long> getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(List<Long> groupIds) {
        this.groupIds = groupIds;
    }

    public List<String> getSips() {
        return sips;
    }

    public void setSips(List<String> sips) {
        this.sips = sips;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public Long getCallId() {
        return callId;
    }

    public void setCallId(Long callId) {
        this.callId = callId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Long loginTime) {
        this.loginTime = loginTime;
    }

    public AgentState getAgentState() {
        return agentState;
    }

    public void setAgentState(AgentState agentState) {
        this.agentState = agentState;
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

    public Long getStateTime() {
        return stateTime;
    }

    public void setStateTime(Long stateTime) {
        this.stateTime = stateTime;
    }

    public AgentState getBeforeState() {
        return beforeState;
    }

    public void setBeforeState(AgentState beforeState) {
        this.beforeState = beforeState;
    }

    public Long getBeforeTime() {
        return beforeTime;
    }

    public void setBeforeTime(Long beforeTime) {
        this.beforeTime = beforeTime;
    }

    public Long getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(Long logoutTime) {
        this.logoutTime = logoutTime;
    }

    public Long getMaxFreeTime() {
        return maxFreeTime;
    }

    public void setMaxFreeTime(Long maxFreeTime) {
        this.maxFreeTime = maxFreeTime;
    }

    public Long getTotalFreeTime() {
        return totalFreeTime;
    }

    public void setTotalFreeTime(Long totalFreeTime) {
        this.totalFreeTime = totalFreeTime;
    }

    public Long getMaxTalkTime() {
        return maxTalkTime;
    }

    public void setMaxTalkTime(Long maxTalkTime) {
        this.maxTalkTime = maxTalkTime;
    }

    public Long getTotalTalkTime() {
        return totalTalkTime;
    }

    public void setTotalTalkTime(Long totalTalkTime) {
        this.totalTalkTime = totalTalkTime;
    }

    public Long getTotalRingTime() {
        return totalRingTime;
    }

    public void setTotalRingTime(Long totalRingTime) {
        this.totalRingTime = totalRingTime;
    }

    public Long getTotalAnswerTime() {
        return totalAnswerTime;
    }

    public void setTotalAnswerTime(Long totalAnswerTime) {
        this.totalAnswerTime = totalAnswerTime;
    }

    public Integer getHiddenCustomer() {
        return hiddenCustomer;
    }

    public void setHiddenCustomer(Integer hiddenCustomer) {
        this.hiddenCustomer = hiddenCustomer;
    }

    public String getCdrNotifyUrl() {
        return cdrNotifyUrl;
    }

    public void setCdrNotifyUrl(String cdrNotifyUrl) {
        this.cdrNotifyUrl = cdrNotifyUrl;
    }

    public String getStateNotifyUrl() {
        return stateNotifyUrl;
    }

    public void setStateNotifyUrl(String stateNotifyUrl) {
        this.stateNotifyUrl = stateNotifyUrl;
    }

    /**
     * 获取坐席被叫号码
     *
     * @return
     */
    public String getCalled() {
        if (loginType == null) {
            return null;
        }
        if (loginType == 3) {
            return this.getSipPhone();
        }
        return getSips().get(0);
    }

    @Override
    public String toString() {
        return "AgentInfo{" +
                "host='" + host + '\'' +
                ", groupIds=" + groupIds +
                ", sips=" + sips +
                ", skills=" + skills +
                ", callId=" + callId +
                ", deviceId='" + deviceId + '\'' +
                ", loginTime=" + loginTime +
                ", agentState=" + agentState +
                ", loginType=" + loginType +
                ", workType=" + workType +
                ", stateTime=" + stateTime +
                ", beforeState=" + beforeState +
                ", beforeTime=" + beforeTime +
                ", logoutTime=" + logoutTime +
                ", maxFreeTime=" + maxFreeTime +
                ", totalFreeTime=" + totalFreeTime +
                ", maxTalkTime=" + maxTalkTime +
                ", totalTalkTime=" + totalTalkTime +
                ", totalRingTime=" + totalRingTime +
                ", totalAnswerTime=" + totalAnswerTime +
                '}';
    }
}
