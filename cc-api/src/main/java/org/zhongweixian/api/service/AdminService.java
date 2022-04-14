package org.zhongweixian.api.service;

import com.github.pagehelper.PageInfo;
import org.cti.cc.entity.AdminMenu;
import org.cti.cc.entity.AdminUser;
import org.cti.cc.po.AdminLoginResult;
import org.cti.cc.po.RolePo;
import org.cti.cc.vo.AdminLogin;
import org.zhongweixian.api.vo.server.MenuVo;
import org.zhongweixian.api.vo.server.RoleVo;

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
     * 授权菜单
     *
     * @param ids
     * @return
     */
    int updateRoleMenus(List<Long> ids);

    /**
     * 角色列表
     *
     * @param params
     * @return
     */
    PageInfo<RolePo> getRoleList(Map<String, Object> params);


    /**
     * 添加/修改角色
     *
     * @param roleVo
     * @return
     */
    Integer saveOrUpdateRole(RoleVo roleVo);
}
