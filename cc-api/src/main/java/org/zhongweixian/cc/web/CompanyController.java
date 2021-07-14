package org.zhongweixian.cc.web;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.github.pagehelper.PageInfo;
import org.apache.poi.ss.usermodel.Workbook;
import org.cti.cc.entity.*;
import org.cti.cc.enums.ErrorCode;
import org.cti.cc.page.Page;
import org.cti.cc.po.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.zhongweixian.cc.service.*;
import org.zhongweixian.cc.vo.*;
import org.zhongweixian.cc.vo.excel.ExcelAgentEntity;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create by caoliang on 2021/04/15
 */
@RestController
@RequestMapping("config")
public class CompanyController extends BaseController {
    protected Logger logger = LoggerFactory.getLogger(CompanyController.class);


    @Autowired
    private CompanyService companyService;

    @Autowired
    private AgentService agentService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private RouteService routeService;

    @Autowired
    private DisplayService displayService;


    /**
     * 1.1 企业列表
     *
     * @param pageInfo
     * @param query
     * @return
     */
    @GetMapping("company")
    public CommonResponse<Page<Company>> companyList(@ModelAttribute("adminAccountInfo") AdminAccountInfo adminAccountInfo, PageInfo pageInfo, String query) {
        Map<String, Object> params = parseMap(adminAccountInfo, pageInfo, query);
        return new CommonResponse(companyService.findByPageParams(params));
    }

    /**
     * 1.2 企业详情
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
     * 1.3 添加企业
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
     * 1.4 修改企业
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
     * 1.5 删除企业
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
     * 3.1 sip列表
     *
     * @param adminAccountInfo
     * @param pageInfo
     * @param query
     * @return
     */
    @GetMapping("sip")
    public CommonResponse<PageInfo<AgentSipPo>> sipList(@ModelAttribute("adminAccountInfo") AdminAccountInfo adminAccountInfo, PageInfo pageInfo, String query) {
        Map<String, Object> params = parseMap(adminAccountInfo, pageInfo, query);
        return new CommonResponse<PageInfo<AgentSipPo>>(agentService.agentSipList(params));
    }

    /**
     * 3.2 添加sip号码
     *
     * @param adminAccountInfo
     * @param agentSipVo
     * @return
     */
    @PostMapping("sip")
    public CommonResponse addSip(@ModelAttribute("adminAccountInfo") AdminAccountInfo adminAccountInfo, @Validated @RequestBody AgentSipVo agentSipVo) {
        agentSipVo.setCompanyId(adminAccountInfo.getBindCompanyId());
        agentSipVo.setId(null);
        agentService.saveOrUpdateAgentSip(agentSipVo);
        return new CommonResponse<>();
    }

    /**
     * 3.3 修改sip号
     *
     * @param adminAccountInfo
     * @param id
     * @param agentSipVo
     * @return
     */
    @PutMapping("sip/{id}")
    public CommonResponse updateSip(@ModelAttribute("adminAccountInfo") AdminAccountInfo adminAccountInfo,
                                    @PathVariable Long id, @Validated @RequestBody AgentSipVo agentSipVo) {
        agentSipVo.setCompanyId(adminAccountInfo.getBindCompanyId());
        agentSipVo.setId(id);
        agentService.saveOrUpdateAgentSip(agentSipVo);
        return new CommonResponse<>();
    }

    /**
     * 3.4 删除sip
     *
     * @param adminAccountInfo
     * @param id
     * @return
     */
    @DeleteMapping("sip/{id}")
    public CommonResponse deleteSip(@ModelAttribute("adminAccountInfo") AdminAccountInfo adminAccountInfo, @PathVariable Long id) {
        agentService.deleteSip(adminAccountInfo.getBindCompanyId(), id);
        return new CommonResponse<>();
    }


    /**
     * 4.1 显号列表
     *
     * @param adminAccountInfo
     * @param pageInfo
     * @param query
     * @return
     */
    @GetMapping("phone")
    public CommonResponse<PageInfo<CompanyPhone>> phoneList(@ModelAttribute("adminAccountInfo") AdminAccountInfo adminAccountInfo, PageInfo pageInfo, String query) {
        Map<String, Object> params = parseMap(adminAccountInfo, pageInfo, query);
        return new CommonResponse<PageInfo<CompanyPhone>>(displayService.findByPageParams(params));
    }

