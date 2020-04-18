package com.aier.ihrm.company;

import com.aier.ihrm.common.utils.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

/**
 * <p>Title: com.aier.ihrm.company</p>
 * <p>Company:爱尔眼科集团</p>
 * <p>Copyright:Copyright(c)</p>
 * User: Administrator
 * Date: 2020/4/18 17:35
 * Description: No Description
 */
// 1、配置SpringBoot的包扫描
@SpringBootApplication(scanBasePackages = "com.aier.ihrm.company")
// 2、配置jpa注解的扫描
@EntityScan(value = "com.aier.ihrm.domain.company")
public class CompanyApplication {

    public static void main(String[] args) {
        SpringApplication.run(CompanyApplication.class,args);
    }

    @Bean
    public IdWorker idWorker() {
        return new IdWorker();
    }
}
