package com.gdufe.cs.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class AdminApplication9090 {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication9090.class, args);
    }

}
