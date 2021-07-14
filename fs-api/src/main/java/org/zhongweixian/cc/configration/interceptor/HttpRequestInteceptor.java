package org.zhongweixian.cc.configration.interceptor;

import com.alibaba.fastjson.JSON;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.cti.cc.entity.AdminAccount;
import org.cti.cc.enums.ErrorCode;
import org.cti.cc.po.AgentInfo;
import org.cti.cc.po.CommonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.zhongweixian.cc.service.AgentService;
import org.zhongweixian.cc.util.BcryptUtil;

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


    private AgentService agentService;

    public HttpRequestInteceptor(AgentService agentService, ApplicationContext applicationContext) {
        this.agentService = agentService;
        this.applicationContext = applicationContext;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean result = false;
        String path = request.getServletPath();
        String method = request.getMethod();
        logger.info("request path:{}, method:{}", path, method);
        String auth = request.getHeader("Authorization");
        String token = request.getHeader("token");
        if (StringUtils.isEmpty(auth) && StringUtils.isEmpty(token)) {
            response.setStatus(401);
            response.setHeader("WWW-Authenticate", "Basic realm=\"input username and password\"");
            logger.warn("response for 401 status");
            return result;
        }

        if (checkAuth(auth, request, response)) {
            return true;
        }
        if (checkToken(token, request, response)) {
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
        if (StringUtils.isBlank(auth)) {
            return false;
        }
        String[] userAndPass = new String(Base64.decodeBase64(auth.split(" ")[1])).split(":");
        if (userAndPass.length < 2) {
            response.setStatus(401);
            response.setHeader("WWW-Authenticate", "Basic realm=\"input username and password\"");
            logger.warn("response for 401 status");
            return true;
        }
        logger.info("username:{} , password:{}", userAndPass[0], userAndPass[1]);
        AgentInfo agentInfo = agentService.getAgentInfo(userAndPass[0]);
        if (agentInfo == null || agentInfo.getStatus() == 0 || !BcryptUtil.checkPwd(userAndPass[1], agentInfo.getPasswd())) {
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
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
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

        AdminAccount adminAccount = new AdminAccount();
        adminAccount.setCompanyId(1L);
        PreAuthenticatedAuthenticationToken authenticationToken = new PreAuthenticatedAuthenticationToken(adminAccount, adminAccount.getPasswd(), null);
        authenticationToken.setAuthenticated(true);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        return true;
    }

}
