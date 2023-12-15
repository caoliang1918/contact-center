package com.voice9.core.entity;

import java.io.Serializable;

/**
 * 按键导航表
 *
 * @author caoliang
 * @date 2020/06/06
 */
public class VdnDtmf implements Serializable {
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
     * 按键导航ID
     */
    private Long navigateId;

    /**
     * 按键
     */
    private String dtmf;

    /**
     * 路由类型(1:技能组,2:IVR,3:路由字码,4:坐席分机,5:挂机)
     */
    private Integer routeType;

    /**
     * 路由值
     */
    private Long routeValue;

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

    public Long getNavigateId() {
        return navigateId;
    }

    public void setNavigateId(Long navigateId) {
        this.navigateId = navigateId;
    }

    public String getDtmf() {
        return dtmf;
    }

    public void setDtmf(String dtmf) {
        this.dtmf = dtmf == null ? null : dtmf.trim();
    }

    public Integer getRouteType() {
        return routeType;
    }

    public void setRouteType(Integer routeType) {
        this.routeType = routeType;
    }

    public Long getRouteValue() {
        return routeValue;
    }

    public void setRouteValue(Long routeValue) {
        this.routeValue = routeValue;
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
        sb.append(", navigateId=").append(navigateId);
        sb.append(", dtmf=").append(dtmf);
        sb.append(", routeType=").append(routeType);
        sb.append(", routeValue=").append(routeValue);
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
        VdnDtmf other = (VdnDtmf) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getCts() == null ? other.getCts() == null : this.getCts().equals(other.getCts()))
                && (this.getUts() == null ? other.getUts() == null : this.getUts().equals(other.getUts()))
                && (this.getCompanyId() == null ? other.getCompanyId() == null : this.getCompanyId().equals(other.getCompanyId()))
                && (this.getNavigateId() == null ? other.getNavigateId() == null : this.getNavigateId().equals(other.getNavigateId()))
                && (this.getDtmf() == null ? other.getDtmf() == null : this.getDtmf().equals(other.getDtmf()))
                && (this.getRouteType() == null ? other.getRouteType() == null : this.getRouteType().equals(other.getRouteType()))
                && (this.getRouteValue() == null ? other.getRouteValue() == null : this.getRouteValue().equals(other.getRouteValue()))
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
        result = prime * result + ((getNavigateId() == null) ? 0 : getNavigateId().hashCode());
        result = prime * result + ((getDtmf() == null) ? 0 : getDtmf().hashCode());
        result = prime * result + ((getRouteType() == null) ? 0 : getRouteType().hashCode());
        result = prime * result + ((getRouteValue() == null) ? 0 : getRouteValue().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        return result;
    }
}