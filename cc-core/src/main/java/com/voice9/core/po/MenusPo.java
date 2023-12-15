package com.voice9.core.po;

import java.util.List;

/**
 * Created by caoliang on 2022/1/6
 */
public class MenusPo {

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
     * 子节点数
     */
    private Integer childNum;

    /**
     * 图标
     */
    private String icon;

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
     * 用户id
     */
    private Long uid;

    /**
     * 子节点菜单
     */
    private List<MenusPo> childs;


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
        this.menuId = menuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getMenuLevel() {
        return menuLevel;
    }

    public void setMenuLevel(Integer menuLevel) {
        this.menuLevel = menuLevel;
    }

    public Integer getChildNum() {
        return childNum;
    }

    public void setChildNum(Integer childNum) {
        this.childNum = childNum;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getMenuOrder() {
        return menuOrder;
    }

    public void setMenuOrder(Integer menuOrder) {
        this.menuOrder = menuOrder;
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

    public List<MenusPo> getChilds() {
        return childs;
    }

    public void setChilds(List<MenusPo> childs) {
        this.childs = childs;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }
}
