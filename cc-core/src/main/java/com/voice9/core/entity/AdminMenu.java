package com.voice9.core.entity;

import java.io.Serializable;

/**
 * 
 *
 * @author caoliang
 * @date   2022/01/06
 */
public class AdminMenu implements Serializable {
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
     * 菜单id
     */
    private String menuId;

    /**
     * 父id
     */
    private String parentId;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 访问url
     */
    private String pathUrl;

    /**
     * 访问方法
     */
    private String pathMethod;

    /**
     * 菜单等级
     */
    private Integer menuLevel;

    /**
     * 排序
     */
    private Integer menuOrder;

    /**
     * 图标
     */
    private String icon;

    /**
     * 子节点数
     */
    private Integer childNum;

    /**
     * 创建时添加权限(1:是,0:否)
     */
    private Integer createDefault;

    /**
     * 已有账号授权(1:是,0:否)
     */
    private Integer initDefault;

    /**
     * 前端
     */
    private Integer frontSite;

    /**
     * 后端
     */
    private Integer endSite;

    /**
     * 数据状态
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

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId == null ? null : menuId.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPathUrl() {
        return pathUrl;
    }

    public void setPathUrl(String pathUrl) {
        this.pathUrl = pathUrl == null ? null : pathUrl.trim();
    }

    public String getPathMethod() {
        return pathMethod;
    }

    public void setPathMethod(String pathMethod) {
        this.pathMethod = pathMethod == null ? null : pathMethod.trim();
    }

    public Integer getMenuLevel() {
        return menuLevel;
    }

    public void setMenuLevel(Integer menuLevel) {
        this.menuLevel = menuLevel;
    }

    public Integer getMenuOrder() {
        return menuOrder;
    }

    public void setMenuOrder(Integer menuOrder) {
        this.menuOrder = menuOrder;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getChildNum() {
        return childNum;
    }

    public void setChildNum(Integer childNum) {
        this.childNum = childNum;
    }

    public Integer getCreateDefault() {
        return createDefault;
    }

    public void setCreateDefault(Integer createDefault) {
        this.createDefault = createDefault;
    }

    public Integer getInitDefault() {
        return initDefault;
    }

    public void setInitDefault(Integer initDefault) {
        this.initDefault = initDefault;
    }

    public Integer getFrontSite() {
        return frontSite;
    }

    public void setFrontSite(Integer frontSite) {
        this.frontSite = frontSite;
    }

    public Integer getEndSite() {
        return endSite;
    }

    public void setEndSite(Integer endSite) {
        this.endSite = endSite;
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
        sb.append(", menuId=").append(menuId);
        sb.append(", parentId=").append(parentId);
        sb.append(", name=").append(name);
        sb.append(", pathUrl=").append(pathUrl);
        sb.append(", pathMethod=").append(pathMethod);
        sb.append(", menuLevel=").append(menuLevel);
        sb.append(", menuOrder=").append(menuOrder);
        sb.append(", createDefault=").append(createDefault);
        sb.append(", initDefault=").append(initDefault);
        sb.append(", frontSite=").append(frontSite);
        sb.append(", endSite=").append(endSite);
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
        AdminMenu other = (AdminMenu) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCts() == null ? other.getCts() == null : this.getCts().equals(other.getCts()))
            && (this.getUts() == null ? other.getUts() == null : this.getUts().equals(other.getUts()))
            && (this.getMenuId() == null ? other.getMenuId() == null : this.getMenuId().equals(other.getMenuId()))
            && (this.getParentId() == null ? other.getParentId() == null : this.getParentId().equals(other.getParentId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getPathUrl() == null ? other.getPathUrl() == null : this.getPathUrl().equals(other.getPathUrl()))
            && (this.getPathMethod() == null ? other.getPathMethod() == null : this.getPathMethod().equals(other.getPathMethod()))
            && (this.getMenuLevel() == null ? other.getMenuLevel() == null : this.getMenuLevel().equals(other.getMenuLevel()))
            && (this.getMenuOrder() == null ? other.getMenuOrder() == null : this.getMenuOrder().equals(other.getMenuOrder()))
            && (this.getCreateDefault() == null ? other.getCreateDefault() == null : this.getCreateDefault().equals(other.getCreateDefault()))
            && (this.getInitDefault() == null ? other.getInitDefault() == null : this.getInitDefault().equals(other.getInitDefault()))
            && (this.getFrontSite() == null ? other.getFrontSite() == null : this.getFrontSite().equals(other.getFrontSite()))
            && (this.getEndSite() == null ? other.getEndSite() == null : this.getEndSite().equals(other.getEndSite()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCts() == null) ? 0 : getCts().hashCode());
        result = prime * result + ((getUts() == null) ? 0 : getUts().hashCode());
        result = prime * result + ((getMenuId() == null) ? 0 : getMenuId().hashCode());
        result = prime * result + ((getParentId() == null) ? 0 : getParentId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getPathUrl() == null) ? 0 : getPathUrl().hashCode());
        result = prime * result + ((getPathMethod() == null) ? 0 : getPathMethod().hashCode());
        result = prime * result + ((getMenuLevel() == null) ? 0 : getMenuLevel().hashCode());
        result = prime * result + ((getMenuOrder() == null) ? 0 : getMenuOrder().hashCode());
        result = prime * result + ((getCreateDefault() == null) ? 0 : getCreateDefault().hashCode());
        result = prime * result + ((getInitDefault() == null) ? 0 : getInitDefault().hashCode());
        result = prime * result + ((getFrontSite() == null) ? 0 : getFrontSite().hashCode());
        result = prime * result + ((getEndSite() == null) ? 0 : getEndSite().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        return result;
    }
}