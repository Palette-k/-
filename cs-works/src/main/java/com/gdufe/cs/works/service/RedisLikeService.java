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
     * @param likedCommentId
     * @param likedPostId
     */
    void saveLiked2Redis(Long likedCommentId, Long likedPostId);

    /**
     * 取消点赞。将状态改变为0
     * @param likedCommentId
     * @param likedPostId
     */
    void unlikeFromRedis(Long likedCommentId, Long likedPostId);

    /**
     * 从Redis中删除一条点赞数据
     * @param likedCommentId
     * @param likedPostId
     */
    void deleteLikedFromRedis(Long likedCommentId, Long likedPostId);

    /**
     * 该评论的点赞数加1
     * @param likedCommentId
     */
    void incrementLikedCount(Long likedCommentId);

    /**
     * 该评论的点赞数减1
     * @param likedCommentId
     */
    void decrementLikedCount(Long likedCommentId);

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