    /**
     * 4.2 添加显号
     *
     * @param adminAccountInfo
     * @param companyPhoneVo
     * @return
     */
    @PostMapping("phone")
    public CommonResponse addPhone(@ModelAttribute("adminAccountInfo") AdminAccountInfo adminAccountInfo, @Validated @RequestBody CompanyPhoneVo companyPhoneVo) {
        if (adminAccountInfo.getCompanyId() != 1) {
            companyPhoneVo.setCompanyId(adminAccountInfo.getBindCompanyId());
        }
        displayService.saveOrUpdatePhone(companyPhoneVo);
        return new CommonResponse();
    }

    /**
     * 4.3 修改显号
     *
     * @param adminAccountInfo
     * @param id
     * @param companyPhoneVo
     * @return
     */
    @PutMapping("phone/{id}")
    public CommonResponse updatePhone(@ModelAttribute("adminAccountInfo") AdminAccountInfo adminAccountInfo,
                                      @PathVariable Long id, @Validated @RequestBody CompanyPhoneVo companyPhoneVo) {
        if (adminAccountInfo.getCompanyId() != 1) {
            companyPhoneVo.setCompanyId(adminAccountInfo.getBindCompanyId());
        }
        companyPhoneVo.setId(id);
        displayService.saveOrUpdatePhone(companyPhoneVo);
        return new CommonResponse();
    }

    /**
     * 4.4 删除显号
     *
     * @param adminAccountInfo
     * @param id
     * @return
     */
    @DeleteMapping("phone/{id}")
    public CommonResponse deletePhone(@ModelAttribute("adminAccountInfo") AdminAccountInfo adminAccountInfo,
                                      @PathVariable Long id) {
        displayService.deletePhone(adminAccountInfo.getCompanyId() == 1 ? null : adminAccountInfo.getBindCompanyId(), id);
        return new CommonResponse();
    }

    /**
     * 4.5 显号池列表
     *
     * @param adminAccountInfo
     * @param pageInfo
     * @param query
     * @return
     */
    @GetMapping("display")
    public CommonResponse<PageInfo<CompanyDisplayPo>> displayGroupList(@ModelAttribute("adminAccountInfo") AdminAccountInfo adminAccountInfo, PageInfo pageInfo, String query) {
        Map<String, Object> params = parseMap(adminAccountInfo, pageInfo, query);
        return new CommonResponse<PageInfo<CompanyDisplayPo>>(displayService.findDisplayByPage(params));
    }

    /**
     * 4.6 获取池列表详情
     *
     * @param adminAccountInfo
     * @param id
     * @return
     */
    @GetMapping("display/{id}")
    public CommonResponse<CompanyDisplayPo> displayGroup(@ModelAttribute("adminAccountInfo") AdminAccountInfo adminAccountInfo, @PathVariable Long id) {
        return new CommonResponse<CompanyDisplayPo>(displayService.getDisplay(adminAccountInfo.getBindCompanyId() == 1L ? null : adminAccountInfo.getBindCompanyId(), id));
    }

    /**
     * 4.7 添加号码池
     *
     * @param adminAccountInfo
     * @param displayGroupVo
     * @return
     */
    @PostMapping("display")
    public CommonResponse displayGroupList(@ModelAttribute("adminAccountInfo") AdminAccountInfo adminAccountInfo,
                                           @Validated @RequestBody DisplayGroupVo displayGroupVo) {
        displayGroupVo.setId(null);
        if (adminAccountInfo.getCompanyId() != 1) {
            displayGroupVo.setCompanyId(adminAccountInfo.getBindCompanyId());
        }
        displayService.saveOrUpdateDisplay(displayGroupVo);
        return new CommonResponse();
    }

