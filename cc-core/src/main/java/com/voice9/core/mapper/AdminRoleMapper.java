package com.voice9.core.mapper;

import com.voice9.core.entity.AdminRole;
import com.voice9.core.entity.AdminRoleMenu;
import com.voice9.core.entity.AdminUser;
import com.voice9.core.mapper.base.BaseMapper;
import com.voice9.core.po.RolePo;

import java.util.List;
import java.util.Map;

public interface AdminRoleMapper extends BaseMapper<AdminRole> {

    /**
     * 查询角色权限
     * @param roleId
     * @return
     */
    RolePo selectRoleMenu(Long roleId);
    /**
     * 条件查询
     *
     * @param params
     * @return
     */
    List<AdminRole> selectList(Map<String, Object> params);

    /**
     * 删除角色菜单
     *
     * @param roleId
     * @return
     */
    Integer deleteMenuByRoleId(Long roleId);


    /**
     * @param adminRoleMenus
     * @return
     */
    Integer batchInserRoleMenus(List<AdminRoleMenu> adminRoleMenus);


    /**
     * 删除角色
     *
     * @param id
     * @return
     */
    Integer deleteRole(Long id);


    /**
     * 根据角色查询账号
     *
     * @param roleId
     * @return
     */
    List<AdminUser> selectByRoleId(Long roleId);
}