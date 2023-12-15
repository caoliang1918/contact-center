package org.voice9.cc.configration.interceptor;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import com.voice9.core.entity.AdminUser;
import com.voice9.core.enums.ErrorCode;
import com.voice9.core.po.AgentInfo;
import com.voice9.core.po.CommonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.voice9.cc.cache.CacheService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Create by caoliang on 2020/9/20
 */
public class HttpRequestInteceptor implements HandlerInterceptor {
    private Logger logger = LoggerFactory.getLogger(HttpRequestInteceptor.class);


    private ApplicationContext applicationContext;


    private CacheService cacheService;

    public HttpRequestInteceptor(CacheService cacheService, ApplicationContext applicationContext) {
        this.cacheService = cacheService;
        this.applicationContext = applicationContext;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean result = false;
        String path = request.getServletPath();
        String method = request.getMethod();
        logger.info("request path:{}, method:{}", path, method);
        String adminToken = request.getHeader("adminToken");
        String token = request.getHeader("token");

        if (path.startsWith("/cti/admin")) {
            if (StringUtils.isBlank(adminToken)) {
                set403(response);
                return false;
            }
        }

        if (checkAdminToken(adminToken)) {
            return true;
        }
        if (checkToken(token)) {
            return true;
        }
        set403(response);
        return result;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }


    /**
     * @param token
     * @return
     * @throws IOException
     */
    private Boolean checkToken(String token) throws IOException {
        if (StringUtils.isBlank(token)) {
            return false;
        }
        Object agentKey = cacheService.getAgentKey(token);
        if (agentKey == null) {
            return false;
        }
        AgentInfo agentInfo = cacheService.getAgentInfo(String.valueOf(agentKey));
        if (agentInfo == null) {
            return false;
        }
        cacheService.refleshAgentToken(agentInfo.getAgentKey(), token);
        PreAuthenticatedAuthenticationToken authenticationToken = new PreAuthenticatedAuthenticationToken(agentInfo, agentInfo.getPasswd(), null);
        authenticationToken.setAuthenticated(true);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        return true;
    }


    /**
     * @param adminToken
     * @return
     */
    private Boolean checkAdminToken(String adminToken) {
        if (StringUtils.isBlank(adminToken)) {
            return false;
        }
        AdminUser adminUser = new AdminUser();
        adminUser.setCompanyId(1L);
        adminUser.setCode("test");
        PreAuthenticatedAuthenticationToken authenticationToken = new PreAuthenticatedAuthenticationToken(adminUser, adminUser.getPasswd(), null);
        authenticationToken.setAuthenticated(true);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        return true;
    }

    private void set403(HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        logger.warn("response for 401 status");
        PrintWriter writer = response.getWriter();
        writer.write(JSON.toJSONString(new CommonResponse(ErrorCode.ACCOUNT_NOT_LOGIN)));
        writer.flush();
        writer.close();
    }
}
