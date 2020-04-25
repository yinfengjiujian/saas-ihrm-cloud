package com.aier.ihrm.common.controller;

import com.aier.ihrm.domain.system.response.ProfileResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
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
    protected String userId;
    protected String companyName;

    /**
     *  controller层的拦截器
     *   @ModelAttribute  加上此注解，
     *   会拦截controller层的所有执行方法，会先执行加了@ModelAttribute的方法作为拦截
     *  JWT方式
     * @param request
     * @param response
     */
//    @ModelAttribute
//    public void setResAndReq(HttpServletRequest request,HttpServletResponse response) {
//        this.request = request;
//        this.response = response;
//        Object user_claims = request.getAttribute("user_claims");
//        if (Objects.nonNull(user_claims)) {
//            this.claims = (Claims) user_claims;
//            this.companyId = (String) claims.get("companyId");
//            this.companyName = (String) claims.get("companyName");
//        }
//    }


    //使用shiro获取
    //进入控制器之前执行的方法
    @ModelAttribute
    public void serResAndReq(HttpServletRequest request,HttpServletResponse response){
        this.request = request;
        this.response = response;

        //获取session中的安全数据
        Subject subject = SecurityUtils.getSubject();
        //subject获取所有的安全集合
        PrincipalCollection principals = subject.getPrincipals();
        if (principals != null && !principals.isEmpty()){
            //获取安全数据
            ProfileResult result = (ProfileResult) principals.getPrimaryPrincipal();
            this.companyId = result.getCompanyId();
            this.companyName = result.getCompany();
            this.userId = result.getUserId();
        }
    }
}
