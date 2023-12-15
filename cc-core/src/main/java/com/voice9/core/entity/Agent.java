package com.voice9.core.entity;

import java.io.Serializable;

/**
 * 座席工号表
 *
 * @author caoliang
 * @date   2020/06/06
 */
public class Agent implements Serializable {
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
     * 座席类型：1:普通座席；2：班长
     */
    private Integer agentType;

    /**
     * 座席密码
     */
    private String passwd;

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
     * 总机坐席
     */
    private Integer agentOnline;

    /**
     * 话后自动空闲间隔时长
     */
    private Integer afterInterval;

    /**
     * 主叫显号
     */
    private String display;

    /**
     * 振铃时长
     */
    private Integer ringTime;

    /**
     * 登录服务地址
     */
    private String host;

    /**
     * 扩展1
     */
    private String ext1;

    /**
     * 扩展2
     */
    private String ext2;

    /**
     * 扩展3
     */
    private String ext3;

    private Integer state;

    /**
     * 状态：1 开通，0关闭, -1:删除
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

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId == null ? null : agentId.trim();
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

    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode == null ? null : agentCode.trim();
    }

    public Integer getAgentType() {
        return agentType;
    }

    public void setAgentType(Integer agentType) {
        this.agentType = agentType;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd == null ? null : passwd.trim();
    }

    public String getSipPhone() {
        return sipPhone;
    }

    public void setSipPhone(String sipPhone) {
        this.sipPhone = sipPhone == null ? null : sipPhone.trim();
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

    public Integer getAgentOnline() {
        return agentOnline;
    }

    public void setAgentOnline(Integer agentOnline) {
        this.agentOnline = agentOnline;
    }

    public Integer getAfterInterval() {
        return afterInterval;
    }

    public void setAfterInterval(Integer afterInterval) {
        this.afterInterval = afterInterval;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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
        sb.append(", uts=").append(uts);
        sb.append(", companyId=").append(companyId);
        sb.append(", agentId=").append(agentId);
        sb.append(", agentKey=").append(agentKey);
        sb.append(", agentName=").append(agentName);
        sb.append(", agentCode=").append(agentCode);
        sb.append(", agentType=").append(agentType);
        sb.append(", passwd=").append(passwd);
        sb.append(", sipPhone=").append(sipPhone);
        sb.append(", record=").append(record);
        sb.append(", groupId=").append(groupId);
        sb.append(", afterInterval=").append(afterInterval);
        sb.append(", display=").append(display);
        sb.append(", ringTime=").append(ringTime);
        sb.append(", ext1=").append(ext1);
        sb.append(", ext2=").append(ext2);
        sb.append(", ext3=").append(ext3);
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
        Agent other = (Agent) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCts() == null ? other.getCts() == null : this.getCts().equals(other.getCts()))
            && (this.getUts() == null ? other.getUts() == null : this.getUts().equals(other.getUts()))
            && (this.getCompanyId() == null ? other.getCompanyId() == null : this.getCompanyId().equals(other.getCompanyId()))
            && (this.getAgentId() == null ? other.getAgentId() == null : this.getAgentId().equals(other.getAgentId()))
            && (this.getAgentKey() == null ? other.getAgentKey() == null : this.getAgentKey().equals(other.getAgentKey()))
            && (this.getAgentName() == null ? other.getAgentName() == null : this.getAgentName().equals(other.getAgentName()))
            && (this.getAgentCode() == null ? other.getAgentCode() == null : this.getAgentCode().equals(other.getAgentCode()))
            && (this.getAgentType() == null ? other.getAgentType() == null : this.getAgentType().equals(other.getAgentType()))
            && (this.getPasswd() == null ? other.getPasswd() == null : this.getPasswd().equals(other.getPasswd()))
            && (this.getSipPhone() == null ? other.getSipPhone() == null : this.getSipPhone().equals(other.getSipPhone()))
            && (this.getRecord() == null ? other.getRecord() == null : this.getRecord().equals(other.getRecord()))
            && (this.getGroupId() == null ? other.getGroupId() == null : this.getGroupId().equals(other.getGroupId()))
            && (this.getAfterInterval() == null ? other.getAfterInterval() == null : this.getAfterInterval().equals(other.getAfterInterval()))
            && (this.getDisplay() == null ? other.getDisplay() == null : this.getDisplay().equals(other.getDisplay()))
            && (this.getRingTime() == null ? other.getRingTime() == null : this.getRingTime().equals(other.getRingTime()))
            && (this.getExt1() == null ? other.getExt1() == null : this.getExt1().equals(other.getExt1()))
            && (this.getExt2() == null ? other.getExt2() == null : this.getExt2().equals(other.getExt2()))
            && (this.getExt3() == null ? other.getExt3() == null : this.getExt3().equals(other.getExt3()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCts() == null) ? 0 : getCts().hashCode());
        result = prime * result + ((getUts() == null) ? 0 : getUts().hashCode());
        result = prime * result + ((getCompanyId() == null) ? 0 : getCompanyId().hashCode());
        result = prime * result + ((getAgentId() == null) ? 0 : getAgentId().hashCode());
        result = prime * result + ((getAgentKey() == null) ? 0 : getAgentKey().hashCode());
        result = prime * result + ((getAgentName() == null) ? 0 : getAgentName().hashCode());
        result = prime * result + ((getAgentCode() == null) ? 0 : getAgentCode().hashCode());
        result = prime * result + ((getAgentType() == null) ? 0 : getAgentType().hashCode());
        result = prime * result + ((getPasswd() == null) ? 0 : getPasswd().hashCode());
        result = prime * result + ((getSipPhone() == null) ? 0 : getSipPhone().hashCode());
        result = prime * result + ((getRecord() == null) ? 0 : getRecord().hashCode());
        result = prime * result + ((getGroupId() == null) ? 0 : getGroupId().hashCode());
        result = prime * result + ((getAfterInterval() == null) ? 0 : getAfterInterval().hashCode());
        result = prime * result + ((getDisplay() == null) ? 0 : getDisplay().hashCode());
        result = prime * result + ((getRingTime() == null) ? 0 : getRingTime().hashCode());
        result = prime * result + ((getExt1() == null) ? 0 : getExt1().hashCode());
        result = prime * result + ((getExt2() == null) ? 0 : getExt2().hashCode());
        result = prime * result + ((getExt3() == null) ? 0 : getExt3().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        return result;
    }
}