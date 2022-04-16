package com.gdufe.cs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/3/27 16:44
 **/
@EnableFeignClients
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableDiscoveryClient
public class csSearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(csSearchApplication.class,args);
    }

}
