package com.voice9.api.vo.server;

import javax.validation.constraints.NotNull;

/**
 * Created by caoliang on 2022/4/5
 */
public class MenuVo {

    @NotNull
    private String name;

    private String menuId;

    private String icon;

    private Integer menuLevel;

    private String pathUrl;

    private String pathMethod;

    @NotNull
    private Integer frontSite;

    @NotNull
    private Integer endSite;

    @NotNull
    private Integer menuOrder;

    private String parentId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getMenuLevel() {
        return menuLevel;
    }

    public void setMenuLevel(Integer menuLevel) {
        this.menuLevel = menuLevel;
    }

    public String getPathUrl() {
        return pathUrl;
    }

    public void setPathUrl(String pathUrl) {
        this.pathUrl = pathUrl;
    }

    public String getPathMethod() {
        return pathMethod;
    }

    public void setPathMethod(String pathMethod) {
        this.pathMethod = pathMethod;
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

    public Integer getMenuOrder() {
        return menuOrder;
    }

    public void setMenuOrder(Integer menuOrder) {
        this.menuOrder = menuOrder;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
