package org.zhongweixian.api.web;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.cti.cc.po.AdminAccountInfo;
import org.cti.cc.po.CommonResponse;
import org.cti.cc.po.RolePo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.zhongweixian.api.vo.server.MenuVo;
import org.zhongweixian.api.vo.server.RoleVo;

import java.util.Map;

/**
 * Created by caoliang on 2022/1/6
 * <p>
 * 超管操作
 */
@RestController
@RequestMapping("admin")
public class AdminController extends BaseController {

    /**
     * 1.2.1 菜单列表
     *
     * @param adminAccountInfo
     * @return
     */
    @GetMapping("menu")
    public CommonResponse menusList(@ModelAttribute("adminAccountInfo") AdminAccountInfo adminAccountInfo, String query) {
        Map<String, Object> params = parseMap(adminAccountInfo, null, query);
        return new CommonResponse(adminService.menusList(params));
    }

    /**
     * 1.2.3 添加/修改菜单
     *
     * @param adminAccountInfo
     * @param menusVo
     * @return
     */

    @PostMapping("menu")
    public CommonResponse saveOrUpdateMenus(@ModelAttribute("adminAccountInfo") AdminAccountInfo adminAccountInfo, @Validated @RequestBody MenuVo menusVo) {
        return new CommonResponse(adminService.saveOrUpdateMenus(menusVo));
    }

    /**
     * 1.2.4 删除菜单
     *
     * @param adminAccountInfo
     * @param menuId
     * @return
     */
    @DeleteMapping("menu/{menuId}")
    public CommonResponse deleteMenus(@ModelAttribute("adminAccountInfo") AdminAccountInfo adminAccountInfo, @PathVariable String menuId) {
        return new CommonResponse(adminService.deleteMenus(menuId));
    }


    /**
     * 1.3.1 角色列表
     *
     * @param adminAccountInfo
     * @param pageInfo
     * @param query
     * @return
     */
    @GetMapping("role")
    public CommonResponse<Page<RolePo>> getRoleList(@ModelAttribute("adminAccountInfo") AdminAccountInfo adminAccountInfo, PageInfo pageInfo, String query) {
        Map<String, Object> params = parseMap(adminAccountInfo, pageInfo, query);
        return new CommonResponse(adminService.getRoleList(params));
    }


    /**
     * 1.3.2 添加/修改角色
     *
     * @param adminAccountInfo
     * @return
     */
    @PostMapping("role")
    public CommonResponse addRole(@ModelAttribute("adminAccountInfo") AdminAccountInfo adminAccountInfo, @Validated @RequestBody RoleVo roleVo) {
        return new CommonResponse(adminService.saveOrUpdateRole(roleVo));
    }

    /**
     * 1.3.3 角色绑定菜单
     *
     * @param adminAccountInfo
     * @return
     */
    @PostMapping("roleMenu")
    public CommonResponse roleMenu(@ModelAttribute("adminAccountInfo") AdminAccountInfo adminAccountInfo) {
        return new CommonResponse();
    }


}
