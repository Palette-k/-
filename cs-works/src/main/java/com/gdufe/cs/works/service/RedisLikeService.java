package com.gdufe.cs.works.service;

import com.gdufe.cs.dto.LikedCountDTO;
import com.gdufe.cs.entities.Liker;

import java.util.List;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/4/25 10:30
 **/
public interface RedisLikeService {


    /**
     * 点赞。状态为1
     * @param likedParentId
     * @param likedPostId
     */
    void saveLiked2Redis(Long likedParentId, Long likedPostId,Integer type);

    /**
     * 取消点赞。将状态改变为0
     * @param likedParentId
     * @param likedPostId
     */
    void unlikeFromRedis(Long likedParentId, Long likedPostId,Integer type);

    /**
     * 从Redis中删除一条点赞数据
     * @param likedParentId
     * @param likedPostId
     */
    void deleteLikedFromRedis(Long likedParentId, Long likedPostId,Integer type);

    /**
     * 该评论的点赞数加1
     * @param likedParentId
     */
    void incrementLikedCount(Long likedParentId,Integer type);

    /**
     * 该评论的点赞数减1
     * @param likedParentId
     */
    void decrementLikedCount(Long likedParentId,Integer type);

    /**
     * 获取Redis中存储的所有点赞数据
     * @return
     */
    List<Liker> getLikedDataFromRedis();

    /**
     * 获取Redis中存储的所有点赞数量
     * @return
     */
    List<LikedCountDTO> getLikedCountFromRedis();
}
