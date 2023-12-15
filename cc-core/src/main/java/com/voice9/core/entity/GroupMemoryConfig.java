package com.voice9.core.entity;

import java.io.Serializable;

/**
 * 
 *
 * @author caoliang
 * @date   2020/06/06
 */
public class GroupMemoryConfig implements Serializable {
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
     * 企业ID
     */
    private Long companyId;

    /**
     * 技能组ID
     */
    private Long groupId;

    /**
     * 匹配成功策略 1:等待记忆坐席 2:超时转其他空闲坐席 3:忙碌转空闲坐席
     */
    private Integer successStrategy;

    /**
     * 匹配成功策略值
     */
    private Long successStrategyValue;

    /**
     * 匹配失败策略:
     * 1:其他空闲坐席
     * 2:其他技能组
     * 3:vdn
     * 4:ivr
     * 5:挂机
     */
    private Integer failStrategy;

    /**
     * 匹配失败策略值
     */
    private Long failStrategyValue;

    /**
     * 记忆天数
     */
    private Integer memoryDay;

    /**
     * 呼入覆盖
     */
    private Integer inboundCover;

    /**
     * 外呼覆盖
     */
    private Integer outboundCover;

    /**
     * 
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

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Integer getSuccessStrategy() {
        return successStrategy;
    }

    public void setSuccessStrategy(Integer successStrategy) {
        this.successStrategy = successStrategy;
    }

    public Long getSuccessStrategyValue() {
        return successStrategyValue;
    }

    public void setSuccessStrategyValue(Long successStrategyValue) {
        this.successStrategyValue = successStrategyValue;
    }

    public Integer getFailStrategy() {
        return failStrategy;
    }

    public void setFailStrategy(Integer failStrategy) {
        this.failStrategy = failStrategy;
    }

    public Long getFailStrategyValue() {
        return failStrategyValue;
    }

    public void setFailStrategyValue(Long failStrategyValue) {
        this.failStrategyValue = failStrategyValue;
    }

    public Integer getMemoryDay() {
        return memoryDay;
    }

    public void setMemoryDay(Integer memoryDay) {
        this.memoryDay = memoryDay;
    }

    public Integer getInboundCover() {
        return inboundCover;
    }

    public void setInboundCover(Integer inboundCover) {
        this.inboundCover = inboundCover;
    }

    public Integer getOutboundCover() {
        return outboundCover;
    }

    public void setOutboundCover(Integer outboundCover) {
        this.outboundCover = outboundCover;
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
        sb.append(", groupId=").append(groupId);
        sb.append(", successStrategy=").append(successStrategy);
        sb.append(", successStrategyValue=").append(successStrategyValue);
        sb.append(", failStrategy=").append(failStrategy);
        sb.append(", failStrategyValue=").append(failStrategyValue);
        sb.append(", memoryDay=").append(memoryDay);
        sb.append(", inboundCover=").append(inboundCover);
        sb.append(", outboundCover=").append(outboundCover);
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
        GroupMemoryConfig other = (GroupMemoryConfig) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUts() == null ? other.getUts() == null : this.getUts().equals(other.getUts()))
            && (this.getCts() == null ? other.getCts() == null : this.getCts().equals(other.getCts()))
            && (this.getCompanyId() == null ? other.getCompanyId() == null : this.getCompanyId().equals(other.getCompanyId()))
            && (this.getGroupId() == null ? other.getGroupId() == null : this.getGroupId().equals(other.getGroupId()))
            && (this.getSuccessStrategy() == null ? other.getSuccessStrategy() == null : this.getSuccessStrategy().equals(other.getSuccessStrategy()))
            && (this.getSuccessStrategyValue() == null ? other.getSuccessStrategyValue() == null : this.getSuccessStrategyValue().equals(other.getSuccessStrategyValue()))
            && (this.getFailStrategy() == null ? other.getFailStrategy() == null : this.getFailStrategy().equals(other.getFailStrategy()))
            && (this.getFailStrategyValue() == null ? other.getFailStrategyValue() == null : this.getFailStrategyValue().equals(other.getFailStrategyValue()))
            && (this.getMemoryDay() == null ? other.getMemoryDay() == null : this.getMemoryDay().equals(other.getMemoryDay()))
            && (this.getInboundCover() == null ? other.getInboundCover() == null : this.getInboundCover().equals(other.getInboundCover()))
            && (this.getOutboundCover() == null ? other.getOutboundCover() == null : this.getOutboundCover().equals(other.getOutboundCover()))
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
        result = prime * result + ((getGroupId() == null) ? 0 : getGroupId().hashCode());
        result = prime * result + ((getSuccessStrategy() == null) ? 0 : getSuccessStrategy().hashCode());
        result = prime * result + ((getSuccessStrategyValue() == null) ? 0 : getSuccessStrategyValue().hashCode());
        result = prime * result + ((getFailStrategy() == null) ? 0 : getFailStrategy().hashCode());
        result = prime * result + ((getFailStrategyValue() == null) ? 0 : getFailStrategyValue().hashCode());
        result = prime * result + ((getMemoryDay() == null) ? 0 : getMemoryDay().hashCode());
        result = prime * result + ((getInboundCover() == null) ? 0 : getInboundCover().hashCode());
        result = prime * result + ((getOutboundCover() == null) ? 0 : getOutboundCover().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        return result;
    }
}