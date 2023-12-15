package com.voice9.core.entity;

import java.io.Serializable;

/**
 * 坐席自定义策略表
 *
 * @author caoliang
 * @date   2020/06/06
 */
public class GroupStrategyExp implements Serializable {
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
     * 技能组id
     */
    private Long groupId;

    /**
     * 自定义值
     */
    private String strategyKey;

    /**
     * 百分百
     */
    private Integer strategyPresent;

    /**
     * 类型
     */
    private Integer strategyType;

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

    public String getStrategyKey() {
        return strategyKey;
    }

    public void setStrategyKey(String strategyKey) {
        this.strategyKey = strategyKey == null ? null : strategyKey.trim();
    }

    public Integer getStrategyPresent() {
        return strategyPresent;
    }

    public void setStrategyPresent(Integer strategyPresent) {
        this.strategyPresent = strategyPresent;
    }

    public Integer getStrategyType() {
        return strategyType;
    }

    public void setStrategyType(Integer strategyType) {
        this.strategyType = strategyType;
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
        sb.append(", groupId=").append(groupId);
        sb.append(", strategyKey=").append(strategyKey);
        sb.append(", strategyPresent=").append(strategyPresent);
        sb.append(", strategyType=").append(strategyType);
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
        GroupStrategyExp other = (GroupStrategyExp) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCts() == null ? other.getCts() == null : this.getCts().equals(other.getCts()))
            && (this.getUts() == null ? other.getUts() == null : this.getUts().equals(other.getUts()))
            && (this.getCompanyId() == null ? other.getCompanyId() == null : this.getCompanyId().equals(other.getCompanyId()))
            && (this.getGroupId() == null ? other.getGroupId() == null : this.getGroupId().equals(other.getGroupId()))
            && (this.getStrategyKey() == null ? other.getStrategyKey() == null : this.getStrategyKey().equals(other.getStrategyKey()))
            && (this.getStrategyPresent() == null ? other.getStrategyPresent() == null : this.getStrategyPresent().equals(other.getStrategyPresent()))
            && (this.getStrategyType() == null ? other.getStrategyType() == null : this.getStrategyType().equals(other.getStrategyType()))
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
        result = prime * result + ((getGroupId() == null) ? 0 : getGroupId().hashCode());
        result = prime * result + ((getStrategyKey() == null) ? 0 : getStrategyKey().hashCode());
        result = prime * result + ((getStrategyPresent() == null) ? 0 : getStrategyPresent().hashCode());
        result = prime * result + ((getStrategyType() == null) ? 0 : getStrategyType().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        return result;
    }
}