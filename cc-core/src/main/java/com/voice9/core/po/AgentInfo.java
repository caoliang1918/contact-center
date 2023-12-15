package com.voice9.core.po;

import com.voice9.core.entity.Agent;
import com.voice9.core.entity.SkillAgent;
import com.voice9.core.vo.AgentPreset;

import java.util.List;

/**
 * Create by caoliang on 2020/10/28
 */
public class AgentInfo extends Agent {


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
    private List<SkillAgent> skillAgents;

    /**
     * 通话id
     */
    private Long callId;

    /**
     * 设备id
     */
    private String deviceId;

    /**
     *  被咨询设备
     */
    private String consultDeviceId;

    /**
     * 登录时间(秒)
     */
    private Long loginTime = 0L;


    /**
     * 1：坐席sip号
     * 2：webrtc
     * 3：坐席手机号
     */
    private Integer loginType;

    /**
     * 1:普通
     * 2:预测
     */
    private Integer workType;

    /**
     * 当前状态
     */
    private AgentState agentState;

    /**
     * 当天状态时间(秒)
     */
    private Long stateTime = 0L;

    /**
     * 坐席状态预设
     */
    private AgentPreset agentPreset;

    /**
     * 上一次状态
     */
    private AgentState beforeState;

    /**
     * 上一次状态时间(秒)
     */
    private Long beforeTime = 0L;

    /**
     * 下线时间(秒)
     */
    private Long logoutTime = 0L;

    /**
     * 坐席最近的一次服务时间,电话则是振铃时间(秒)
     */
    private Long serviceTime = 0L;

    /**
     * 最大空闲时长
     */
    private Long maxReadyTime = 0L;

    /**
     * 累计空闲
     */
    private Long totalReadyTime = 0L;


    /**
     * 空闲次数
     */
    private Long readyTimes = 0L;

    /**
     * 忙碌次数
     */
    private Long notReadyTimes = 0L;

    /**
     * 累计话后时间长
     */
    private Long totalAfterTime = 0L;


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
    private Long totalRingTimes = 0L;

    /**
     * 当日累计接听次数
     */
    private Long totalAnswerTimes = 0L;

    /**
     * 隐藏客户号码(0:不隐藏;1:隐藏)
     */
    private Integer hiddenCustomer;

    /**
     * 坐席状态回调地址
     */
    private String webHook;

    /**
     * 坐席token,可以用于websocket和http请求
     */
    private String token;

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

    public List<SkillAgent> getSkillAgents() {
        return skillAgents;
    }

    public void setSkillAgents(List<SkillAgent> skillAgents) {
        this.skillAgents = skillAgents;
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

    public String getConsultDeviceId() {
        return consultDeviceId;
    }

    public void setConsultDeviceId(String consultDeviceId) {
        this.consultDeviceId = consultDeviceId;
    }

    public Long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Long loginTime) {
        this.loginTime = loginTime;
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

    public AgentState getAgentState() {
        return agentState;
    }

    public void setAgentState(AgentState agentState) {
        this.agentState = agentState;
    }

    public Long getStateTime() {
        return stateTime;
    }

    public void setStateTime(Long stateTime) {
        this.stateTime = stateTime;
    }

    public AgentPreset getAgentPreset() {
        return agentPreset;
    }

    public void setAgentPreset(AgentPreset agentPreset) {
        this.agentPreset = agentPreset;
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

    public Long getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(Long serviceTime) {
        this.serviceTime = serviceTime;
    }

    public Long getMaxReadyTime() {
        return maxReadyTime == null ? 0L : maxReadyTime;
    }

    public void setMaxReadyTime(Long maxReadyTime) {
        this.maxReadyTime = maxReadyTime;
    }

    public Long getTotalReadyTime() {
        return totalReadyTime == null ? 0L : totalReadyTime;
    }

    public void setTotalReadyTime(Long totalReadyTime) {
        this.totalReadyTime = totalReadyTime;
    }

    public Long getReadyTimes() {
        return readyTimes;
    }

    public void setReadyTimes(Long readyTimes) {
        this.readyTimes = readyTimes;
    }

    public Long getNotReadyTimes() {
        return notReadyTimes;
    }

    public void setNotReadyTimes(Long notReadyTimes) {
        this.notReadyTimes = notReadyTimes;
    }

    public Long getTotalAfterTime() {
        return totalAfterTime;
    }

    public void setTotalAfterTime(Long totalAfterTime) {
        this.totalAfterTime = totalAfterTime;
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

    public Long getTotalRingTimes() {
        return totalRingTimes;
    }

    public void setTotalRingTimes(Long totalRingTimes) {
        this.totalRingTimes = totalRingTimes;
    }

    public Long getTotalAnswerTimes() {
        return totalAnswerTimes;
    }

    public void setTotalAnswerTimes(Long totalAnswerTimes) {
        this.totalAnswerTimes = totalAnswerTimes;
    }

    public Integer getHiddenCustomer() {
        return hiddenCustomer == null ? 0 : hiddenCustomer;
    }

    public void setHiddenCustomer(Integer hiddenCustomer) {
        this.hiddenCustomer = hiddenCustomer;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getWebHook() {
        return webHook;
    }

    public void setWebHook(String webHook) {
        this.webHook = webHook;
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
        if (getSips() == null || getSips().size() == 0) {
            return null;
        }
        return getSips().get(0);
    }

    @Override
    public String toString() {
        return "AgentInfo{" + ", groupIds=" + groupIds + ", sips=" + sips + ", skillAgents=" + skillAgents + ", callId=" + callId + ", deviceId='" + deviceId + '\'' + ", loginTime=" + loginTime + ", agentState=" + agentState + ", loginType=" + loginType + ", workType=" + workType + ", stateTime=" + stateTime + ", beforeState=" + beforeState + ", beforeTime=" + beforeTime + ", logoutTime=" + logoutTime + ", maxReadyTime=" + maxReadyTime + ", totalReadyTime=" + totalReadyTime + ", maxTalkTime=" + maxTalkTime + ", totalTalkTime=" + totalTalkTime + ", totalRingTimes=" + totalRingTimes + ", totalAnswerTimes=" + totalAnswerTimes + '}';
    }
}
