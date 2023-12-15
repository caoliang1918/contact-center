package com.voice9.core.po;

import com.voice9.core.entity.AdminRole;

import java.util.List;

/**
 * Created by caoliang on 2022/1/27.
 */
public class RolePo extends AdminRole {

    /**
     * 菜单权限集合
     */
    private List<MenusPo> adminMenuList;

    public List<MenusPo> getAdminMenuList() {
        return adminMenuList;
    }

    public void setAdminMenuList(List<MenusPo> adminMenuList) {
        this.adminMenuList = adminMenuList;
    }
}
