package com.voice9.core.entity;

import java.io.Serializable;

/**
 * 溢出策略前置条件
 *
 * @author caoliang
 * @date   2020/06/06
 */
public class OverflowFront implements Serializable {
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
     * 策略ID
     */
    private Long overflowId;

    /**
     * frontType 1:队列长度; 2:队列等待最大时长; 3:呼损率
     */
    private Integer frontType;

    /**
     * 5种条件:
     * 0:全部; 1:小于或等于; 2:等于; 3:大于或等于; 4:大于
     */
    private Integer compareCondition;

    /**
     * 
     */
    private Integer rankValueStart;

    /**
     * 符号条件值
     */
    private Integer rankValue;

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

    public Long getOverflowId() {
        return overflowId;
    }

    public void setOverflowId(Long overflowId) {
        this.overflowId = overflowId;
    }

    public Integer getFrontType() {
        return frontType;
    }

    public void setFrontType(Integer frontType) {
        this.frontType = frontType;
    }

    public Integer getCompareCondition() {
        return compareCondition;
    }

    public void setCompareCondition(Integer compareCondition) {
        this.compareCondition = compareCondition;
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
        sb.append(", overflowId=").append(overflowId);
        sb.append(", frontType=").append(frontType);
        sb.append(", compareCondition=").append(compareCondition);
        sb.append(", rankValueStart=").append(rankValueStart);
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
        OverflowFront other = (OverflowFront) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCts() == null ? other.getCts() == null : this.getCts().equals(other.getCts()))
            && (this.getUts() == null ? other.getUts() == null : this.getUts().equals(other.getUts()))
            && (this.getCompanyId() == null ? other.getCompanyId() == null : this.getCompanyId().equals(other.getCompanyId()))
            && (this.getOverflowId() == null ? other.getOverflowId() == null : this.getOverflowId().equals(other.getOverflowId()))
            && (this.getFrontType() == null ? other.getFrontType() == null : this.getFrontType().equals(other.getFrontType()))
            && (this.getCompareCondition() == null ? other.getCompareCondition() == null : this.getCompareCondition().equals(other.getCompareCondition()))
            && (this.getRankValueStart() == null ? other.getRankValueStart() == null : this.getRankValueStart().equals(other.getRankValueStart()))
            && (this.getRankValue() == null ? other.getRankValue() == null : this.getRankValue().equals(other.getRankValue()))
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
        result = prime * result + ((getOverflowId() == null) ? 0 : getOverflowId().hashCode());
        result = prime * result + ((getFrontType() == null) ? 0 : getFrontType().hashCode());
        result = prime * result + ((getCompareCondition() == null) ? 0 : getCompareCondition().hashCode());
        result = prime * result + ((getRankValueStart() == null) ? 0 : getRankValueStart().hashCode());
        result = prime * result + ((getRankValue() == null) ? 0 : getRankValue().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        return result;
    }
}