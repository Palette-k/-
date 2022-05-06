package com.gdufe.cs.filter;

import com.alibaba.fastjson.JSONObject;
import com.gdufe.cs.dto.ResultDTO;
import com.gdufe.cs.exception.CustomizeErrorCode;
import com.gdufe.cs.helper.JwtHelper;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @Author: wzq
 * @Description: 网关整合用户认证
 * @DateTime: 2022/3/30 18:56
 **/
@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath();
        System.out.println("==="+path);

        //内部服务接口，不允许外部访问
       /* if(antPathMatcher.match("/api/admin/**", path)) {
            ServerHttpResponse response = exchange.getResponse();
            return out(response, CustomizeErrorCode.PERMISSION);
        }*/

        Long userId = this.getUserId(request);
        //api接口，异步请求，校验用户必须登录
        if(antPathMatcher.match("/api/**/auth/**", path)) {
            if(StringUtils.isEmpty(userId)) {
                ServerHttpResponse response = exchange.getResponse();
                return out(response, CustomizeErrorCode.NO_LOGIN);
            }
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }

    /**
     * api接口鉴权失败返回数据
     * @param response
     * @return
     */
    private Mono<Void> out(ServerHttpResponse response, CustomizeErrorCode code) {
        ResultDTO result = ResultDTO.build(null, code);
        byte[] bits = JSONObject.toJSONString(result).getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(bits);
        //指定编码，否则在浏览器中会中文乱码
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        return response.writeWith(Mono.just(buffer));
    }

    /**
     * 获取当前登录用户id
     * @param request
     * @return
     */
    private Long getUserId(ServerHttpRequest request) {
        String token = "";

        //nginx反向代理后丢失请求头问题 已解决
        List<String> tokenList = request.getHeaders().get("token");
        if(null  != tokenList) {
            token = tokenList.get(0);
        }
        if(!StringUtils.isEmpty(token) || token != "undefined") {
            return JwtHelper.getUserId(token);
        }
        return null;
    }
}
