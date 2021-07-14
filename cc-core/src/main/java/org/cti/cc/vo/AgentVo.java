package org.cti.cc.vo;

import org.cti.cc.entity.Skill;
import org.cti.cc.po.AgentState;

import java.util.List;

/**
 * Created by caoliang on 2020/12/17
 */
public class AgentVo {

    /**
     * PK
     */
    private Long id;



    /**
     * 企业ID
     */
    private Long companyId;

    /**
     * 坐席工号
     */
    private String agentId;

    /**
     * 坐席账户
     */
    private String agentKey;

    /**
     * 坐席名称
     */
    private String agentName;

    /**
     * 坐席分机号
     */
    private String agentCode;

    /**
     * 座席类型：0:普通座席；1：班长
     */
    private Integer agentType;

    /**
     * 绑定的电话号码
     */
    private String sipPhone;

    /**
     * 是否录音 0 no 1 yes
     */
    private Integer record;

    /**
     * 座席主要技能组  不能为空 必填项
     */
    private Long groupId;

    /**
     * 话后自动空闲间隔时长
     */
    private Integer afterInterval;

    /**
     * 主叫显号
     */
    private String diaplay;

    /**
     * 振铃时长
     */
    private Integer ringTime;

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
     * 坐席技能
     */
    private List<Skill> skills;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getAgentKey() {
        return agentKey;
    }

    public void setAgentKey(String agentKey) {
        this.agentKey = agentKey;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }

    public Integer getAgentType() {
        return agentType;
    }

    public void setAgentType(Integer agentType) {
        this.agentType = agentType;
    }

    public String getSipPhone() {
        return sipPhone;
    }

    public void setSipPhone(String sipPhone) {
        this.sipPhone = sipPhone;
    }

    public Integer getRecord() {
        return record;
    }

    public void setRecord(Integer record) {
        this.record = record;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Integer getAfterInterval() {
        return afterInterval;
    }

    public void setAfterInterval(Integer afterInterval) {
        this.afterInterval = afterInterval;
    }

    public String getDiaplay() {
        return diaplay;
    }

    public void setDiaplay(String diaplay) {
        this.diaplay = diaplay;
    }

    public Integer getRingTime() {
        return ringTime;
    }

    public void setRingTime(Integer ringTime) {
        this.ringTime = ringTime;
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
        return "AgentVo{" +
                "id=" + id +
                ", companyId=" + companyId +
                ", agentId='" + agentId + '\'' +
                ", agentKey='" + agentKey + '\'' +
                ", agentName='" + agentName + '\'' +
                ", agentCode='" + agentCode + '\'' +
                ", agentType=" + agentType +
                ", sipPhone='" + sipPhone + '\'' +
                ", record=" + record +
                ", groupId=" + groupId +
                ", afterInterval=" + afterInterval +
                ", diaplay='" + diaplay + '\'' +
                ", ringTime=" + ringTime +
                ", host='" + host + '\'' +
                ", groupIds=" + groupIds +
                ", sips=" + sips +
                ", skills=" + skills +
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
