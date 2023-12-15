package org.voice9.cc.websocket.response;

import com.voice9.core.po.AgentState;

import java.util.List;

/**
 * Created by caoliang on 2020/11/3
 */
public class AgentStateResppnse {

    /**
     *
     */
    private Long id;

    /**
     *
     */
    private String agentKey;

    /**
     *
     */
    private Long companyId;

    /**
     * 绑定的电话号码
     */
    private String sipPhone;

    /**
     *
     */
    private Long groupId;

    /**
     * 登录服务地址
     */
    private String host;

    /**
     * 业务所在实例
     */
    private String appId;

    /**
     * 所属技能组
     */
    private List<Long> groupIds;

    /**
     * 坐席sip
     */
    private List<String> sips;

    /**
     *
     */
    private Long callId;

    /**
     * 登录时间
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
     * 当天状态时间
     */
    private Long stateTime = 0L;

    /**
     * 上一次状态
     */
    private AgentState beforeState;

    /**
     * 上一次状态时间
     */
    private Long beforeTime = 0L;

    /**
     * 下线时间
     */
    private Long logoutTime = 0L;


    /**
     * 坐席最近的一次服务时间,电话则是振铃时间
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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAgentKey() {
        return agentKey;
    }

    public void setAgentKey(String agentKey) {
        this.agentKey = agentKey;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getSipPhone() {
        return sipPhone;
    }

    public void setSipPhone(String sipPhone) {
        this.sipPhone = sipPhone;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
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

    public Long getCallId() {
        return callId;
    }

    public void setCallId(Long callId) {
        this.callId = callId;
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

    public Long getMaxReadyTime() {
        return maxReadyTime;
    }

    public void setMaxReadyTime(Long maxReadyTime) {
        this.maxReadyTime = maxReadyTime;
    }

    public Long getTotalReadyTime() {
        return totalReadyTime;
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

    public Long getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(Long serviceTime) {
        this.serviceTime = serviceTime;
    }

    @Override
    public String toString() {
        return "AgentStateResppnse{" +
                "id=" + id +
                ", agentKey='" + agentKey + '\'' +
                ", companyId=" + companyId +
                ", sipPhone='" + sipPhone + '\'' +
                ", groupId=" + groupId +
                ", host='" + host + '\'' +
                ", appId=" + appId +
                ", groupIds=" + groupIds +
                ", sips=" + sips +
                ", callId=" + callId +
                ", loginTime=" + loginTime +
                ", loginType=" + loginType +
                ", workType=" + workType +
                ", agentState=" + agentState +
                ", stateTime=" + stateTime +
                ", beforeState=" + beforeState +
                ", beforeTime=" + beforeTime +
                ", logoutTime=" + logoutTime +
                ", serviceTime=" + serviceTime +
                ", maxReadyTime=" + maxReadyTime +
                ", totalReadyTime=" + totalReadyTime +
                ", readyTimes=" + readyTimes +
                ", notReadyTimes=" + notReadyTimes +
                ", totalAfterTime=" + totalAfterTime +
                ", maxTalkTime=" + maxTalkTime +
                ", totalTalkTime=" + totalTalkTime +
                ", totalRingTimes=" + totalRingTimes +
                ", totalAnswerTimes=" + totalAnswerTimes +
                '}';
    }
}
