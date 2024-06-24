package com.voice9.api.service;

import com.github.pagehelper.PageInfo;
import com.voice9.core.entity.AdminMenu;
import com.voice9.core.entity.AdminRole;
import com.voice9.core.entity.AdminUser;
import com.voice9.core.entity.SipGateway;
import com.voice9.core.po.AdminLoginResult;
import com.voice9.core.po.RolePo;
import com.voice9.core.vo.AdminLogin;
import com.voice9.api.vo.server.MenuVo;
import com.voice9.api.vo.server.RoleMenuVo;
import com.voice9.api.vo.server.RoleVo;
import com.voice9.core.vo.SipGatewayReq;

import java.util.List;
import java.util.Map;

/**
 * Created by caoliang on 2022/1/6
 */
public interface AdminService extends BaseService<AdminUser> {

    /**
     * 账号登录
     *
     * @param adminLogin
     * @return
     */
    AdminLoginResult login(AdminLogin adminLogin);

    /**
     * @param token
     * @return
     */
    Boolean logout(String token);

    /**
     * 获取菜单列表
     *
     * @return
     */
    List<AdminMenu> menusList(Map<String, Object> params);

    /**
     * 添加/修改菜单
     *
     * @param menusVo
     * @return
     */
    Integer saveOrUpdateMenus(MenuVo menusVo);

    /**
     * 删除菜单
     *
     * @param menuId
     * @return
     */
    Integer deleteMenus(String menuId);

    /**
     * 获取角色菜单
     *
     * @param id
     * @return
     */
    RolePo getRoleMenus(Long id);

    /**
     * 角色列表
     *
     * @param params
     * @return
     */
    PageInfo<AdminRole> getRoleList(Map<String, Object> params);


    /**
     * 添加/修改角色
     *
     * @param roleVo
     * @return
     */
    Integer saveOrUpdateRole(RoleVo roleVo);

    /**
     * @param id
     * @return
     */
    Integer deleteRole(Long id);

    /**
     * 角色绑定菜单
     *
     * @param roleMenuVo
     * @return
     */
    Integer roleBindMenu(RoleMenuVo roleMenuVo);


    /**
     * sip网关
     *
     * @param params
     * @return
     */
    PageInfo<SipGateway> sipGatewayList(Map<String, Object> params);

    /**
     * 添加或修改sip网关
     *
     * @param sipGatewayReq
     * @return
     */
    Long saveOrUpdateSipGateway(SipGatewayReq sipGatewayReq);

    /**
     * 删除sip网关
     *
     * @param ids
     * @return
     */
    int deleteSipGateway(List<Long> ids);


}
