package org.zhongweixian.api.configration.interceptor;

import org.cti.cc.mapper.AdminAccountMapper;
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
    private AdminAccountMapper adminAccountMapper;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/config/")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT","PATCH")
                .maxAge(3600);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HttpRequestInteceptor(adminAccountMapper))
                .addPathPatterns("/call/**")
                .addPathPatterns("/config/**")
                .excludePathPatterns("/**/*.js", "/**/*.css", "/**/*.png");
    }


}
