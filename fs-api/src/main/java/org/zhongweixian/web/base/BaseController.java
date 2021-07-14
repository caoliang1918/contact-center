package org.zhongweixian.web.base;

import org.cti.cc.po.AgentInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.zhongweixian.cc.cache.CacheService;

/**
 * Created by caoliang on 2020/11/2
 */
public class BaseController {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected CacheService cacheService;

    /**
     * RestTemplate restTemplate = new RestTemplate();
     * <p>
     * restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor("user", "pwd"));
     *
     * @return
     */
    @ModelAttribute("agentInfo")
    public AgentInfo agentInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return null;
        }
        logger.info("authentication:{}", authentication);
        return (AgentInfo) authentication.getPrincipal();
    }

}
