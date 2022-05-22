package com.gdufe.cs.member.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/5/12 20:53
 **/
@Component
public class RedisUtils {

    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    public RedisUtils(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public List<Object> get(String key) {
        // 获取信箱中所有的信息
        return redisTemplate.opsForList().range(key, 0, -1);
    }

    /**
     * 模糊查询
     * @param matchKey 关键字
     * @return
     */
    public Set<Long> scan(String matchKey) {

        //搜索到的 key 值存放的集合
        Set<String> keys = stringRedisTemplate.keys("*" + matchKey + "*");

        //获取一个 redis 操作对象
        ListOperations ops = redisTemplate.opsForList();
        //设置一个存放查询数据的集合
        Set<Long> voList = new HashSet<>();
        //遍历搜索的 key 并存放到集合中
        for (String key : keys) {
            String[] split = key.split("-");
            String from = split[0];
            String to = split[1];
            voList.add(Long.valueOf(from));
            voList.add(Long.valueOf(to));

        }

        return voList;
    }


    public void set(String key, Object value) {
        // 向正在发送信息的任意两人的信箱中中添加信息
        redisTemplate.opsForList().rightPush(key, value);
    }

}
