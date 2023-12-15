package com.voice9.core.entity;

import java.io.Serializable;

/**
 * @author caoliang
 * @date 2022/05/08
 */
public class StatHourAgent implements Serializable {
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
        return statTime == null ? 0L : statTime;
    }

    public void setStatTime(Long statTime) {
        this.statTime = statTime;
    }

    public Long getCalloutCnt() {
        return calloutCnt == null ? 0L : calloutCnt;
    }

    public void setCalloutCnt(Long calloutCnt) {
        this.calloutCnt = calloutCnt;
    }

    public Long getCalloutAnswerCnt() {
        return calloutAnswerCnt == null ? 0L : calloutAnswerCnt;
    }

    public void setCalloutAnswerCnt(Long calloutAnswerCnt) {
        this.calloutAnswerCnt = calloutAnswerCnt;
    }

    public Long getCallinCnt() {
        return callinCnt == null ? 0L : callinCnt;
    }

    public void setCallinCnt(Long callinCnt) {
        this.callinCnt = callinCnt;
    }

    public Long getCallinAnswerCnt() {
        return callinAnswerCnt == null ? 0L : callinAnswerCnt;
    }

    public void setCallinAnswerCnt(Long callinAnswerCnt) {
        this.callinAnswerCnt = callinAnswerCnt;
    }

    public Long getLoginCnt() {
        return loginCnt == null ? 0L : loginCnt;
    }

    public void setLoginCnt(Long loginCnt) {
        this.loginCnt = loginCnt;
    }

    public Long getReadyCnt() {
        return readyCnt == null ? 0L : readyCnt;
    }

    public void setReadyCnt(Long readyCnt) {
        this.readyCnt = readyCnt;
    }

    public Long getNotReadyCnt() {
        return notReadyCnt == null ? 0L : notReadyCnt;
    }

    public void setNotReadyCnt(Long notReadyCnt) {
        this.notReadyCnt = notReadyCnt;
    }

    public Long getAfterCnt() {
        return afterCnt == null ? 0L : afterCnt;
    }

    public void setAfterCnt(Long afterCnt) {
        this.afterCnt = afterCnt;
    }

    public Long getLoginTime() {
        return loginTime == null ? 0L : loginTime;
    }

    public void setLoginTime(Long loginTime) {
        this.loginTime = loginTime;
    }

    public Long getReadyTime() {
        return readyTime == null ? 0L : readyTime;
    }

    public void setReadyTime(Long readyTime) {
        this.readyTime = readyTime;
    }

    public Long getNotReadyTime() {
        return notReadyTime == null ? 0L : notReadyTime;
    }

    public void setNotReadyTime(Long notReadyTime) {
        this.notReadyTime = notReadyTime;
    }

    public Long getBusyTime() {
        return busyTime == null ? 0L : busyTime;
    }

    public void setBusyTime(Long busyTime) {
        this.busyTime = busyTime;
    }

    public Long getAfterTime() {
        return afterTime == null ? 0L : afterTime;
    }

    public void setAfterTime(Long afterTime) {
        this.afterTime = afterTime;
    }

    public Long getTalkTime() {
        return talkTime == null ? 0L : afterTime;
    }

    public void setTalkTime(Long talkTime) {
        this.talkTime = talkTime;
    }

    public Long getCallinTalkTime() {
        return callinTalkTime == null ? 0L : callinTalkTime;
    }

    public void setCallinTalkTime(Long callinTalkTime) {
        this.callinTalkTime = callinTalkTime;
    }

    public Long getCalloutTalkTime() {
        return calloutTalkTime == null ? 0L : calloutTalkTime;
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
        return "StatHourAgent{" + "id=" + id + ", cts=" + cts + ", companyId=" + companyId + ", agentKey='" + agentKey + '\'' + ", agentName='" + agentName + '\'' + ", statTime=" + statTime + ", calloutCnt=" + calloutCnt + ", calloutAnswerCnt=" + calloutAnswerCnt + ", callinCnt=" + callinCnt + ", callinAnswerCnt=" + callinAnswerCnt + ", loginCnt=" + loginCnt + ", readyCnt=" + readyCnt + ", notReadyCnt=" + notReadyCnt + ", afterCnt=" + afterCnt + ", loginTime=" + loginTime + ", readyTime=" + readyTime + ", notReadyTime=" + notReadyTime + ", busyTime=" + busyTime + ", afterTime=" + afterTime + ", talkTime=" + talkTime + ", callinTalkTime=" + callinTalkTime + ", calloutTalkTime=" + calloutTalkTime + ", status=" + status + '}';
    }
}