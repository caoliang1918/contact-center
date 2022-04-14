package org.cti.cc.po;

import org.cti.cc.entity.AdminMenu;
import org.cti.cc.entity.AdminRole;

import java.util.List;

/**
 * Created by caoliang on 2022/1/27.
 */
public class RolePo extends AdminRole {

    /**
     * 菜单权限集合
     */
    private List<AdminMenu> adminMenuList;

    public List<AdminMenu> getAdminMenuList() {
        return adminMenuList;
    }

    public void setAdminMenuList(List<AdminMenu> adminMenuList) {
        this.adminMenuList = adminMenuList;
    }
}