    /**
     * 4.8 号码池更新号码
     *
     * @param adminAccountInfo
     * @param displayGroupVo
     * @return
     */
    @PutMapping("display/{id}")
    public CommonResponse displayGroupUpdate(@ModelAttribute("adminAccountInfo") AdminAccountInfo adminAccountInfo,
                                             @PathVariable Long id, @Validated @RequestBody DisplayGroupVo displayGroupVo) {
        displayGroupVo.setId(id);
        if (adminAccountInfo.getCompanyId() != 1) {
            displayGroupVo.setCompanyId(adminAccountInfo.getBindCompanyId());
        }
        displayService.saveOrUpdateDisplay(displayGroupVo);
        return new CommonResponse();
    }

    /**
     * 4.9 删除号码池
     *
     * @param adminAccountInfo
     * @param id
     * @return
     */
    @DeleteMapping("display/{id}")
    public CommonResponse deleteDisplayGroup(@ModelAttribute("adminAccountInfo") AdminAccountInfo adminAccountInfo,
                                             @PathVariable Long id) {
        displayService.deleteDisplay(adminAccountInfo.getBindCompanyId(), id);
        return new CommonResponse();
    }


    /*******************************************************************************/
    /****************字冠、网关、网关组设置：新增加网关，再增加网关组，再增加字冠**************/
    /****************其中字冠是按企业划分，有全局的字冠，每个企业也可以新建企业字冠************/
    /*******************************************************************************/

    /**
     * 5.1 网关列表
     *
     * @param adminAccountInfo
     * @param pageInfo
     * @param query
     * @return
     */
    @GetMapping("routegetway")
    public CommonResponse<PageInfo<RouteGetway>> routeGetwayList(@ModelAttribute("adminAccountInfo") AdminAccountInfo adminAccountInfo, PageInfo pageInfo, String query) {
        Map<String, Object> params = parseMap(adminAccountInfo, pageInfo, query);
        return new CommonResponse<PageInfo<RouteGetway>>(routeService.findRouteGetwayByPage(params));
    }

    /**
     * 5.2 网关详情
     *
     * @param adminAccountInfo
     * @param id
     * @return
     */
    @GetMapping("routegetway/{id}")
    public CommonResponse<RouteGetway> routeGetway(@ModelAttribute("adminAccountInfo") AdminAccountInfo adminAccountInfo, @PathVariable Long id) {
        return new CommonResponse<RouteGetway>(routeService.findRoutewayById(id));
    }

    /**
     * 5.3 添加网关
     *
     * @param adminAccountInfo
     * @param routeGetwayVo
     * @return
     */
    @PostMapping("routegetway")
    public CommonResponse<RouteGetway> addRouteGetway(@ModelAttribute("adminAccountInfo") AdminAccountInfo adminAccountInfo,
                                                      @Validated @RequestBody RouteGetwayVo routeGetwayVo) {
        routeGetwayVo.setId(null);
        routeService.saveOrUpdateRouteGetway(routeGetwayVo);
        return new CommonResponse<>();
    }

    /**
     * 5.4 修改网关
     *
     * @param adminAccountInfo
     * @param id
     * @param routeGetwayVo
     * @return
     */
    @PutMapping("routegetway/{id}")
    public CommonResponse updateRouteGetway(@ModelAttribute("adminAccountInfo") AdminAccountInfo adminAccountInfo, @PathVariable Long id,
                                            @Validated @RequestBody RouteGetwayVo routeGetwayVo) {
        routeGetwayVo.setId(id);
        return new CommonResponse(routeService.saveOrUpdateRouteGetway(routeGetwayVo));
    }

    /**
     * 5.5 删除网关
     *
     * @param adminAccountInfo
     * @param id
     * @return
     */
    @DeleteMapping("routegetway/{id}")
    public CommonResponse delRouteGetway(@ModelAttribute("adminAccountInfo") AdminAccountInfo adminAccountInfo, @PathVariable Long id) {
        return new CommonResponse(routeService.delRoutewayById(id));
    }

    /**
     * 5.6 网关组列表
     *
     * @param adminAccountInfo
     * @param pageInfo
     * @param query
     * @return
     */
    @GetMapping("routegroup")
    public CommonResponse<PageInfo<RouteGroup>> routeGroupList(@ModelAttribute("adminAccountInfo") AdminAccountInfo adminAccountInfo, PageInfo pageInfo, String query) {
        Map<String, Object> params = parseMap(adminAccountInfo, pageInfo, query);
        return new CommonResponse<PageInfo<RouteGroup>>(routeService.findRouteGroupByPage(params));
    }

