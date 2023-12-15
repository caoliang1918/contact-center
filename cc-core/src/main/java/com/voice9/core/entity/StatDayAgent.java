package com.voice9.core.entity;

import java.io.Serializable;

/**
 * 
 *
 * @author caoliang
 * @date   2022/05/08
 */
public class StatDayAgent implements Serializable {
    /**
     * PK
     */
    private Long id;

    /**
     * cts
     */
    private Long cts;

    /**
     * 企业id
     */
    private Long companyId;

    /**
     * 坐席编号
     */
    private String agentKey;

    /**
     * 坐席名称
     */
    private String agentName;

    /**
     * 统计时间
     */
    private Long statTime;

    /**
     * 外呼总量
     */
    private Long calloutCnt;

    /**
     * 外呼接通量
     */
    private Long calloutAnswerCnt;

    /**
     * 呼入分配量
     */
    private Long callinCnt;

    /**
     * 呼入接起量
     */
    private Long callinAnswerCnt;

    /**
     * 登录次数
     */
    private Long loginCnt;

    /**
     * 空闲次数
     */
    private Long readyCnt;

    /**
     * 忙碌次数
     */
    private Long notReadyCnt;

    /**
     * 话后次数
     */
    private Long afterCnt;

    /**
     * 登录总时长
     */
    private Long loginTime;

    /**
     * 空闲总时长
     */
    private Long readyTime;

    /**
     * 忙碌总时长
     */
    private Long notReadyTime;

    /**
     * 自定义忙碌总时间
     */
    private Long busyTime;

    /**
     * 话后总时长
     */
    private Long afterTime;

    /**
     * 通话总时长
     */
    private Long talkTime;

    /**
     * 呼入通话总时长
     */
    private Long callinTalkTime;

    /**
     * 外呼通话总时长
     */
    private Long calloutTalkTime;

    /**
     * 状态
     */
    private Integer status;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCts() {
        return cts;
    }

    public void setCts(Long cts) {
        this.cts = cts;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getAgentKey() {
        return agentKey;
    }

    public void setAgentKey(String agentKey) {
        this.agentKey = agentKey == null ? null : agentKey.trim();
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName == null ? null : agentName.trim();
    }

    public Long getStatTime() {
        return statTime;
    }

    public void setStatTime(Long statTime) {
        this.statTime = statTime;
    }

    public Long getCalloutCnt() {
        return calloutCnt;
    }

    public void setCalloutCnt(Long calloutCnt) {
        this.calloutCnt = calloutCnt;
    }

    public Long getCalloutAnswerCnt() {
        return calloutAnswerCnt;
    }

    public void setCalloutAnswerCnt(Long calloutAnswerCnt) {
        this.calloutAnswerCnt = calloutAnswerCnt;
    }

    public Long getCallinCnt() {
        return callinCnt;
    }

    public void setCallinCnt(Long callinCnt) {
        this.callinCnt = callinCnt;
    }

    public Long getCallinAnswerCnt() {
        return callinAnswerCnt;
    }

    public void setCallinAnswerCnt(Long callinAnswerCnt) {
        this.callinAnswerCnt = callinAnswerCnt;
    }

    public Long getLoginCnt() {
        return loginCnt;
    }

    public void setLoginCnt(Long loginCnt) {
        this.loginCnt = loginCnt;
    }

    public Long getReadyCnt() {
        return readyCnt;
    }

    public void setReadyCnt(Long readyCnt) {
        this.readyCnt = readyCnt;
    }

    public Long getNotReadyCnt() {
        return notReadyCnt;
    }

    public void setNotReadyCnt(Long notReadyCnt) {
        this.notReadyCnt = notReadyCnt;
    }

    public Long getAfterCnt() {
        return afterCnt;
    }

    public void setAfterCnt(Long afterCnt) {
        this.afterCnt = afterCnt;
    }

    public Long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Long loginTime) {
        this.loginTime = loginTime;
    }

    public Long getReadyTime() {
        return readyTime;
    }

    public void setReadyTime(Long readyTime) {
        this.readyTime = readyTime;
    }

    public Long getNotReadyTime() {
        return notReadyTime;
    }

    public void setNotReadyTime(Long notReadyTime) {
        this.notReadyTime = notReadyTime;
    }

    public Long getBusyTime() {
        return busyTime;
    }

    public void setBusyTime(Long busyTime) {
        this.busyTime = busyTime;
    }

    public Long getAfterTime() {
        return afterTime;
    }

    public void setAfterTime(Long afterTime) {
        this.afterTime = afterTime;
    }

    public Long getTalkTime() {
        return talkTime;
    }

    public void setTalkTime(Long talkTime) {
        this.talkTime = talkTime;
    }

    public Long getCallinTalkTime() {
        return callinTalkTime;
    }

    public void setCallinTalkTime(Long callinTalkTime) {
        this.callinTalkTime = callinTalkTime;
    }

    public Long getCalloutTalkTime() {
        return calloutTalkTime;
    }

