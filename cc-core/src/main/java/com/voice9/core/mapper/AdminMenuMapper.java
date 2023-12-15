package com.voice9.core.mapper;

import com.voice9.core.entity.AdminMenu;
import com.voice9.core.mapper.base.BaseMapper;

import java.util.List;
import java.util.Map;

public interface AdminMenuMapper extends BaseMapper<AdminMenu> {


    /**
     * 菜单列表
     *
     * @param params
     * @return
     */
    List<AdminMenu> selectList(Map<String, Object> params);

    /**
     * 子菜单
     *
     * @param menuId
     * @return
     */
    Integer selectChildMenus(String menuId);

    /**
     * 登录时返回的菜单列表
     *
     * @param params
     * @return
     */
    List<AdminMenu> selectUserMenus(Map<String, Object> params);

    /**
     * 根据角色返回菜单
     *
     * @param roleId
     * @return
     */
    List<String> selectByRoleId(Long roleId);


    /**
     * 删除菜单
     * @param menuId
     * @return
     */
    Integer deleteMenu(String menuId);

    /**
     * 删除角色绑定的菜单
     * @param menuId
     * @return
     */
    Integer deleteRoleMenu(String menuId);


    /**
     * 获取子节点菜单
     *
     * @param menuId
     * @return
     */
    AdminMenu selectByMenuId(String menuId);

}