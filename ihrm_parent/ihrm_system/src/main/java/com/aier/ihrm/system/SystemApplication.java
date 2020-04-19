package com.aier.ihrm.system;

import com.aier.ihrm.common.utils.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

/**
 * <p>Title: com.aier.ihrm.system</p>
 * <p>Company:爱尔眼科集团</p>
 * <p>Copyright:Copyright(c)</p>
 * User: Administrator
 * Date: 2020/4/19 17:53
 * Description: No Description
 */
// 1、配置SpringBoot的包扫描
@SpringBootApplication(scanBasePackages = "com.aier.ihrm.system")
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
}
