package com.gdufe.cs.Interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author: wzq
 * @Description: 管理员登录 拦截器
 * @DateTime: 2022/3/15 9:12
 **/
@Slf4j
public class AdminInterceptor implements HandlerInterceptor {

    /*目标执行方法之前*/
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        log.info("preHandle拦截的请求路径是{}",requestURI);

        //登录检查逻辑
        HttpSession session = request.getSession();

        Object loginUser = session.getAttribute("msg");

        if(loginUser != "管理员登录成功"){
            //放行
            return true;
        }

        //拦截住，未登录，跳转到登陆页
        request.setAttribute("msg","请先登陆");
        request.getRequestDispatcher("/admin/login").forward(request,response);

        return false;
    }
}
