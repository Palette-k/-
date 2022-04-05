package com.gdufe.cs.member;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/3/27 20:44
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class csMemberApplication8002 {
    public static void main(String[] args) {
        SpringApplication.run(csMemberApplication8002.class,args);
    }

}