    public void setCalloutTalkTime(Long calloutTalkTime) {
        this.calloutTalkTime = calloutTalkTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", cts=").append(cts);
        sb.append(", companyId=").append(companyId);
        sb.append(", agentKey=").append(agentKey);
        sb.append(", agentName=").append(agentName);
        sb.append(", statTime=").append(statTime);
        sb.append(", calloutCnt=").append(calloutCnt);
        sb.append(", calloutAnswerCnt=").append(calloutAnswerCnt);
        sb.append(", callinCnt=").append(callinCnt);
        sb.append(", callinAnswerCnt=").append(callinAnswerCnt);
        sb.append(", loginCnt=").append(loginCnt);
        sb.append(", readyCnt=").append(readyCnt);
        sb.append(", notReadyCnt=").append(notReadyCnt);
        sb.append(", afterCnt=").append(afterCnt);
        sb.append(", loginTime=").append(loginTime);
        sb.append(", readyTime=").append(readyTime);
        sb.append(", notReadyTime=").append(notReadyTime);
        sb.append(", busyTime=").append(busyTime);
        sb.append(", afterTime=").append(afterTime);
        sb.append(", talkTime=").append(talkTime);
        sb.append(", callinTalkTime=").append(callinTalkTime);
        sb.append(", calloutTalkTime=").append(calloutTalkTime);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        StatDayAgent other = (StatDayAgent) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCts() == null ? other.getCts() == null : this.getCts().equals(other.getCts()))
            && (this.getCompanyId() == null ? other.getCompanyId() == null : this.getCompanyId().equals(other.getCompanyId()))
            && (this.getAgentKey() == null ? other.getAgentKey() == null : this.getAgentKey().equals(other.getAgentKey()))
            && (this.getAgentName() == null ? other.getAgentName() == null : this.getAgentName().equals(other.getAgentName()))
            && (this.getStatTime() == null ? other.getStatTime() == null : this.getStatTime().equals(other.getStatTime()))
            && (this.getCalloutCnt() == null ? other.getCalloutCnt() == null : this.getCalloutCnt().equals(other.getCalloutCnt()))
            && (this.getCalloutAnswerCnt() == null ? other.getCalloutAnswerCnt() == null : this.getCalloutAnswerCnt().equals(other.getCalloutAnswerCnt()))
            && (this.getCallinCnt() == null ? other.getCallinCnt() == null : this.getCallinCnt().equals(other.getCallinCnt()))
            && (this.getCallinAnswerCnt() == null ? other.getCallinAnswerCnt() == null : this.getCallinAnswerCnt().equals(other.getCallinAnswerCnt()))
            && (this.getLoginCnt() == null ? other.getLoginCnt() == null : this.getLoginCnt().equals(other.getLoginCnt()))
            && (this.getReadyCnt() == null ? other.getReadyCnt() == null : this.getReadyCnt().equals(other.getReadyCnt()))
            && (this.getNotReadyCnt() == null ? other.getNotReadyCnt() == null : this.getNotReadyCnt().equals(other.getNotReadyCnt()))
            && (this.getAfterCnt() == null ? other.getAfterCnt() == null : this.getAfterCnt().equals(other.getAfterCnt()))
            && (this.getLoginTime() == null ? other.getLoginTime() == null : this.getLoginTime().equals(other.getLoginTime()))
            && (this.getReadyTime() == null ? other.getReadyTime() == null : this.getReadyTime().equals(other.getReadyTime()))
            && (this.getNotReadyTime() == null ? other.getNotReadyTime() == null : this.getNotReadyTime().equals(other.getNotReadyTime()))
            && (this.getBusyTime() == null ? other.getBusyTime() == null : this.getBusyTime().equals(other.getBusyTime()))
            && (this.getAfterTime() == null ? other.getAfterTime() == null : this.getAfterTime().equals(other.getAfterTime()))
            && (this.getTalkTime() == null ? other.getTalkTime() == null : this.getTalkTime().equals(other.getTalkTime()))
            && (this.getCallinTalkTime() == null ? other.getCallinTalkTime() == null : this.getCallinTalkTime().equals(other.getCallinTalkTime()))
            && (this.getCalloutTalkTime() == null ? other.getCalloutTalkTime() == null : this.getCalloutTalkTime().equals(other.getCalloutTalkTime()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCts() == null) ? 0 : getCts().hashCode());
        result = prime * result + ((getCompanyId() == null) ? 0 : getCompanyId().hashCode());
        result = prime * result + ((getAgentKey() == null) ? 0 : getAgentKey().hashCode());
        result = prime * result + ((getAgentName() == null) ? 0 : getAgentName().hashCode());
        result = prime * result + ((getStatTime() == null) ? 0 : getStatTime().hashCode());
        result = prime * result + ((getCalloutCnt() == null) ? 0 : getCalloutCnt().hashCode());
        result = prime * result + ((getCalloutAnswerCnt() == null) ? 0 : getCalloutAnswerCnt().hashCode());
        result = prime * result + ((getCallinCnt() == null) ? 0 : getCallinCnt().hashCode());
        result = prime * result + ((getCallinAnswerCnt() == null) ? 0 : getCallinAnswerCnt().hashCode());
        result = prime * result + ((getLoginCnt() == null) ? 0 : getLoginCnt().hashCode());
        result = prime * result + ((getReadyCnt() == null) ? 0 : getReadyCnt().hashCode());
        result = prime * result + ((getNotReadyCnt() == null) ? 0 : getNotReadyCnt().hashCode());
        result = prime * result + ((getAfterCnt() == null) ? 0 : getAfterCnt().hashCode());
        result = prime * result + ((getLoginTime() == null) ? 0 : getLoginTime().hashCode());
        result = prime * result + ((getReadyTime() == null) ? 0 : getReadyTime().hashCode());
        result = prime * result + ((getNotReadyTime() == null) ? 0 : getNotReadyTime().hashCode());
        result = prime * result + ((getBusyTime() == null) ? 0 : getBusyTime().hashCode());
        result = prime * result + ((getAfterTime() == null) ? 0 : getAfterTime().hashCode());
        result = prime * result + ((getTalkTime() == null) ? 0 : getTalkTime().hashCode());
        result = prime * result + ((getCallinTalkTime() == null) ? 0 : getCallinTalkTime().hashCode());
        result = prime * result + ((getCalloutTalkTime() == null) ? 0 : getCalloutTalkTime().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        return result;
    }
}