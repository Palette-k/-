package com.gdufe.cs.config;

import io.swagger.annotations.ApiOperation;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @Author: wzq
 * @Description: Swagger接口文档 配置文件
 * @DateTime: 2022/3/31 20:29
 **/
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket initDocket(Environment env) {

        //设置要暴漏接口文档的配置环境
        Profiles profile = Profiles.of("dev", "test");
        boolean flag = env.acceptsProfiles(profile);
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .enable(flag)
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Swagger3接口文档")
                .description("CommentSystem-书影音评价系统")
                .contact(new Contact("wzq", "null", "1148432487@qq.com"))
                .version("1.0")
                .build();
    }
}
