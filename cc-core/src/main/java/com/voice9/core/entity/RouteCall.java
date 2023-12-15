package com.voice9.core.entity;

import java.io.Serializable;

/**
 * 字冠路由表
 *
 * @author caoliang
 * @date   2020/06/06
 */
public class RouteCall implements Serializable {
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
     * 所属企业
     */
    private Long companyId;

    /**
     * 所有路由组
     */
    private Long routeGroupId;

    /**
     * 字冠号码
     */
    private String routeNum;

    /**
     * 最长
     */
    private Integer numMax;

    /**
     * 最短
     */
    private Integer numMin;

    /**
     * 主叫替换规则
     */
    private Integer callerChange;

    /**
     * 替换号码
     */
    private String callerChangeNum;

    /**
     * 被叫替换规则
     */
    private Integer calledChange;

    /**
     * 替换号码
     */
    private String calledChangeNum;

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

    public Long getRouteGroupId() {
        return routeGroupId;
    }

    public void setRouteGroupId(Long routeGroupId) {
        this.routeGroupId = routeGroupId;
    }

    public String getRouteNum() {
        return routeNum;
    }

    public void setRouteNum(String routeNum) {
        this.routeNum = routeNum == null ? null : routeNum.trim();
    }

    public Integer getNumMax() {
        return numMax;
    }

    public void setNumMax(Integer numMax) {
        this.numMax = numMax;
    }

    public Integer getNumMin() {
        return numMin;
    }

    public void setNumMin(Integer numMin) {
        this.numMin = numMin;
    }

    public Integer getCallerChange() {
        return callerChange;
    }

    public void setCallerChange(Integer callerChange) {
        this.callerChange = callerChange;
    }

    public String getCallerChangeNum() {
        return callerChangeNum;
    }

    public void setCallerChangeNum(String callerChangeNum) {
        this.callerChangeNum = callerChangeNum == null ? null : callerChangeNum.trim();
    }

    public Integer getCalledChange() {
        return calledChange;
    }

    public void setCalledChange(Integer calledChange) {
        this.calledChange = calledChange;
    }

    public String getCalledChangeNum() {
        return calledChangeNum;
    }

    public void setCalledChangeNum(String calledChangeNum) {
        this.calledChangeNum = calledChangeNum == null ? null : calledChangeNum.trim();
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
        sb.append(", routeGroupId=").append(routeGroupId);
        sb.append(", routeNum=").append(routeNum);
        sb.append(", numMax=").append(numMax);
        sb.append(", numMin=").append(numMin);
        sb.append(", callerChange=").append(callerChange);
        sb.append(", callerChangeNum=").append(callerChangeNum);
        sb.append(", calledChange=").append(calledChange);
        sb.append(", calledChangeNum=").append(calledChangeNum);
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
        RouteCall other = (RouteCall) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCts() == null ? other.getCts() == null : this.getCts().equals(other.getCts()))
            && (this.getUts() == null ? other.getUts() == null : this.getUts().equals(other.getUts()))
            && (this.getCompanyId() == null ? other.getCompanyId() == null : this.getCompanyId().equals(other.getCompanyId()))
            && (this.getRouteGroupId() == null ? other.getRouteGroupId() == null : this.getRouteGroupId().equals(other.getRouteGroupId()))
            && (this.getRouteNum() == null ? other.getRouteNum() == null : this.getRouteNum().equals(other.getRouteNum()))
            && (this.getNumMax() == null ? other.getNumMax() == null : this.getNumMax().equals(other.getNumMax()))
            && (this.getNumMin() == null ? other.getNumMin() == null : this.getNumMin().equals(other.getNumMin()))
            && (this.getCallerChange() == null ? other.getCallerChange() == null : this.getCallerChange().equals(other.getCallerChange()))
            && (this.getCallerChangeNum() == null ? other.getCallerChangeNum() == null : this.getCallerChangeNum().equals(other.getCallerChangeNum()))
            && (this.getCalledChange() == null ? other.getCalledChange() == null : this.getCalledChange().equals(other.getCalledChange()))
            && (this.getCalledChangeNum() == null ? other.getCalledChangeNum() == null : this.getCalledChangeNum().equals(other.getCalledChangeNum()))
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
        result = prime * result + ((getRouteGroupId() == null) ? 0 : getRouteGroupId().hashCode());
        result = prime * result + ((getRouteNum() == null) ? 0 : getRouteNum().hashCode());
        result = prime * result + ((getNumMax() == null) ? 0 : getNumMax().hashCode());
        result = prime * result + ((getNumMin() == null) ? 0 : getNumMin().hashCode());
        result = prime * result + ((getCallerChange() == null) ? 0 : getCallerChange().hashCode());
        result = prime * result + ((getCallerChangeNum() == null) ? 0 : getCallerChangeNum().hashCode());
        result = prime * result + ((getCalledChange() == null) ? 0 : getCalledChange().hashCode());
        result = prime * result + ((getCalledChangeNum() == null) ? 0 : getCalledChangeNum().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        return result;
    }
}