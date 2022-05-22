package com.gdufe.cs.member.controller;

import com.gdufe.cs.dto.ChatDTO;
import com.gdufe.cs.dto.ResultDTO;
import com.gdufe.cs.entities.Chat;
import com.gdufe.cs.entities.User;
import com.gdufe.cs.member.service.UserService;
import com.gdufe.cs.member.utils.RedisUtils;
import com.gdufe.cs.member.webSocket.AWDMatchWebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/5/12 14:54
 **/
@RestController
@RequestMapping("/member")
public class WebSocketController {


    private final RedisUtils redis;

    @Autowired
    private UserService userService;



    @Autowired
    public WebSocketController(RedisUtils redis) {
        this.redis = redis;
    }

    @RequestMapping("/pullMsg")
    public List<Object> pullMsg(@RequestParam("from") Long from, @RequestParam("to") Long to) {
        // 根据两人的 id 生成键，并到 redis 中获取
        String key = LongStream.of(from, to)
                .sorted()
                .mapToObj(String::valueOf)
                .collect(Collectors.joining("-"));
        return redis.get(key);
    }

    @RequestMapping("/get/getContactor")
    public List<User> getContactor(@RequestParam("from")Long from){

        List<User> userList = new ArrayList<>();
        Set<Long> tos = redis.scan(from.toString());
        for (Long to : tos) {
            System.out.println("to:" + to );
            if(to.equals(from)){
                continue;
            }
            User contactor = userService.getById(to);
            userList.add(contactor);
        }

     return userList;
    }



}
