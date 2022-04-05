package com.gdufe.cs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/3/8 18:08
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class OperationMain8082 {
    public static void main(String[] args) {
        SpringApplication.run(OperationMain8082.class,args);
    }
}
