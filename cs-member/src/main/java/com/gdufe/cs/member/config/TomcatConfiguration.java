package com.gdufe.cs.member.config;

import org.apache.catalina.connector.Connector;
import org.apache.tomcat.websocket.server.WsSci;
import org.springframework.boot.web.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: wzq
 * @Description: 为了使用wss协议，进行了Tomcat服务器配置，以便可以使用https协议
 * @DateTime: 2022/5/12 20:58
 **/
@Configuration
public class TomcatConfiguration {

    @Bean
    public ServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        tomcat.addAdditionalTomcatConnectors(createSslConnector());
        return tomcat;
    }

    private Connector createSslConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        connector.setPort(8089);
        connector.setSecure(false);
        connector.setRedirectPort(443);
        return connector;
    }

    @Bean
    public TomcatContextCustomizer tomcatContextCustomizer() {
        return context -> context.addServletContainerInitializer(new WsSci(), null);
    }

}
