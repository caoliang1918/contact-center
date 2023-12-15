package com.voice9.core.entity;

import java.io.Serializable;

/**
 * 坐席技能表
 *
 * @author caoliang
 * @date 2020/06/06
 */
public class SkillAgent implements Serializable {
    /**
     * PK
     */
    private Long id;

    /**
     * 创建时间
     */
    private Long uts;

    /**
     * 修改时间
     */
    private Long cts;

    /**
     * 企业id
     */
    private Long companyId;

    /**
     * 技能id
     */
    private Long skillId;

    /**
     * 坐席id
     */
    private Long agentId;

    /**
     * 范围
     */
    private Integer rankValue;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 坐席
     */
    private String agentKey;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUts() {
        return uts;
    }

    public void setUts(Long uts) {
        this.uts = uts;
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

    public Long getSkillId() {
        return skillId;
    }

    public void setSkillId(Long skillId) {
        this.skillId = skillId;
    }

    public Long getAgentId() {
        return agentId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    public Integer getRankValue() {
        return rankValue;
    }

    public void setRankValue(Integer rankValue) {
        this.rankValue = rankValue;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAgentKey() {
        return agentKey;
    }

    public void setAgentKey(String agentKey) {
        this.agentKey = agentKey;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", uts=").append(uts);
        sb.append(", cts=").append(cts);
        sb.append(", companyId=").append(companyId);
        sb.append(", skillId=").append(skillId);
        sb.append(", agentId=").append(agentId);
        sb.append(", rankValue=").append(rankValue);
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
        SkillAgent other = (SkillAgent) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getUts() == null ? other.getUts() == null : this.getUts().equals(other.getUts()))
                && (this.getCts() == null ? other.getCts() == null : this.getCts().equals(other.getCts()))
                && (this.getCompanyId() == null ? other.getCompanyId() == null : this.getCompanyId().equals(other.getCompanyId()))
                && (this.getSkillId() == null ? other.getSkillId() == null : this.getSkillId().equals(other.getSkillId()))
                && (this.getAgentId() == null ? other.getAgentId() == null : this.getAgentId().equals(other.getAgentId()))
                && (this.getRankValue() == null ? other.getRankValue() == null : this.getRankValue().equals(other.getRankValue()))
                && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUts() == null) ? 0 : getUts().hashCode());
        result = prime * result + ((getCts() == null) ? 0 : getCts().hashCode());
        result = prime * result + ((getCompanyId() == null) ? 0 : getCompanyId().hashCode());
        result = prime * result + ((getSkillId() == null) ? 0 : getSkillId().hashCode());
        result = prime * result + ((getAgentId() == null) ? 0 : getAgentId().hashCode());
        result = prime * result + ((getRankValue() == null) ? 0 : getRankValue().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        return result;
    }
}