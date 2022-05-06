package com.gdufe.cs.works.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.gdufe.cs.dto.LikedCountDTO;
import com.gdufe.cs.entities.Comment;
import com.gdufe.cs.entities.Liker;
import com.gdufe.cs.enums.LikedStatusEnum;
import com.gdufe.cs.works.mapper.LikerMapper;
import com.gdufe.cs.works.service.CommentService;
import com.gdufe.cs.works.service.LikerService;
import com.gdufe.cs.works.service.RedisLikeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/4/20 19:36
 **/
@Slf4j
@Service
public class LikerServiceImpl extends ServiceImpl<LikerMapper, Liker> implements LikerService {


    @Autowired
    LikerMapper likerMapper;

    @Autowired
    RedisLikeService redisLikeService;


    @Autowired
    CommentService commentService;



    @Override
    public Page<Liker> getLikedListBylikedCommentId(Long likedCommentId, Pageable pageable) {
        return likerMapper.findBylikedCommentIdAndStatus(likedCommentId, LikedStatusEnum.LIKE.getCode(), pageable);
    }

    @Override
    public Page<Liker> getLikedListByLikedPostId(Long likedPostId, Pageable pageable) {
        return likerMapper.findByLikedPostIdAndStatus(likedPostId, LikedStatusEnum.LIKE.getCode(), pageable);
    }

    @Override
    public Liker getBylikedCommentIdAndLikedPostId(Long likedCommentId, Long likedPostId) {
        return likerMapper.findBylikedCommentIdAndLikedPostId(likedCommentId, likedPostId);
    }

    @Override
    @Transactional
    public void transLikedFromRedis2DB() {
        List<Liker> list = redisLikeService.getLikedDataFromRedis();
        for (Liker like : list) {
            Liker ul = getBylikedCommentIdAndLikedPostId(like.getLikedCommentId(), like.getLikedPostId());
            if (ul == null){
                //没有记录，直接存入
                likerMapper.insert(like);
            }else{
                //有记录，需要更新
                ul.setStatus(like.getStatus());
                ul.setGmtCreate(System.currentTimeMillis());
                likerMapper.insert(ul);
            }
        }
    }

    @Override
    @Transactional
    public void transLikedCountFromRedis2DB() {
        List<LikedCountDTO> list = redisLikeService.getLikedCountFromRedis();
        for (LikedCountDTO dto : list) {
            Comment comment = commentService.getById(dto.getKey());
            //点赞数量属于无关紧要的操作，出错无需抛异常
            if (comment != null){
                Integer likeNum = comment.getLikeCount() + dto.getValue();
                comment.setLikeCount(likeNum);
                //更新点赞数量
                commentService.saveOrUpdate(comment);
            }
        }
    }
}
