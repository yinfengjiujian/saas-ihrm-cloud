package com.aier.ihrm.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * <p>Title: com.aier.ihrm.eureka</p>
 * <p>Company:爱尔眼科集团</p>
 * <p>Copyright:Copyright(c)</p>
 * User: Administrator
 * Date: 2020/4/26 23:17
 * Description: No Description
 */
@SpringBootApplication
@EnableEurekaServer //开启Eureka服务端配置
public class EurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication.class , args);
    }
}
