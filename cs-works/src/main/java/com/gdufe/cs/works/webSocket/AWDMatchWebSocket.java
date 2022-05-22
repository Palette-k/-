package com.gdufe.cs.works.webSocket;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/5/12 14:07
 **/
@Component
@Slf4j
@ServerEndpoint("/webSocket/{userId}")
public class AWDMatchWebSocket {

    private static int loginCount = 0;
    private static Map<Long, AWDMatchWebSocket> users = new ConcurrentHashMap<Long, AWDMatchWebSocket>();
    private Session session;
    private Long userId;

    // 收到消息触发事件
    @OnMessage
    public void onMessage(String message) throws IOException, InterruptedException {
        //JSON数据
        log.info("onMessage:{}",message);
        Map<String,String> map = JSON.parseObject(message, HashMap.class);
        //接收人
        String to = map.get("to");
        //内容
        String info = map.get("info");

        if (to.equals("All")){
            sendMessageAll("ALL:"+info);
        }else{
            sendMessageTo(info,Long.valueOf(to));
        }
    }

    // 打开连接触发事件
    @OnOpen
    public void onOpen(@PathParam("userId") Long userId, Session session, EndpointConfig config) {
        log.info("onOpen:{}",userId);
        this.userId = userId;
        this.session = session;
        //添加登录用户数量
        addLoginCount();
        users.put(userId, this);
        log.info("当前在线人数:"+loginCount);
        log.info("已连接:"+userId);
    }

    // 关闭连接触发事件
    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        log.info("onClose");
        users.remove(userId);
        //减少断开连接的用户
        reduceLoginCount();
    }

    // 传输消息错误触发事件
    @OnError
    public void onError(Throwable error) {
        log.info("onError:{}",error.getMessage());
    }

    public void sendMessageTo(String message, Long To) throws IOException {
        for (AWDMatchWebSocket item : users.values()) {
            if (item.userId.equals(To) ){
                item.session.getAsyncRemote().sendText(message);
                log.info("给{}发送了信息：{}",To,message);
            }

        }
    }

    public void sendMessageAll(String message) throws IOException {
        for (AWDMatchWebSocket item : users.values()) {
            item.session.getAsyncRemote().sendText(message);
            log.info("给所有人发送了信息：{}",message);
        }
    }

    public static synchronized void addLoginCount() {
        AWDMatchWebSocket.loginCount++;
    }

    public static synchronized void reduceLoginCount() {
        AWDMatchWebSocket.loginCount--;
    }

    public static synchronized Map<Long, AWDMatchWebSocket> getUsers() {
        return users;
    }



}