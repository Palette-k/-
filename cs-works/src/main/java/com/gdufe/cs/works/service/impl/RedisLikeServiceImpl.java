package com.gdufe.cs.works.service.impl;

import com.gdufe.cs.dto.LikedCountDTO;
import com.gdufe.cs.entities.Liker;
import com.gdufe.cs.enums.LikedStatusEnum;
import com.gdufe.cs.works.service.LikerService;
import com.gdufe.cs.works.service.RedisLikeService;
import com.gdufe.cs.works.utils.RedisKeyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/4/25 10:40
 **/
@Service
@Slf4j
public class RedisLikeServiceImpl implements RedisLikeService {


    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    LikerService likerService;

    @Override
    public void saveLiked2Redis(Long likedParentId, Long likedPostId,Integer type) {
        String key = RedisKeyUtils.getLikedKey(likedParentId, likedPostId,type);
        redisTemplate.opsForHash().put(RedisKeyUtils.MAP_KEY_USER_LIKED, key, LikedStatusEnum.LIKE.getCode());
    }

    @Override
    public void unlikeFromRedis(Long likedParentId, Long likedPostId,Integer type) {
        String key = RedisKeyUtils.getLikedKey(likedParentId, likedPostId,type);
        redisTemplate.opsForHash().put(RedisKeyUtils.MAP_KEY_USER_LIKED, key, LikedStatusEnum.UNLIKE.getCode());
    }

    @Override
    public void deleteLikedFromRedis(Long likedParentId, Long likedPostId,Integer type) {
        String key = RedisKeyUtils.getLikedKey(likedParentId, likedPostId,type);

            Long flag = redisTemplate.opsForHash().delete(RedisKeyUtils.MAP_KEY_USER_LIKED, key);
            //log.info("flag={}",flag);

       if(flag == 0){
            //从数据库中改变状态
            likerService.updateLikedStatusByType(likedParentId, likedPostId,type);
        }

    }

    @Override
    public void incrementLikedCount(Long likedParentId,Integer type) {
        redisTemplate.opsForHash().increment(RedisKeyUtils.MAP_KEY_LIKED_COUNT, likedParentId+"::"+type, 1);

    }

    @Override
    public void decrementLikedCount(Long likedParentId,Integer type) {
        redisTemplate.opsForHash().increment(RedisKeyUtils.MAP_KEY_LIKED_COUNT, likedParentId+"::"+type, -1);
    }

    @Override
    public List<Liker> getLikedDataFromRedis() {
        Cursor<Map.Entry<Object, Object>> cursor = redisTemplate.opsForHash().scan(RedisKeyUtils.MAP_KEY_USER_LIKED, ScanOptions.NONE);
        List<Liker> list = new ArrayList<>();
        while (cursor.hasNext()){
            Map.Entry<Object, Object> entry = cursor.next();
            String key = (String) entry.getKey();
            //分离出 likedParentId，likedPostId
            String[] split = key.split("::");
            String likedParentId = split[0];
            String likedPostId = split[1];
            String type = split[2];

            Long likedParentId1 = Long.valueOf(likedParentId);
            Long likedPostId1 = Long.valueOf(likedPostId);
            Integer type1 = Integer.valueOf(type);

            Integer value = (Integer) entry.getValue();

            //组装成 UserLike 对象
            Liker userLike = new Liker(likedParentId1, likedPostId1, type1,value);
            list.add(userLike);

            //存到 list 后从 Redis 中删除
            redisTemplate.opsForHash().delete(RedisKeyUtils.MAP_KEY_USER_LIKED, key);
        }

        return list;
    }

    @Override
    public List<LikedCountDTO> getLikedCountFromRedis() {
        Cursor<Map.Entry<Object, Object>> cursor = redisTemplate.opsForHash().scan(RedisKeyUtils.MAP_KEY_LIKED_COUNT, ScanOptions.NONE);
        List<LikedCountDTO> list = new ArrayList<>();
        while (cursor.hasNext()){
            Map.Entry<Object, Object> map = cursor.next();

            String key = map.getKey().toString();
            //分离出 likedParentId，likedPostId
            String[] split = key.split("::");
            String likedParentId = split[0];
            String type = split[1];

            //将点赞数量存储在 LikedCountDTO
           Long likedParentId1 = Long.valueOf(likedParentId);
           Integer type1 = Integer.valueOf(type);


            LikedCountDTO dto = new LikedCountDTO(likedParentId1, type1, (Integer) map.getValue());
            list.add(dto);
            //从Redis中删除这条记录
            redisTemplate.opsForHash().delete(RedisKeyUtils.MAP_KEY_LIKED_COUNT, key);
        }
        return list;
    }
}
