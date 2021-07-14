package org.zhongweixian.cc.websocket.response;

import org.cti.cc.entity.Skill;
import org.cti.cc.po.AgentState;

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
     *
     */
    private Long loginTime = 0L;

    /**
     *
     */
    private AgentState agentState;

    /**
     * 1： 坐席sip号
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

    @Override
    public String toString() {
        return "AgentStateResppnse{" +
                "id=" + id +
                ", agentKey='" + agentKey + '\'' +
                ", companyId=" + companyId +
                ", sipPhone='" + sipPhone + '\'' +
                ", groupId=" + groupId +
                ", host='" + host + '\'' +
                ", groupIds=" + groupIds +
                ", sips=" + sips +
                ", callId=" + callId +
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
