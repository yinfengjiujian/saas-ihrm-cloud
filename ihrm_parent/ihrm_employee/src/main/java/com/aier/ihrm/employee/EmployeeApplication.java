package com.aier.ihrm.employee;

import com.aier.ihrm.common.utils.IdWorker;
import com.aier.ihrm.common.utils.JWTUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

/**
 * <p>Title: com.aier.ihrm.employee</p>
 * <p>Company:爱尔眼科集团</p>
 * <p>Copyright:Copyright(c)</p>
 * User: Administrator
 * Date: 2020/4/26 21:28
 * Description: No Description
 */
// 1、配置SpringBoot的包扫描
@SpringBootApplication(scanBasePackages = {"com.aier.ihrm.employee","com.aier.ihrm.common"})
// 2、配置jpa注解的扫描
@EntityScan(value = "com.aier.ihrm.domain.employee")
//注册到Eureka
@EnableEurekaClient
public class EmployeeApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeApplication.class,args);
    }

    @Bean
    public IdWorker idWorker() {
        return new IdWorker();
    }

    @Bean
    public JWTUtils createJWTUtils() {
        return new JWTUtils();
    }
}
