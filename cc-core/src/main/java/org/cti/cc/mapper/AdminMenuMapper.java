package org.cti.cc.mapper;

import org.cti.cc.entity.AdminMenu;
import org.cti.cc.mapper.base.BaseMapper;

import java.util.List;
import java.util.Map;

public interface AdminMenuMapper extends BaseMapper<AdminMenu> {


    /**
     * 菜单列表
     *
     * @param params
     * @return
     */
    List<AdminMenu> menusList(Map<String, Object> params);

    /**
     *
     * @param menuId
     * @return
     */
    Integer selectChildMenus(String menuId);

    /**
     * @param params 获取用户权限菜单
     * @return
     */
    List<AdminMenu> selectRoleMenus(Map<String, Object> params);

    /**
     * 登录时返回的菜单列表
     *
     * @param params
     * @return
     */
    List<AdminMenu> selectUserMenus(Map<String, Object> params);


    List<AdminMenu> selectByRoleId(Long roleId);


    Integer deleteMenus(String menuId);


    AdminMenu selectByMenuId(String menuId);
}