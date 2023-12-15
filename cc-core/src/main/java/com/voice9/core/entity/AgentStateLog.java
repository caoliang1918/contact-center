package com.voice9.core.entity;

import java.io.Serializable;

/**
 * 坐席状态历史表
 *
 * @author caoliang
 * @date 2020/06/06
 */
public class AgentStateLog implements Serializable {
    /**
     * PK
     */
    private Long id;

    /**
     * 创建时间
     */
    private Long cts;

    /**
     * 修改时间
     */
    private Long uts;

    /**
     * 企业id
     */
    private Long companyId;

    /**
     * 主技能组id
     */
    private Long groupId;

    /**
     * 坐席id
     */
    private Long agentId;

    /**
     * 坐席编号
     */
    private String agentKey;

    /**
     * 坐席名称
     */
    private String agentName;

    /**
     * 通话唯一标识
     */
    private Long callId;

    /**
     * 登录类型
     */
    private Integer loginType;

    /**
     * 工作类型
     */
    private Integer workType;

    /**
     * 服务站点
     */
    private String host;

    /**
     * 远端地址
     */
    private String remoteAddress;

    /**
     * 变更之前状态
     */
    private String beforeState;

    /**
     * 更变之前时间
     */
    private Long beforeTime;

    /**
     * 变更之后状态
     */
    private String state;

    /**
     * 当前时间
     */
    private Long stateTime;

    /**
     * 持续时间
     */
    private Integer duration;

    /**
     * 忙碌类型
     */
    private String busyDesc;

    /**
     * 状态
     */
    private Integer status;

    /**
     *
     */
    private String month;

    /**
     * 扩展字段1
     */
    private String ext1;

    /**
     * 扩展字段2
     */
    private String ext2;

    /**
     * 扩展字段3
     */
    private String ext3;

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

    public Long getUts() {
        return uts;
    }

    public void setUts(Long uts) {
        this.uts = uts;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getAgentId() {
        return agentId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
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
        this.agentName = agentName;
    }

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

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host == null ? null : host.trim();
    }

    public String getRemoteAddress() {
        return remoteAddress;
    }

    public void setRemoteAddress(String remoteAddress) {
        this.remoteAddress = remoteAddress == null ? null : remoteAddress.trim();
    }

    public String getBeforeState() {
        return beforeState;
    }

    public void setBeforeState(String beforeState) {
        this.beforeState = beforeState == null ? null : beforeState.trim();
    }

    public Long getBeforeTime() {
        return beforeTime;
    }

