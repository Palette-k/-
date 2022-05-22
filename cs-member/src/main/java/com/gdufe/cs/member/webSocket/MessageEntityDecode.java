package com.gdufe.cs.member.webSocket;

import com.gdufe.cs.entities.Chat;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Component;

import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/5/12 21:17
 **/
@Component
public class MessageEntityDecode implements Decoder.Text<Chat> {

    @Override
    public Chat decode(String s) {
        // 利用 gson 处理消息实体，并格式化日期格式
        return new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create()
                .fromJson(s, Chat.class);
    }

    @Override
    public boolean willDecode(String s) {
        return true;
    }

    @Override
    public void init(EndpointConfig endpointConfig) {}

    @Override
    public void destroy() {}

}


