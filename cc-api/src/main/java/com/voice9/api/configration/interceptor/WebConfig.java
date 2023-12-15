package com.voice9.api.configration.interceptor;

import com.voice9.core.mapper.AdminUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Create by caoliang on 2020/9/20
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private AdminUserMapper adminUserMapper;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/admin/")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT", "PATCH")
                .maxAge(3600);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HttpRequestInteceptor(adminUserMapper))
                .addPathPatterns("/call/**")
                .addPathPatterns("/config/**")
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/index/*", "/**/*.js", "/**/*.css", "/**/*.png");
    }


}
