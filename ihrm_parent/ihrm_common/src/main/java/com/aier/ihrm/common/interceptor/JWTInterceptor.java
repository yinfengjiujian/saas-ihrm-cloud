package com.aier.ihrm.common.interceptor;

import com.aier.ihrm.common.entity.ResultCode;
import com.aier.ihrm.common.exception.CommonException;
import com.aier.ihrm.common.utils.JWTUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * <p>Title: com.aier.ihrm.common.interceptor</p>
 * <p>Company:爱尔眼科集团</p>
 * <p>Copyright:Copyright(c)</p>
 * User: Administrator
 * Date: 2020/4/25 10:30
 * Description: JWT拦截器
 *
 * 自定义拦截器
 *    继承HandlerInterceptorAdapter 类由Spring提供的抽象类
 *    preHandle  进入到控制器方法之前执行的内容
 *       boolean:
 *          true   可以继续执行控制器的方法
 *          false  不满足条件，不执行控制器的方法了
 *    postHandle  执行控制器方法之后执行的内容
 *    afterCompletion   响应结束之前执行的内容
 *
 */
@Component
@Slf4j
public class JWTInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private JWTUtils jwtUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /***
         * 1、通过拦截器获取到token
         */
        String authorization = request.getHeader("Authorization");
        if (!StringUtils.isEmpty(authorization) && authorization.startsWith("Bearer")) {
            String token = authorization.replace("Bearer ","");
            log.info("获取到的token值为：{}", token);
            Claims claims = jwtUtils.parseToken(token);
            if (Objects.nonNull(claims)) {
                /**
                 * 1、通过claims获取到当前用户可访问的API权限字符串
                 * 2、通过拦截方法获取被访问方法的所需要权限，
                 */
                String apis = (String) claims.get("apis");
                HandlerMethod method = (HandlerMethod) handler;
                RequestMapping requestMapping = method.getMethodAnnotation(RequestMapping.class);
                String name = requestMapping.name();
                // 判断当前用户是否具有当前方法的访问权限标识
                if (apis.contains(name)) {
                    // 将token中的claims放入request中
                    request.setAttribute("user_claims", claims);
                    return true;
                } else {
                    throw new CommonException(ResultCode.UNAUTHORISE);
                }
            }
        }
        throw new CommonException(ResultCode.UNAUTHENTICATED);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }
}
