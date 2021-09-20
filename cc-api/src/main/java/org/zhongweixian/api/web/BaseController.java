package org.zhongweixian.api.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.cti.cc.entity.AdminAccount;
import org.cti.cc.po.AdminAccountInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.zhongweixian.api.service.*;

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

    @ModelAttribute("adminAccountInfo")
    public AdminAccount adminAccountInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return null;
        }
        logger.info("authentication:{}", authentication);
        return (AdminAccount) authentication.getPrincipal();
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
            params.put("pageNum", pageInfo.getPageNum());
            params.put("pageSize", pageInfo.getPageSize() == 0 ? 20 : pageInfo.getPageSize());
        }
        return params;
    }
}
