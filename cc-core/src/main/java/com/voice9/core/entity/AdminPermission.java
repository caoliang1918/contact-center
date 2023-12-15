package com.voice9.core.entity;

import java.io.Serializable;

/**
 * 权限表
 *
 * @author caoliang
 * @date   2020/06/06
 */
public class AdminPermission implements Serializable {
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
     * 权限名称
     */
    private String permissionName;

    /**
     * 权限URL
     */
    private String permissionUrl;

    /**
     * 父权限id
     */
    private Long parentId;

    /**
     * 排序
     */
    private Integer permissionOrder;

    /**
     * 图标
     */
    private String icon;

    /**
     * 是否前端权限
     */
    private Integer isFront;

    /**
     * 是否后端权限
     */
    private Integer isInterface;

    /**
     * 
     */
    private Integer status;

    /**
     * 菜单级别
     */
    private Integer permissionLevel;

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

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName == null ? null : permissionName.trim();
    }

    public String getPermissionUrl() {
        return permissionUrl;
    }

    public void setPermissionUrl(String permissionUrl) {
        this.permissionUrl = permissionUrl == null ? null : permissionUrl.trim();
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getPermissionOrder() {
        return permissionOrder;
    }

    public void setPermissionOrder(Integer permissionOrder) {
        this.permissionOrder = permissionOrder;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public Integer getIsFront() {
        return isFront;
    }

    public void setIsFront(Integer isFront) {
        this.isFront = isFront;
    }

    public Integer getIsInterface() {
        return isInterface;
    }

    public void setIsInterface(Integer isInterface) {
        this.isInterface = isInterface;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPermissionLevel() {
        return permissionLevel;
    }

    public void setPermissionLevel(Integer permissionLevel) {
        this.permissionLevel = permissionLevel;
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
        sb.append(", permissionName=").append(permissionName);
        sb.append(", permissionUrl=").append(permissionUrl);
        sb.append(", parentId=").append(parentId);
        sb.append(", permissionOrder=").append(permissionOrder);
        sb.append(", icon=").append(icon);
        sb.append(", isFront=").append(isFront);
        sb.append(", isInterface=").append(isInterface);
        sb.append(", status=").append(status);
        sb.append(", permissionLevel=").append(permissionLevel);
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
        AdminPermission other = (AdminPermission) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCts() == null ? other.getCts() == null : this.getCts().equals(other.getCts()))
            && (this.getUts() == null ? other.getUts() == null : this.getUts().equals(other.getUts()))
            && (this.getCompanyId() == null ? other.getCompanyId() == null : this.getCompanyId().equals(other.getCompanyId()))
            && (this.getPermissionName() == null ? other.getPermissionName() == null : this.getPermissionName().equals(other.getPermissionName()))
            && (this.getPermissionUrl() == null ? other.getPermissionUrl() == null : this.getPermissionUrl().equals(other.getPermissionUrl()))
            && (this.getParentId() == null ? other.getParentId() == null : this.getParentId().equals(other.getParentId()))
            && (this.getPermissionOrder() == null ? other.getPermissionOrder() == null : this.getPermissionOrder().equals(other.getPermissionOrder()))
            && (this.getIcon() == null ? other.getIcon() == null : this.getIcon().equals(other.getIcon()))
            && (this.getIsFront() == null ? other.getIsFront() == null : this.getIsFront().equals(other.getIsFront()))
            && (this.getIsInterface() == null ? other.getIsInterface() == null : this.getIsInterface().equals(other.getIsInterface()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getPermissionLevel() == null ? other.getPermissionLevel() == null : this.getPermissionLevel().equals(other.getPermissionLevel()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCts() == null) ? 0 : getCts().hashCode());
        result = prime * result + ((getUts() == null) ? 0 : getUts().hashCode());
        result = prime * result + ((getCompanyId() == null) ? 0 : getCompanyId().hashCode());
        result = prime * result + ((getPermissionName() == null) ? 0 : getPermissionName().hashCode());
        result = prime * result + ((getPermissionUrl() == null) ? 0 : getPermissionUrl().hashCode());
        result = prime * result + ((getParentId() == null) ? 0 : getParentId().hashCode());
        result = prime * result + ((getPermissionOrder() == null) ? 0 : getPermissionOrder().hashCode());
        result = prime * result + ((getIcon() == null) ? 0 : getIcon().hashCode());
        result = prime * result + ((getIsFront() == null) ? 0 : getIsFront().hashCode());
        result = prime * result + ((getIsInterface() == null) ? 0 : getIsInterface().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getPermissionLevel() == null) ? 0 : getPermissionLevel().hashCode());
        return result;
    }
}