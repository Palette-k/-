package com.gdufe.cs.config;

import com.gdufe.cs.Interceptor.AdminInterceptor;
import com.gdufe.cs.Interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: wzq
 * @Description: 拦截器（拦截 非管理员用户登录后台管理页面 以及 用户未登录进行评论）
 * @DateTime: 2022/3/15 8:54
 **/
@Configuration
public class AdminWebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       /* registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/comment");*/
     /*   registry.addInterceptor(new AdminInterceptor())
                .addPathPatterns("/admin");*/

    }
}
