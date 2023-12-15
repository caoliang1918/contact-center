package com.voice9.core.entity;

import java.io.Serializable;

/**
 * 
 *
 * @author caoliang
 * @date   2020/06/06
 */
public class GroupOverflow implements Serializable {
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
     * 技能组ID
     */
    private Long groupId;

    /**
     * 溢出策略ID
     */
    private Long overflowId;

    /**
     * 优先级
     */
    private Integer levelValue;

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

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getOverflowId() {
        return overflowId;
    }

    public void setOverflowId(Long overflowId) {
        this.overflowId = overflowId;
    }

    public Integer getLevelValue() {
        return levelValue;
    }

    public void setLevelValue(Integer levelValue) {
        this.levelValue = levelValue;
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
        sb.append(", groupId=").append(groupId);
        sb.append(", overflowId=").append(overflowId);
        sb.append(", levelValue=").append(levelValue);
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
        GroupOverflow other = (GroupOverflow) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCts() == null ? other.getCts() == null : this.getCts().equals(other.getCts()))
            && (this.getUts() == null ? other.getUts() == null : this.getUts().equals(other.getUts()))
            && (this.getGroupId() == null ? other.getGroupId() == null : this.getGroupId().equals(other.getGroupId()))
            && (this.getOverflowId() == null ? other.getOverflowId() == null : this.getOverflowId().equals(other.getOverflowId()))
            && (this.getLevelValue() == null ? other.getLevelValue() == null : this.getLevelValue().equals(other.getLevelValue()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCts() == null) ? 0 : getCts().hashCode());
        result = prime * result + ((getUts() == null) ? 0 : getUts().hashCode());
        result = prime * result + ((getGroupId() == null) ? 0 : getGroupId().hashCode());
        result = prime * result + ((getOverflowId() == null) ? 0 : getOverflowId().hashCode());
        result = prime * result + ((getLevelValue() == null) ? 0 : getLevelValue().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        return result;
    }
}