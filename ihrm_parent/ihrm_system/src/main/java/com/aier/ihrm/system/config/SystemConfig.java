package com.aier.ihrm.system.config;

import com.aier.ihrm.common.interceptor.JWTInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * <p>Title: com.aier.ihrm.system.config</p>
 * <p>Company:爱尔眼科集团</p>
 * <p>Copyright:Copyright(c)</p>
 * User: Administrator
 * Date: 2020/4/25 11:04
 * Description: No Description
 */
//@Configuration
public class SystemConfig extends WebMvcConfigurationSupport {

    @Autowired
    private JWTInterceptor jwtInterceptor;

    /**
     * 添加拦截器的配置
     * @param registry
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        // 1、添加自定义拦截器
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/**") // 指定拦截器的url地址
                .excludePathPatterns("/sys/login","/frame/register/**");// 指定不拦截的url地址
    }
}