    /**
     * 5.7 网关组详情
     *
     * @param adminAccountInfo
     * @param id
     * @return
     */
    @GetMapping("routegroup/{id}")
    public CommonResponse<RouteGroupPo> routeGroup(@ModelAttribute("adminAccountInfo") AdminAccountInfo adminAccountInfo, @PathVariable Long id) {
        return new CommonResponse<RouteGroupPo>(routeService.findRouteGroup(id));
    }

    /**
     * 5.8 添加网关组
     *
     * @param adminAccountInfo
     * @param routeGroupVo
     * @return
     */
    @PostMapping("routegroup")
    public CommonResponse addRouteGroup(@ModelAttribute("adminAccountInfo") AdminAccountInfo adminAccountInfo, @Validated @RequestBody RouteGroupVo routeGroupVo) {
        routeGroupVo.setId(null);
        return new CommonResponse<>(routeService.saveOrUpdateRouteGroup(routeGroupVo));
    }

    /**
     * 5.9 修改网关组
     *
     * @param adminAccountInfo
     * @param routeGroupVo
     * @return
     */
    @PutMapping("routegroup/{id}")
    public CommonResponse updateRouteGroup(@ModelAttribute("adminAccountInfo") AdminAccountInfo adminAccountInfo, @PathVariable Long id, @Validated @RequestBody RouteGroupVo routeGroupVo) {
        routeGroupVo.setId(id);
        return new CommonResponse<>(routeService.saveOrUpdateRouteGroup(routeGroupVo));
    }

    /**
     * 5.10 删除网关组
     *
     * @param adminAccountInfo
     * @param id
     * @return
     */
    @DeleteMapping("routegroup/{id}")
    public CommonResponse deleteRouteGroup(@ModelAttribute("adminAccountInfo") AdminAccountInfo adminAccountInfo, @PathVariable Long id) {
        return new CommonResponse<>(routeService.deleteRouteGroup(id));
    }

    /**
     * 5.11 子冠列表
     *
     * @param adminAccountInfo
     * @param pageInfo
     * @param query
     * @return
     */
    @GetMapping("routecall")
    public CommonResponse<PageInfo<RouteCallInfo>> routeCallList(@ModelAttribute("adminAccountInfo") AdminAccountInfo adminAccountInfo, PageInfo pageInfo, String query) {
        Map<String, Object> params = parseMap(adminAccountInfo, pageInfo, query);
        return new CommonResponse<PageInfo<RouteCallInfo>>(routeService.findRouteCallByPage(params));
    }

    /**
     * 5.12 字冠详情
     *
     * @param adminAccountInfo
     * @param id
     * @return
     */
    @GetMapping("routecall/{id}")
    public CommonResponse<RouteCallInfo> routeCallList(@ModelAttribute("adminAccountInfo") AdminAccountInfo adminAccountInfo, @PathVariable Long id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("companyId", adminAccountInfo.getBindCompanyId());
        return new CommonResponse<RouteCallInfo>(routeService.findRouteCall(params));
    }

    /**
     * 5.13 新增字冠
     *
     * @param adminAccountInfo
     * @param routeCallVo
     * @return
     */
    @PostMapping("routecall")
    public CommonResponse addRouteCall(@ModelAttribute("adminAccountInfo") AdminAccountInfo adminAccountInfo,
                                       @Validated @RequestBody RouteCallVo routeCallVo) {
        if (adminAccountInfo.getCompanyId() != 1) {
            routeCallVo.setCompanyId(adminAccountInfo.getBindCompanyId());
        }
        routeCallVo.setId(null);
        return new CommonResponse(routeService.saveOrUpdateRouteCall(routeCallVo));
    }

