package com.gdufe.cs.works.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/4/25 20:48
 **/
@Data
@ConfigurationProperties(prefix = "cs.thread")
//@Component
public class ThreadPoolConfigProperties {

    private Integer coreSize;

    private Integer maxSize;

    private Integer keepAliveTime;
}
