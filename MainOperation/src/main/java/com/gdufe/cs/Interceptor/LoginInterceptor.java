package com.gdufe.cs.Interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author: wzq
 * @Description: 登录拦截器
 * @DateTime: 2022/3/15 9:00
 **/
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    /*目标执行方法之前*/
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestURI = request.getRequestURI();
        log.info("preHandle拦截的请求路径是{}",requestURI);

        //登录检查逻辑

        Object loginUser = request.getSession().getAttribute("loginUser");
        log.info("preHandle中的loginUser是{}",loginUser);

        if(loginUser != null){
            //放行
            return true;
        }

        //拦截住，未登录，跳转到登陆页
        request.setAttribute("msg","请先登陆");
        request.getRequestDispatcher("/member/user/login").forward(request,response);

        return false;
    }
}
