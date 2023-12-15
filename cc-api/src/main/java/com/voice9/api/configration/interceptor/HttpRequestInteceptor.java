package com.voice9.api.configration.interceptor;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import com.voice9.core.entity.AdminUser;
import com.voice9.core.mapper.AdminUserMapper;
import com.voice9.core.po.AdminAccountInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Create by caoliang on 2020/9/20
 */
public class HttpRequestInteceptor implements HandlerInterceptor {
    private Logger logger = LoggerFactory.getLogger(HttpRequestInteceptor.class);

    private AdminUserMapper adminUserMapper;

    public HttpRequestInteceptor(AdminUserMapper adminUserMapper) {
        this.adminUserMapper = adminUserMapper;
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean result = false;
        String path = request.getServletPath();
        String method = request.getMethod();
        logger.info("request path:{}, method:{}", path, method);
        String auth = request.getHeader("Authorization");
        String token = request.getHeader("token");

        AdminAccountInfo adminAccount = new AdminAccountInfo();
        adminAccount.setCompanyId(1L);
        adminAccount.setBindCompanyId(1L);

        PreAuthenticatedAuthenticationToken authenticationToken = new PreAuthenticatedAuthenticationToken(adminAccount, adminAccount.getPasswd(), null);
        authenticationToken.setAuthenticated(true);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        if (adminAccount != null) {
            return true;
        }

        if (StringUtils.isEmpty(auth) && StringUtils.isEmpty(token)) {
            response.setStatus(401);
            response.setHeader(HttpHeaders.WWW_AUTHENTICATE, "Basic realm=\"input username and password\"");
            logger.warn("response for 401 status");
            return result;
        }

        if (StringUtils.isNotBlank(auth) && checkAuth(auth, request, response)) {
            return true;
        }

        if (StringUtils.isNotBlank(token) && checkToken(token, request, response)) {
            return true;
        }
        return result;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    /**
     * @param auth
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    private Boolean checkAuth(String auth, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String[] userAndPass = new String(Base64.decodeBase64(auth.split(" ")[1])).split(":");
        if (userAndPass.length < 2) {
            response.setStatus(401);
            response.setHeader(HttpHeaders.WWW_AUTHENTICATE, "Basic realm=\"input username and password\"");
            logger.warn("response for 401 status");
            return true;
        }
        logger.info("username:{} , password:{}", userAndPass[0], userAndPass[1]);
        AdminUser adminUser = adminUserMapper.selectByPrimaryKey(1L);
        /*if (agentInfo == null || agentInfo.getStatus() == 0 || !BcryptUtil.checkPwd(userAndPass[1], agentInfo.getPasswd())) {
            response.setStatus(401);
            response.setHeader("Content-Type", "application/json;charset=UTF-8");
            logger.warn("response for 401 status");
            PrintWriter writer = response.getWriter();
            writer.write(JSON.toJSONString(new CommonResponse(ErrorCode.ACCOUNT_ERROR)));
            writer.flush();
            writer.close();
            return true;
        }
        logger.info("agentInfo:{}", agentInfo);
        PreAuthenticatedAuthenticationToken authenticationToken = new PreAuthenticatedAuthenticationToken(agentInfo, agentInfo.getPasswd(), null);
        authenticationToken.setAuthenticated(true);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);*/
        return true;
    }


    /**
     * @param token
     * @param request
     * @param response
     * @return
     */
    private Boolean checkToken(String token, HttpServletRequest request, HttpServletResponse response) {
        if (StringUtils.isBlank(token)) {
            return false;
        }

        AdminUser adminUser = new AdminUser();
        adminUser.setCompanyId(1L);
        PreAuthenticatedAuthenticationToken authenticationToken = new PreAuthenticatedAuthenticationToken(adminUser, adminUser.getPasswd(), null);
        authenticationToken.setAuthenticated(true);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        return true;
    }
}
