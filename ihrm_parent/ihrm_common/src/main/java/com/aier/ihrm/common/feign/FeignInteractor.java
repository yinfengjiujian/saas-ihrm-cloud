package com.aier.ihrm.common.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Objects;

/**
 * <p>Title: com.aier.ihrm.common.feign</p>
 * <p>Company:爱尔眼科集团</p>
 * <p>Copyright:Copyright(c)</p>
 * User: Administrator
 * Date: 2020/4/28 21:06
 * Description: 配置feign拦截器，添加请求头信息
 */
@Configuration
public class FeignInteractor {

    /** 配置feign拦截器，解决请求头问题，feign调用需要将个人用户身份进行传递，通过
     *  拦截器传递个人身份到被调用的微服务上
     * */
    @Bean
    public RequestInterceptor requestInterceptor() {
        /**RequestInterceptor，是一个内部类，返回新建立的对象即可*/
        return new RequestInterceptor() {
            /**获取所有请求的属性，赋值到feign中*/
            @Override
            public void apply(RequestTemplate requestTemplate) {
                ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                if (Objects.nonNull(requestAttributes)) {
                    HttpServletRequest request = requestAttributes.getRequest();
                    //获取请求头的所有属性
                    Enumeration<String> headerNames = request.getHeaderNames();
                    if (Objects.nonNull(headerNames)) {
                        // 循环请求头所有属性
                        while (headerNames.hasMoreElements()) {
                            // 获取某个请求头元素
                            String element = headerNames.nextElement();
                            // 获取对应请求头元素的值
                            String value = request.getHeader(element);
                            // 添加所有请求头信息，进行feign调用的时候传递
                            requestTemplate.header(element,value);
                        }
                    }
                }
            }
        };
    }
}