    /**
     * 5.14 修改字冠
     *
     * @param adminAccountInfo
     * @param routeCallVo
     * @return
     */
    @PutMapping("routecall/{id}")
    public CommonResponse updateRouteCall(@ModelAttribute("adminAccountInfo") AdminAccountInfo adminAccountInfo, @PathVariable Long id,
                                          @Validated @RequestBody RouteCallVo routeCallVo) {
        routeCallVo.setCompanyId(adminAccountInfo.getBindCompanyId());
        routeCallVo.setId(id);
        return new CommonResponse(routeService.saveOrUpdateRouteCall(routeCallVo));
    }

    /**
     * 5.15 删除字冠
     *
     * @param adminAccountInfo
     * @param id
     * @return
     */
    @DeleteMapping("routecall/{id}")
    public CommonResponse deleteRouteCall(@ModelAttribute("adminAccountInfo") AdminAccountInfo adminAccountInfo, @PathVariable Long id) {
        return new CommonResponse(routeService.deleteRouteCall(adminAccountInfo.getBindCompanyId(), id));
    }

    /**
     * 6.1 技能组列表
     *
     * @param adminAccountInfo
     * @param pageInfo
     * @param query
     * @return
     */
    @GetMapping("group")
    public CommonResponse<Page<GroupInfo>> groupList(@ModelAttribute("adminAccountInfo") AdminAccountInfo adminAccountInfo, PageInfo pageInfo, String query) {
        Map<String, Object> params = parseMap(adminAccountInfo, pageInfo, query);
        return new CommonResponse(groupService.findByPageParams(params));
    }

    /**
     * 6.2 技能组详情
     *
     * @param adminAccountInfo
     * @param id
     * @return
     */
    @GetMapping("group/{id}")
    public CommonResponse<GroupInfo> groupInfo(@ModelAttribute("adminAccountInfo") AdminAccountInfo adminAccountInfo, @PathVariable Long id) {
        return new CommonResponse(groupService.getGroupInfo(adminAccountInfo.getCompanyId() == 1L ? null : adminAccountInfo.getCompanyId(), id));
    }

    /**
     * 6.3 添加技能组
     *
     * @param adminAccountInfo
     * @param groupInfoVo
     * @return
     */
    @PostMapping("group")
    public CommonResponse addGroup(@ModelAttribute("adminAccountInfo") AdminAccountInfo adminAccountInfo,
                                   @Validated @RequestBody GroupInfoVo groupInfoVo) {
        groupInfoVo.setCompanyId(adminAccountInfo.getBindCompanyId());
        groupInfoVo.setId(null);
        return new CommonResponse(groupService.saveOrUpdateGroup(groupInfoVo));
    }

    /**
     * 6.4 修改技能组
     *
     * @param adminAccountInfo
     * @param groupInfoVo
     * @return
     */
    @PutMapping("group/{id}")
    public CommonResponse updateGroup(@ModelAttribute("adminAccountInfo") AdminAccountInfo adminAccountInfo,
                                      @PathVariable Long id, @Validated @RequestBody GroupInfoVo groupInfoVo) {
        groupInfoVo.setId(id);
        groupInfoVo.setCompanyId(adminAccountInfo.getBindCompanyId());
        return new CommonResponse(groupService.saveOrUpdateGroup(groupInfoVo));
    }

    /**
     * 6.5 删除技能组
     *
     * @param adminAccountInfo
     * @param id
     * @return
     */
    @DeleteMapping("group/{id}")
    public CommonResponse updateGroup(@ModelAttribute("adminAccountInfo") AdminAccountInfo adminAccountInfo, @PathVariable Long id) {
        return new CommonResponse(groupService.deleteGroup(adminAccountInfo.getBindCompanyId(), id));
    }

    /**
     * 8.1 坐席列表
     *
     * @param adminAccountInfo
     * @param pageInfo
     * @param query
     * @return
     */
    @GetMapping("agent")
    public CommonResponse<PageInfo<Agent>> agentList(@ModelAttribute("adminAccountInfo") AdminAccountInfo adminAccountInfo, PageInfo pageInfo, String query) {
        Map<String, Object> params = parseMap(adminAccountInfo, pageInfo, query);
        return new CommonResponse(agentService.findByPageParams(params));
    }

