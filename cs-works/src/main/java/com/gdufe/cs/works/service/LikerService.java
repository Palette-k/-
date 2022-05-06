package com.gdufe.cs.works.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.gdufe.cs.entities.Liker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/4/20 19:36
 **/
public interface LikerService extends IService<Liker> {



    /**
     * 根据被点赞人的id查询点赞列表（即查询都谁给这个人点赞过）
     * @param likedCommentId 被点赞人的id
     * @param pageable
     * @return
     */
    Page<Liker> getLikedListBylikedCommentId(Long likedCommentId, Pageable pageable);

    /**
     * 根据点赞人的id查询点赞列表（即查询这个人都给谁点赞过）
     * @param likedPostId
     * @param pageable
     * @return
     */
    Page<Liker> getLikedListByLikedPostId(Long likedPostId, Pageable pageable);

    /**
     * 通过被点赞人和点赞人id查询是否存在点赞记录
     * @param likedCommentId
     * @param likedPostId
     * @return
     */
    Liker getBylikedCommentIdAndLikedPostId(Long likedCommentId, Long likedPostId);

    /**
     * 将Redis里的点赞数据存入数据库中
     */
    void transLikedFromRedis2DB();

    /**
     * 将Redis中的点赞数量数据存入数据库
     */
    void transLikedCountFromRedis2DB();
}
