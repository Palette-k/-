package com.gdufe.cs.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;


import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;


/**
 * @Author: wzq
 * @Description: 后端设置跨域配置
 * @DateTime: 2022/3/13 17:55
 **/
@Configuration
public class CorsConfig {
    // 当前跨域请求最大有效时长。这里默认1天
    private static final long MAX_AGE = 24 * 60 * 60;

    @Bean
    public CorsWebFilter corsWebFilter() {
        //导包要注意选择 reactive 因为网关是基于响应式编程的
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*"); // 1 设置访问源地址
        corsConfiguration.addAllowedHeader("*"); // 2 设置访问源请求头
        corsConfiguration.addAllowedMethod("*"); // 3 设置访问源请求方法

        corsConfiguration.setAllowCredentials(true); // 是否允许携带 cookie 进行跨域
        corsConfiguration.setMaxAge(MAX_AGE);
        source.registerCorsConfiguration("/**", corsConfiguration); // 4 对接口配置跨域设置
        return new CorsWebFilter(source);
    }

}
