package com.gdufe.cs.member.webSocket;

import com.gdufe.cs.entities.Chat;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Component;

import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/5/12 21:18
 **/
@Component
public class MessageEntityEncode implements Encoder.Text<Chat> {

    @Override
    public String encode(Chat messageEntity) {
        return new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create()
                .toJson(messageEntity);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {}

    @Override
    public void destroy() {}

}