    public void setBeforeTime(Long beforeTime) {
        this.beforeTime = beforeTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public Long getStateTime() {
        return stateTime;
    }

    public void setStateTime(Long stateTime) {
        this.stateTime = stateTime;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getBusyDesc() {
        return busyDesc;
    }

    public void setBusyDesc(String busyDesc) {
        this.busyDesc = busyDesc == null ? null : busyDesc.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getExt1() {
        return ext1;
    }

    public void setExt1(String ext1) {
        this.ext1 = ext1 == null ? null : ext1.trim();
    }

    public String getExt2() {
        return ext2;
    }

    public void setExt2(String ext2) {
        this.ext2 = ext2 == null ? null : ext2.trim();
    }

    public String getExt3() {
        return ext3;
    }

    public void setExt3(String ext3) {
        this.ext3 = ext3 == null ? null : ext3.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", cts=").append(cts);
        sb.append(", uts=").append(uts);
        sb.append(", companyId=").append(companyId);
        sb.append(", groupId=").append(groupId);
        sb.append(", agentId=").append(agentId);
        sb.append(", agentKey=").append(agentKey);
        sb.append(", callId=").append(callId);
        sb.append(", loginType=").append(loginType);
        sb.append(", workType=").append(workType);
        sb.append(", host=").append(host);
        sb.append(", remoteAddress=").append(remoteAddress);
        sb.append(", beforeState=").append(beforeState);
        sb.append(", beforeTime=").append(beforeTime);
        sb.append(", state=").append(state);
        sb.append(", stateTime=").append(stateTime);
        sb.append(", duration=").append(duration);
        sb.append(", busyDesc=").append(busyDesc);
        sb.append(", status=").append(status);
        sb.append(", ext1=").append(ext1);
        sb.append(", ext2=").append(ext2);
        sb.append(", ext3=").append(ext3);
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
        AgentStateLog other = (AgentStateLog) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getCts() == null ? other.getCts() == null : this.getCts().equals(other.getCts()))
                && (this.getUts() == null ? other.getUts() == null : this.getUts().equals(other.getUts()))
                && (this.getCompanyId() == null ? other.getCompanyId() == null : this.getCompanyId().equals(other.getCompanyId()))
                && (this.getGroupId() == null ? other.getGroupId() == null : this.getGroupId().equals(other.getGroupId()))
                && (this.getAgentId() == null ? other.getAgentId() == null : this.getAgentId().equals(other.getAgentId()))
                && (this.getAgentKey() == null ? other.getAgentKey() == null : this.getAgentKey().equals(other.getAgentKey()))
                && (this.getCallId() == null ? other.getCallId() == null : this.getCallId().equals(other.getCallId()))
                && (this.getLoginType() == null ? other.getLoginType() == null : this.getLoginType().equals(other.getLoginType()))
                && (this.getWorkType() == null ? other.getWorkType() == null : this.getWorkType().equals(other.getWorkType()))
                && (this.getHost() == null ? other.getHost() == null : this.getHost().equals(other.getHost()))
                && (this.getRemoteAddress() == null ? other.getRemoteAddress() == null : this.getRemoteAddress().equals(other.getRemoteAddress()))
                && (this.getBeforeState() == null ? other.getBeforeState() == null : this.getBeforeState().equals(other.getBeforeState()))
                && (this.getBeforeTime() == null ? other.getBeforeTime() == null : this.getBeforeTime().equals(other.getBeforeTime()))
                && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
                && (this.getStateTime() == null ? other.getStateTime() == null : this.getStateTime().equals(other.getStateTime()))
                && (this.getDuration() == null ? other.getDuration() == null : this.getDuration().equals(other.getDuration()))
                && (this.getBusyDesc() == null ? other.getBusyDesc() == null : this.getBusyDesc().equals(other.getBusyDesc()))
                && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
                && (this.getExt1() == null ? other.getExt1() == null : this.getExt1().equals(other.getExt1()))
                && (this.getExt2() == null ? other.getExt2() == null : this.getExt2().equals(other.getExt2()))
                && (this.getExt3() == null ? other.getExt3() == null : this.getExt3().equals(other.getExt3()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCts() == null) ? 0 : getCts().hashCode());
        result = prime * result + ((getUts() == null) ? 0 : getUts().hashCode());
        result = prime * result + ((getCompanyId() == null) ? 0 : getCompanyId().hashCode());
        result = prime * result + ((getGroupId() == null) ? 0 : getGroupId().hashCode());
        result = prime * result + ((getAgentId() == null) ? 0 : getAgentId().hashCode());
        result = prime * result + ((getAgentKey() == null) ? 0 : getAgentKey().hashCode());
        result = prime * result + ((getCallId() == null) ? 0 : getCallId().hashCode());
        result = prime * result + ((getLoginType() == null) ? 0 : getLoginType().hashCode());
        result = prime * result + ((getWorkType() == null) ? 0 : getWorkType().hashCode());
        result = prime * result + ((getHost() == null) ? 0 : getHost().hashCode());
        result = prime * result + ((getRemoteAddress() == null) ? 0 : getRemoteAddress().hashCode());
        result = prime * result + ((getBeforeState() == null) ? 0 : getBeforeState().hashCode());
        result = prime * result + ((getBeforeTime() == null) ? 0 : getBeforeTime().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getStateTime() == null) ? 0 : getStateTime().hashCode());
        result = prime * result + ((getDuration() == null) ? 0 : getDuration().hashCode());
        result = prime * result + ((getBusyDesc() == null) ? 0 : getBusyDesc().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getExt1() == null) ? 0 : getExt1().hashCode());
        result = prime * result + ((getExt2() == null) ? 0 : getExt2().hashCode());
        result = prime * result + ((getExt3() == null) ? 0 : getExt3().hashCode());
        return result;
    }
}