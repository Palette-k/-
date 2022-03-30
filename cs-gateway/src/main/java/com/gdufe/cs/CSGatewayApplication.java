package com.gdufe.cs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author: wzq
 * @Description: 网关
 * 1.开启服务注册发现
 *   （配置nacos的注册中心地址）
 * @DateTime: 2022/3/26 17:05
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class CSGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(CSGatewayApplication.class,args);

    }
}
