package com.voice9.core.entity;

import java.io.Serializable;

/**
 * 溢出策略表
 *
 * @author caoliang
 * @date   2020/06/06
 */
public class OverflowConfig implements Serializable {
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
     * 名称
     */
    private String name;

    /**
     * 1:排队,2:溢出,3:挂机
     */
    private Integer handleType;

    /**
     * 排队方式(1:先进先出,2:vip,3:自定义)
     */
    private Integer busyType;

    /**
     * 排队超时时间
     */
    private Integer queueTimeout;

    /**
     * 排队超时(1:溢出,2:挂机)
     */
    private Integer busyTimeoutType;

    /**
     * 溢出(1:group,2:ivr,3:vdn)
     */
    private Integer overflowType;

    /**
     * 溢出值
     */
    private Integer overflowValue;

    /**
     * 自定义排队表达式
     */
    private String lineupExpression;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getHandleType() {
        return handleType;
    }

    public void setHandleType(Integer handleType) {
        this.handleType = handleType;
    }

    public Integer getBusyType() {
        return busyType;
    }

    public void setBusyType(Integer busyType) {
        this.busyType = busyType;
    }

    public Integer getQueueTimeout() {
        return queueTimeout;
    }

    public void setQueueTimeout(Integer queueTimeout) {
        this.queueTimeout = queueTimeout;
    }

    public Integer getBusyTimeoutType() {
        return busyTimeoutType;
    }

    public void setBusyTimeoutType(Integer busyTimeoutType) {
        this.busyTimeoutType = busyTimeoutType;
    }

    public Integer getOverflowType() {
        return overflowType;
    }

    public void setOverflowType(Integer overflowType) {
        this.overflowType = overflowType;
    }

    public Integer getOverflowValue() {
        return overflowValue;
    }

    public void setOverflowValue(Integer overflowValue) {
        this.overflowValue = overflowValue;
    }

    public String getLineupExpression() {
        return lineupExpression;
    }

    public void setLineupExpression(String lineupExpression) {
        this.lineupExpression = lineupExpression == null ? null : lineupExpression.trim();
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
        sb.append(", name=").append(name);
        sb.append(", handleType=").append(handleType);
        sb.append(", busyType=").append(busyType);
        sb.append(", queueTimeout=").append(queueTimeout);
        sb.append(", busyTimeoutType=").append(busyTimeoutType);
        sb.append(", overflowType=").append(overflowType);
        sb.append(", overflowValue=").append(overflowValue);
        sb.append(", lineupExpression=").append(lineupExpression);
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
        OverflowConfig other = (OverflowConfig) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCts() == null ? other.getCts() == null : this.getCts().equals(other.getCts()))
            && (this.getUts() == null ? other.getUts() == null : this.getUts().equals(other.getUts()))
            && (this.getCompanyId() == null ? other.getCompanyId() == null : this.getCompanyId().equals(other.getCompanyId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getHandleType() == null ? other.getHandleType() == null : this.getHandleType().equals(other.getHandleType()))
            && (this.getBusyType() == null ? other.getBusyType() == null : this.getBusyType().equals(other.getBusyType()))
            && (this.getQueueTimeout() == null ? other.getQueueTimeout() == null : this.getQueueTimeout().equals(other.getQueueTimeout()))
            && (this.getBusyTimeoutType() == null ? other.getBusyTimeoutType() == null : this.getBusyTimeoutType().equals(other.getBusyTimeoutType()))
            && (this.getOverflowType() == null ? other.getOverflowType() == null : this.getOverflowType().equals(other.getOverflowType()))
            && (this.getOverflowValue() == null ? other.getOverflowValue() == null : this.getOverflowValue().equals(other.getOverflowValue()))
            && (this.getLineupExpression() == null ? other.getLineupExpression() == null : this.getLineupExpression().equals(other.getLineupExpression()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCts() == null) ? 0 : getCts().hashCode());
        result = prime * result + ((getUts() == null) ? 0 : getUts().hashCode());
        result = prime * result + ((getCompanyId() == null) ? 0 : getCompanyId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getHandleType() == null) ? 0 : getHandleType().hashCode());
        result = prime * result + ((getBusyType() == null) ? 0 : getBusyType().hashCode());
        result = prime * result + ((getQueueTimeout() == null) ? 0 : getQueueTimeout().hashCode());
        result = prime * result + ((getBusyTimeoutType() == null) ? 0 : getBusyTimeoutType().hashCode());
        result = prime * result + ((getOverflowType() == null) ? 0 : getOverflowType().hashCode());
        result = prime * result + ((getOverflowValue() == null) ? 0 : getOverflowValue().hashCode());
        result = prime * result + ((getLineupExpression() == null) ? 0 : getLineupExpression().hashCode());
        return result;
    }
}