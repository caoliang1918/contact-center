package com.voice9.core.entity;

import java.io.Serializable;

/**
 * 路由字码表
 *
 * @author caoliang
 * @date   2020/06/06
 */
public class VdnConfig implements Serializable {
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
     * 子码日程
     */
    private String name;

    /**
     * vdn_id
     */
    private Long vdnId;

    /**
     * 日程id
     */
    private Long scheduleId;

    /**
     * 路由类型(1:技能组,2:放音,3:ivr,4:坐席,5:外呼)
     */
    private Integer routeType;

    /**
     * 路由类型值
     */
    private String routeValue;

    /**
     * 放音类型(1:按键导航,2:技能组,3:ivr,4:路由字码,5:挂机)
     */
    private Integer playType;

    /**
     * 放音类型对应值
     */
    private Long playValue;

    /**
     * 结束音
     */
    private String dtmfEnd;

    /**
     * 重复播放次数
     */
    private Integer retry;

    /**
     * 最大收键长度
     */
    private Integer dtmfMax;

    /**
     * 最小收键长度
     */
    private Integer dtmfMin;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Long getVdnId() {
        return vdnId;
    }

    public void setVdnId(Long vdnId) {
        this.vdnId = vdnId;
    }

    public Long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Integer getRouteType() {
        return routeType;
    }

    public void setRouteType(Integer routeType) {
        this.routeType = routeType;
    }

    public String getRouteValue() {
        return routeValue;
    }

    public void setRouteValue(String routeValue) {
        this.routeValue = routeValue == null ? null : routeValue.trim();
    }

    public Integer getPlayType() {
        return playType;
    }

    public void setPlayType(Integer playType) {
        this.playType = playType;
    }

    public Long getPlayValue() {
        return playValue;
    }

    public void setPlayValue(Long playValue) {
        this.playValue = playValue;
    }

    public String getDtmfEnd() {
        return dtmfEnd;
    }

    public void setDtmfEnd(String dtmfEnd) {
        this.dtmfEnd = dtmfEnd == null ? null : dtmfEnd.trim();
    }

    public Integer getRetry() {
        return retry;
    }

    public void setRetry(Integer retry) {
        this.retry = retry;
    }

    public Integer getDtmfMax() {
        return dtmfMax;
    }

    public void setDtmfMax(Integer dtmfMax) {
        this.dtmfMax = dtmfMax;
    }

    public Integer getDtmfMin() {
        return dtmfMin;
    }

    public void setDtmfMin(Integer dtmfMin) {
        this.dtmfMin = dtmfMin;
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
        sb.append(", name=").append(name);
        sb.append(", vdnId=").append(vdnId);
        sb.append(", scheduleId=").append(scheduleId);
        sb.append(", routeType=").append(routeType);
        sb.append(", routeValue=").append(routeValue);
        sb.append(", playType=").append(playType);
        sb.append(", playValue=").append(playValue);
        sb.append(", dtmfEnd=").append(dtmfEnd);
        sb.append(", retry=").append(retry);
        sb.append(", dtmfMax=").append(dtmfMax);
        sb.append(", dtmfMin=").append(dtmfMin);
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
        VdnConfig other = (VdnConfig) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getCts() == null ? other.getCts() == null : this.getCts().equals(other.getCts()))
                && (this.getUts() == null ? other.getUts() == null : this.getUts().equals(other.getUts()))
                && (this.getCompanyId() == null ? other.getCompanyId() == null : this.getCompanyId().equals(other.getCompanyId()))
                && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
                && (this.getVdnId() == null ? other.getVdnId() == null : this.getVdnId().equals(other.getVdnId()))
                && (this.getScheduleId() == null ? other.getScheduleId() == null : this.getScheduleId().equals(other.getScheduleId()))
                && (this.getRouteType() == null ? other.getRouteType() == null : this.getRouteType().equals(other.getRouteType()))
                && (this.getRouteValue() == null ? other.getRouteValue() == null : this.getRouteValue().equals(other.getRouteValue()))
                && (this.getPlayType() == null ? other.getPlayType() == null : this.getPlayType().equals(other.getPlayType()))
                && (this.getPlayValue() == null ? other.getPlayValue() == null : this.getPlayValue().equals(other.getPlayValue()))
                && (this.getDtmfEnd() == null ? other.getDtmfEnd() == null : this.getDtmfEnd().equals(other.getDtmfEnd()))
                && (this.getRetry() == null ? other.getRetry() == null : this.getRetry().equals(other.getRetry()))
                && (this.getDtmfMax() == null ? other.getDtmfMax() == null : this.getDtmfMax().equals(other.getDtmfMax()))
                && (this.getDtmfMin() == null ? other.getDtmfMin() == null : this.getDtmfMin().equals(other.getDtmfMin()))
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
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getVdnId() == null) ? 0 : getVdnId().hashCode());
        result = prime * result + ((getScheduleId() == null) ? 0 : getScheduleId().hashCode());
        result = prime * result + ((getRouteType() == null) ? 0 : getRouteType().hashCode());
        result = prime * result + ((getRouteValue() == null) ? 0 : getRouteValue().hashCode());
        result = prime * result + ((getPlayType() == null) ? 0 : getPlayType().hashCode());
        result = prime * result + ((getPlayValue() == null) ? 0 : getPlayValue().hashCode());
        result = prime * result + ((getDtmfEnd() == null) ? 0 : getDtmfEnd().hashCode());
        result = prime * result + ((getRetry() == null) ? 0 : getRetry().hashCode());
        result = prime * result + ((getDtmfMax() == null) ? 0 : getDtmfMax().hashCode());
        result = prime * result + ((getDtmfMin() == null) ? 0 : getDtmfMin().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        return result;
    }
}