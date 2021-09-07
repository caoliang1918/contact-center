package org.cti.cc.entity;

import java.io.Serializable;

/**
 * @author caoliang
 * @date 2021/09/05
 */
public class StatHourAgentWork implements Serializable {
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
    private Long stateTime;

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

    public Long getStateTime() {
        return stateTime;
    }

    public void setStateTime(Long stateTime) {
        this.stateTime = stateTime;
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
        return talkTime == null ? 0L : talkTime;
    }

    public void setTalkTime(Long talkTime) {
        this.talkTime = talkTime;
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
        sb.append(", stateTime=").append(stateTime);
        sb.append(", loginTime=").append(loginTime);
        sb.append(", readyTime=").append(readyTime);
        sb.append(", notReadyTime=").append(notReadyTime);
        sb.append(", busyTime=").append(busyTime);
        sb.append(", afterTime=").append(afterTime);
        sb.append(", talkTime=").append(talkTime);
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
        StatHourAgentWork other = (StatHourAgentWork) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getCts() == null ? other.getCts() == null : this.getCts().equals(other.getCts()))
                && (this.getCompanyId() == null ? other.getCompanyId() == null : this.getCompanyId().equals(other.getCompanyId()))
                && (this.getAgentKey() == null ? other.getAgentKey() == null : this.getAgentKey().equals(other.getAgentKey()))
                && (this.getAgentName() == null ? other.getAgentName() == null : this.getAgentName().equals(other.getAgentName()))
                && (this.getStateTime() == null ? other.getStateTime() == null : this.getStateTime().equals(other.getStateTime()))
                && (this.getLoginTime() == null ? other.getLoginTime() == null : this.getLoginTime().equals(other.getLoginTime()))
                && (this.getReadyTime() == null ? other.getReadyTime() == null : this.getReadyTime().equals(other.getReadyTime()))
                && (this.getNotReadyTime() == null ? other.getNotReadyTime() == null : this.getNotReadyTime().equals(other.getNotReadyTime()))
                && (this.getBusyTime() == null ? other.getBusyTime() == null : this.getBusyTime().equals(other.getBusyTime()))
                && (this.getAfterTime() == null ? other.getAfterTime() == null : this.getAfterTime().equals(other.getAfterTime()))
                && (this.getTalkTime() == null ? other.getTalkTime() == null : this.getTalkTime().equals(other.getTalkTime()))
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
        result = prime * result + ((getStateTime() == null) ? 0 : getStateTime().hashCode());
        result = prime * result + ((getLoginTime() == null) ? 0 : getLoginTime().hashCode());
        result = prime * result + ((getReadyTime() == null) ? 0 : getReadyTime().hashCode());
        result = prime * result + ((getNotReadyTime() == null) ? 0 : getNotReadyTime().hashCode());
        result = prime * result + ((getBusyTime() == null) ? 0 : getBusyTime().hashCode());
        result = prime * result + ((getAfterTime() == null) ? 0 : getAfterTime().hashCode());
        result = prime * result + ((getTalkTime() == null) ? 0 : getTalkTime().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        return result;
    }
}