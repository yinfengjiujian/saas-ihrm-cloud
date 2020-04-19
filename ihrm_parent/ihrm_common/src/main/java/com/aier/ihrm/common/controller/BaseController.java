package com.aier.ihrm.common.controller;

import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>Title: com.aier.ihrm.common.controller</p>
 * <p>Company:爱尔眼科集团</p>
 * <p>Copyright:Copyright(c)</p>
 * User: Administrator
 * Date: 2020/4/19 11:14
 * Description: No Description
 */
public class BaseController {

    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected String companyId;
    protected String companyName;

    /**
     *  controller层的拦截器
     *   @ModelAttribute  加上此注解，
     *   会拦截controller层的所有执行方法，会先执行加了@ModelAttribute的方法作为拦截
     *
     * @param request
     * @param response
     */
    @ModelAttribute
    public void setResAndReq(HttpServletRequest request,HttpServletResponse response) {
        this.request = request;
        this.response = response;
        // 以后再去解决companyId
        this.companyId = "1";
        this.companyName = "爱尔眼科集团";
    }

}
