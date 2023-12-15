package com.voice9.core.entity;

import java.io.Serializable;

/**
 * 路由字冠关联组表
 *
 * @author caoliang
 * @date   2020/06/06
 */
public class RouteGetwayGroup implements Serializable {
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
     * 媒体网关
     */
    private Long getwayId;

    /**
     * 网关组
     */
    private Long routeGroupId;

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

    public Long getGetwayId() {
        return getwayId;
    }

    public void setGetwayId(Long getwayId) {
        this.getwayId = getwayId;
    }

    public Long getRouteGroupId() {
        return routeGroupId;
    }

    public void setRouteGroupId(Long routeGroupId) {
        this.routeGroupId = routeGroupId;
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
        sb.append(", getwayId=").append(getwayId);
        sb.append(", routeGroupId=").append(routeGroupId);
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
        RouteGetwayGroup other = (RouteGetwayGroup) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCts() == null ? other.getCts() == null : this.getCts().equals(other.getCts()))
            && (this.getUts() == null ? other.getUts() == null : this.getUts().equals(other.getUts()))
            && (this.getGetwayId() == null ? other.getGetwayId() == null : this.getGetwayId().equals(other.getGetwayId()))
            && (this.getRouteGroupId() == null ? other.getRouteGroupId() == null : this.getRouteGroupId().equals(other.getRouteGroupId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCts() == null) ? 0 : getCts().hashCode());
        result = prime * result + ((getUts() == null) ? 0 : getUts().hashCode());
        result = prime * result + ((getGetwayId() == null) ? 0 : getGetwayId().hashCode());
        result = prime * result + ((getRouteGroupId() == null) ? 0 : getRouteGroupId().hashCode());
        return result;
    }
}