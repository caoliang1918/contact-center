package com.voice9.api.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import com.voice9.api.exception.BusinessException;
import com.voice9.api.service.*;
import com.voice9.core.entity.AdminUser;
import com.voice9.core.enums.ErrorCode;
import com.voice9.core.po.AdminAccountInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.HashMap;
import java.util.Map;

/**
 * Create by caoliang on 2020/12/15
 */
public class BaseController {
    protected Logger logger = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    protected CompanyService companyService;

    @Autowired
    protected AgentService agentService;

    @Autowired
    protected GroupService groupService;

    @Autowired
    protected RouteService routeService;

    @Autowired
    protected DisplayService displayService;

    @Autowired
    protected CallLogService callLogService;

    @Autowired
    protected AdminService adminService;


    @Autowired
    protected SkillService skillService;

    @ModelAttribute("adminAccountInfo")
    public AdminUser adminAccountInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new BusinessException(ErrorCode.ACCOUNT_AUTH_ERROR);
        }
        return (AdminUser) authentication.getPrincipal();
    }


    protected Map<String, Object> parseMap(AdminAccountInfo adminAccountInfo, PageInfo pageInfo, String query) {
        Map<String, Object> params = null;
        if (StringUtils.isNotBlank(query)) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                params = mapper.readValue(query, Map.class);
                logger.info("query params:{}", query);
            } catch (Exception e) {
                logger.error("query format to json error, {}", query);
            }
        }
        if (params == null) {
            params = new HashMap<>();
        }
        if (adminAccountInfo.getCompanyId() == 1L) {
            if (params.containsKey("companyId") && "0".equals(params.get("companyId"))) {
                params.remove("companyId");
            } else {
                params.put("companyId", params.get("companyId"));
            }
        } else {
            params.put("companyId", adminAccountInfo.getBindCompanyId());
        }
        if (pageInfo != null) {
            params.put("pageNum", pageInfo.getPageNum() == 0 ? 1 : pageInfo.getPageNum());
            params.put("pageSize", pageInfo.getPageSize() == 0 ? 20 : pageInfo.getPageSize());
        }
        return params;
    }
}
