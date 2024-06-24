package com.voice9.api.web;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.voice9.core.entity.AdminRole;
import com.voice9.core.entity.Company;
import com.voice9.core.entity.SipGateway;
import com.voice9.core.enums.ErrorCode;
import com.voice9.core.po.AdminAccountInfo;
import com.voice9.core.po.CommonResponse;
import com.voice9.core.po.CompanyInfo;
import com.voice9.core.po.RolePo;
import com.voice9.core.vo.CompanyVo;
import com.voice9.core.vo.SipGatewayReq;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.voice9.api.vo.server.MenuVo;
import com.voice9.api.vo.server.RoleMenuVo;
import com.voice9.api.vo.server.RoleVo;

import java.util.List;
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
    public CommonResponse<Page<AdminRole>> getRoleList(@ModelAttribute("adminAccountInfo") AdminAccountInfo adminAccountInfo, PageInfo pageInfo, String query) {
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
     * 1.3.3 获取角色菜单
     *
     * @param adminAccountInfo
     * @param id
     * @return
     */
    @GetMapping("role/{id}")
    public CommonResponse<RolePo> getRole(@ModelAttribute("adminAccountInfo") AdminAccountInfo adminAccountInfo, @PathVariable Long id) {
        return new CommonResponse<>(adminService.getRoleMenus(id));

    }

    /**
     * 1.3.4 角色绑定菜单
     *
     * @param adminAccountInfo
     * @return
     */
    @PostMapping("roleMenu")
    public CommonResponse roleMenu(@ModelAttribute("adminAccountInfo") AdminAccountInfo adminAccountInfo, @Validated @RequestBody RoleMenuVo roleMenuVo) {
        return new CommonResponse(adminService.roleBindMenu(roleMenuVo));
    }

    /**
     * 1.3.5 删除角色
     *
     * @param adminAccountInfo
     * @param id
     * @return
     */
    @DeleteMapping("role/{id}")
    public CommonResponse deleteRole(@ModelAttribute("adminAccountInfo") AdminAccountInfo adminAccountInfo, @PathVariable Long id) {
        return new CommonResponse(adminService.deleteRole(id));
    }


    /**
     * 1.4.1 企业列表
     *
     * @param pageInfo
     * @param query
     * @return
     */
    @GetMapping("company")
    public CommonResponse<com.voice9.core.page.Page<Company>> companyList(@ModelAttribute("adminAccountInfo") AdminAccountInfo adminAccountInfo, PageInfo pageInfo, String query) {
        Map<String, Object> params = parseMap(adminAccountInfo, pageInfo, query);
        return new CommonResponse(companyService.findByPageParams(params));
    }

    /**
     * 1.4.2 企业详情
     *
     * @param adminAccountInfo
     * @param id
     * @return
     */
    @GetMapping("company/{id}")
    public CommonResponse<CompanyInfo> getCompany(@ModelAttribute("adminAccountInfo") AdminAccountInfo adminAccountInfo, @PathVariable Long id) {
        if (adminAccountInfo.getCompanyId() != 1) {
            return new CommonResponse(ErrorCode.ACCOUNT_AUTH_ERROR);
        }
        return new CommonResponse(companyService.getCompanyInfo(adminAccountInfo.getCompanyId() == 1L ? id : adminAccountInfo.getCompanyId()));
    }

    /**
     * 1.4.3 添加企业
     *
     * @param adminAccountInfo
     * @param companyVo
     * @return
     */
    @PostMapping("company")
    public CommonResponse addCompany(@ModelAttribute("adminAccountInfo") AdminAccountInfo adminAccountInfo, @Validated @RequestBody CompanyVo companyVo) {
        companyService.addCompany(companyVo);
        return new CommonResponse();
    }

    /**
     * 1.4.4 修改企业
     *
     * @param adminAccountInfo
     * @param company
     * @return
     */
    @PutMapping("company/{id}")
    public CommonResponse updateCompany(@ModelAttribute("adminAccountInfo") AdminAccountInfo adminAccountInfo, @PathVariable Long id, @Validated @RequestBody Company company) {
        if (adminAccountInfo.getCompanyId() != 1) {
            return new CommonResponse(ErrorCode.ACCOUNT_AUTH_ERROR);
        }
        company.setId(id);
        companyService.updateCompany(company);
        return new CommonResponse();
    }


    /**
     * 1.4.5 删除企业
     *
     * @param adminAccountInfo
     * @return
     */
    @DeleteMapping("company/{id}")
    public CommonResponse deleteCompany(@ModelAttribute("adminAccountInfo") AdminAccountInfo adminAccountInfo, @PathVariable Long id) {
        if (adminAccountInfo.getCompanyId() != 1) {
            return new CommonResponse(ErrorCode.ACCOUNT_AUTH_ERROR);
        }
        return new CommonResponse(companyService.deleteCompany(id));
    }


    /**
     * 1.11.1 sip网关列表
     *
     * @param adminAccountInfo
     * @param pageInfo
     * @param query
     * @return
     */
    @GetMapping("sipGateway")
    public CommonResponse<PageInfo<SipGateway>> sipGatewayList(@ModelAttribute("adminAccountInfo") AdminAccountInfo adminAccountInfo, PageInfo pageInfo, String query) {
        Map<String, Object> params = parseMap(adminAccountInfo, pageInfo, query);
        return new CommonResponse(adminService.sipGatewayList(params));
    }

    /**
     * 1.11.2 添加或修改sip网关
     *
     * @param adminAccountInfo
     * @param sipGatewayReq
     * @return
     */
    @PostMapping("sipGateway")
    public CommonResponse saveOrUpdateSipGateway(@ModelAttribute("adminAccountInfo") AdminAccountInfo adminAccountInfo, @RequestBody @Validated SipGatewayReq sipGatewayReq) {
        return new CommonResponse(adminService.saveOrUpdateSipGateway(sipGatewayReq));
    }

    /**
     * 1.11.3 删除sip网关
     *
     * @param adminAccountInfo
     * @param ids
     * @return
     */
    @DeleteMapping("sipGateway")
    public CommonResponse deleteSipGateway(@ModelAttribute("adminAccountInfo") AdminAccountInfo adminAccountInfo, @RequestBody List<Long> ids) {
        return new CommonResponse(adminService.deleteSipGateway(ids));
    }
}
