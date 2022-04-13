package com.gdufe.cs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/4/4 23:09
 **/
@EnableDiscoveryClient
@SpringBootApplication
public class csThirdPartyApplication {
    public static void main(String[] args) {
        SpringApplication.run(csThirdPartyApplication.class,args);
    }

}