    /**
     * 8.2 坐席详情
     *
     * @param adminAccountInfo
     * @param id
     * @return
     */
    @GetMapping("agent/{id}")
    public CommonResponse<AgentInfo> agentInfo(@ModelAttribute("adminAccountInfo") AdminAccountInfo adminAccountInfo, @PathVariable Long id) {
        return new CommonResponse(agentService.getAgentInfo(adminAccountInfo.getCompanyId() == 1L ? null : adminAccountInfo.getCompanyId(), id));
    }

    /**
     * 8.3 添加坐席
     *
     * @param adminAccountInfo
     * @param agentVo
     * @return
     */
    @PostMapping("agent")
    public CommonResponse addAgent(@ModelAttribute("adminAccountInfo") AdminAccountInfo adminAccountInfo, @Validated @RequestBody AgentVo agentVo) {
        agentVo.setCompanyId(adminAccountInfo.getBindCompanyId());
        agentVo.setId(null);
        agentService.saveOrUpdate(agentVo);
        return new CommonResponse<>();
    }

    /**
     * 8.4 修改坐席
     *
     * @param adminAccountInfo
     * @param id
     * @return
     */
    @PutMapping("agent/{id}")
    public CommonResponse updateAgent(@ModelAttribute("adminAccountInfo") AdminAccountInfo adminAccountInfo, @PathVariable Long id, @Validated @RequestBody AgentVo agentVo) {
        agentVo.setCompanyId(adminAccountInfo.getBindCompanyId());
        agentVo.setId(id);
        agentService.saveOrUpdate(agentVo);
        return new CommonResponse<>();
    }

    /**
     * 8.5 删除坐席
     *
     * @param adminAccountInfo
     * @param id
     * @return
     */
    @DeleteMapping("agent/{id}")
    public CommonResponse deleteAgent(@ModelAttribute("adminAccountInfo") AdminAccountInfo adminAccountInfo, @PathVariable Long id) {
        agentService.deleteAgent(adminAccountInfo.getBindCompanyId(), id);
        return new CommonResponse();
    }


    /**
     * 8.6 批量添加坐席
     *
     * @param adminAccountInfo
     * @param count
     * @param start
     * @return
     */
    @PostMapping("agent/batch")
    public CommonResponse batchAddAgent(@ModelAttribute("adminAccountInfo") AdminAccountInfo adminAccountInfo,
                                        @RequestParam Integer count, @RequestParam String prefix,
                                        @RequestParam Long start, @RequestParam String pwd) {
        Integer result = agentService.batchAddAgent(adminAccountInfo.getBindCompanyId(), count, prefix, start, pwd);
        return new CommonResponse<>(result);
    }

    /**
     * 8.7 坐席导出
     *
     * @param response
     * @param adminAccountInfo
     * @param pageInfo
     * @param query
     * @throws IOException
     */
    @GetMapping("agentExport")
    public void agentExport(HttpServletResponse response,
                            @ModelAttribute("adminAccountInfo") AdminAccountInfo adminAccountInfo,
                            PageInfo pageInfo, String query) throws IOException {
        Map<String, Object> params = parseMap(adminAccountInfo, pageInfo, query);
        List<Agent> agentList = agentService.findByPageParams(params).getList();
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(
                null, "坐席列表", ExcelType.XSSF), ExcelAgentEntity.class, agentList);
        String filename = URLEncoder.encode("坐席列表.xlsx", "UTF8");
        response.setHeader("content-disposition", "attachment;Filename=" + filename);
        response.setContentType("application/vnd.ms-excel");
        ServletOutputStream out = response.getOutputStream();
        workbook.write(out);
        out.flush();
    }


    /**
     * 技能中添加坐席
     *
     * @param adminAccountInfo
     * @param skillId
     * @param rank
     * @param agentIds
     * @return
     */
    @PostMapping("agent/bindskill/{skillId}")
    public CommonResponse agentSindSkill(@ModelAttribute("adminAccountInfo") AdminAccountInfo adminAccountInfo,
                                         @PathVariable Long skillId, @RequestParam Integer rank, @RequestBody List<Long> agentIds) {

        return new CommonResponse();
    }


}
