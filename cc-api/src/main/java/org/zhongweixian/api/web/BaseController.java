package org.zhongweixian.api.web;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONValidator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.bouncycastle.util.encoders.UrlBase64;
import org.bouncycastle.util.encoders.UrlBase64Encoder;
import org.cti.cc.entity.AdminUser;
import org.cti.cc.enums.ErrorCode;
import org.cti.cc.po.AdminAccountInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.zhongweixian.api.exception.BusinessException;
import org.zhongweixian.api.service.*;

import java.net.URLDecoder;
import java.nio.charset.Charset;
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
