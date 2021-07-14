package org.cti.cc.entity;

import java.io.Serializable;

/**
 * 技能组技能表
 *
 * @author caoliang
 * @date   2020/06/06
 */
public class SkillGroup implements Serializable {
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
     * 技能ID
     */
    private Long skillId;

    /**
     * 技能组ID
     */
    private Long groupId;

    /**
     * 等级类型(1:全部,2:等于,3:>,4:<,5:介于)
     */
    private Integer rankAbout;

    /**
     * 介于的开始值
     */
    private Integer rankValueStart;

    /**
     * 等级值
     */
    private Integer rankValue;

    /**
     * 占用率
     */
    private Integer rate;

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

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Integer getRankAbout() {
        return rankAbout;
    }

    public void setRankAbout(Integer rankAbout) {
        this.rankAbout = rankAbout;
    }

    public Integer getRankValueStart() {
        return rankValueStart;
    }

    public void setRankValueStart(Integer rankValueStart) {
        this.rankValueStart = rankValueStart;
    }

    public Integer getRankValue() {
        return rankValue;
    }

    public void setRankValue(Integer rankValue) {
        this.rankValue = rankValue;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
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
        sb.append(", uts=").append(uts);
        sb.append(", cts=").append(cts);
        sb.append(", companyId=").append(companyId);
        sb.append(", skillId=").append(skillId);
        sb.append(", groupId=").append(groupId);
        sb.append(", rankAbout=").append(rankAbout);
        sb.append(", rankValueStart=").append(rankValueStart);
        sb.append(", rankValue=").append(rankValue);
        sb.append(", rate=").append(rate);
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
        SkillGroup other = (SkillGroup) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUts() == null ? other.getUts() == null : this.getUts().equals(other.getUts()))
            && (this.getCts() == null ? other.getCts() == null : this.getCts().equals(other.getCts()))
            && (this.getCompanyId() == null ? other.getCompanyId() == null : this.getCompanyId().equals(other.getCompanyId()))
            && (this.getSkillId() == null ? other.getSkillId() == null : this.getSkillId().equals(other.getSkillId()))
            && (this.getGroupId() == null ? other.getGroupId() == null : this.getGroupId().equals(other.getGroupId()))
            && (this.getRankAbout() == null ? other.getRankAbout() == null : this.getRankAbout().equals(other.getRankAbout()))
            && (this.getRankValueStart() == null ? other.getRankValueStart() == null : this.getRankValueStart().equals(other.getRankValueStart()))
            && (this.getRankValue() == null ? other.getRankValue() == null : this.getRankValue().equals(other.getRankValue()))
            && (this.getRate() == null ? other.getRate() == null : this.getRate().equals(other.getRate()))
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
        result = prime * result + ((getGroupId() == null) ? 0 : getGroupId().hashCode());
        result = prime * result + ((getRankAbout() == null) ? 0 : getRankAbout().hashCode());
        result = prime * result + ((getRankValueStart() == null) ? 0 : getRankValueStart().hashCode());
        result = prime * result + ((getRankValue() == null) ? 0 : getRankValue().hashCode());
        result = prime * result + ((getRate() == null) ? 0 : getRate().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        return result;
    }
}