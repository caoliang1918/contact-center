package org.voice9.cc.configration.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.voice9.cc.cache.CacheService;
import org.voice9.cc.service.AgentService;

/**
 * Create by caoliang on 2020/9/20
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private AgentService agentService;

    @Autowired
    private CacheService cacheService;

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/cti/**")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT", "PATCH")
                .maxAge(3600);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HttpRequestInteceptor(cacheService, applicationContext))
                .addPathPatterns("/cti/call/**")
                .addPathPatterns("/cti/agent/**")
                .addPathPatterns("/cti/admin/**")
                .excludePathPatterns("/cti/agent/login","/index/**", "/**/*.js", "/**/*.css", "/**/*.png");
    }


}
