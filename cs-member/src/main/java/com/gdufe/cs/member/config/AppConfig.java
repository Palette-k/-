package com.gdufe.cs.member.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: wzq
 * @Description: 自定义的Spring上下文处理的配置，为了防止webSocket启用时无法正确加载上下文
 * @DateTime: 2022/5/12 20:55
 **/
@Configuration
@ConditionalOnWebApplication
public class AppConfig {

    @Bean
    public Gson gson() {
        return new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    }

    @Bean
    public CustomSpringConfigurator customSpringConfigurator() {
        return new CustomSpringConfigurator();
    }

}

