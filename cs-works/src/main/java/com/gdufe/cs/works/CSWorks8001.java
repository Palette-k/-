package com.gdufe.cs.works;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/3/27 14:53
 **/
@EnableFeignClients("com.gdufe.cs.works.feign")
@EnableDiscoveryClient
@SpringBootApplication
public class CSWorks8001 {
    public static void main(String[] args) {
        SpringApplication.run(CSWorks8001.class,args);
    }

}
