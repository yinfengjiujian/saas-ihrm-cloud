package com.aier.ihrm.system;

import com.aier.ihrm.common.interceptor.JWTInterceptor;
import com.aier.ihrm.common.utils.IdWorker;
import com.aier.ihrm.common.utils.JWTUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;

/**
 * <p>Title: com.aier.ihrm.system</p>
 * <p>Company:爱尔眼科集团</p>
 * <p>Copyright:Copyright(c)</p>
 * User: Administrator
 * Date: 2020/4/19 17:53
 * Description: No Description
 */
// 1、配置SpringBoot的包扫描
@SpringBootApplication(scanBasePackages = {"com.aier.ihrm.system","com.aier.ihrm.common"})
// 2、配置jpa注解的扫描
@EntityScan(value = "com.aier.ihrm.domain.system")
public class SystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class,args);
    }

    @Bean
    public IdWorker idWorker() {
        return new IdWorker();
    }

    @Bean
    public JWTUtils createJWTUtils() {
        return new JWTUtils();
    }

    @Bean
    public JWTInterceptor interceptor() {
        return new JWTInterceptor();
    }

    // 解决 no session问题
    @Bean
    public OpenEntityManagerInViewFilter openEntityManagerInViewFilter() {
        return new OpenEntityManagerInViewFilter();
    }
}
