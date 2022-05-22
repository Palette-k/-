package com.gdufe.cs.works.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.gdufe.cs.dto.LikedCountDTO;
import com.gdufe.cs.entities.Comment;
import com.gdufe.cs.entities.Liker;
import com.gdufe.cs.entities.Works;
import com.gdufe.cs.enums.LikedStatusEnum;
import com.gdufe.cs.works.config.MyRabbitMQConfig;
import com.gdufe.cs.works.mapper.LikerMapper;
import com.gdufe.cs.works.service.CommentService;
import com.gdufe.cs.works.service.LikerService;
import com.gdufe.cs.works.service.RedisLikeService;
import com.gdufe.cs.works.service.WorksService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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

    @Autowired
    WorksService worksService;

    @Autowired
    RabbitTemplate rabbitTemplate;



    @Override
    public Page<Liker> getLikedListBylikedCommentId(Long likedCommentId, Pageable pageable) {
        return likerMapper.findBylikedCommentIdAndStatus(likedCommentId, LikedStatusEnum.LIKE.getCode(), pageable);
    }

    @Override
    public Page<Liker> getLikedListByLikedPostId(Long likedPostId, Pageable pageable) {
        return likerMapper.findByLikedPostIdAndStatus(likedPostId, LikedStatusEnum.LIKE.getCode(), pageable);
    }

    @Override
    public Liker getBylikedParentIdAndLikedPostId(Long likedParentId, Long likedPostId,Integer type) {
        return likerMapper.findBylikedParentIdAndLikedPostIdAndType(likedParentId, likedPostId,type);
    }

    @Override
    @Transactional
    public void transLikedFromRedis2DB() {
        List<Liker> list = redisLikeService.getLikedDataFromRedis();
        for (Liker like : list) {
            Liker ul = getBylikedParentIdAndLikedPostId(like.getLikedParentId(), like.getLikedPostId(),like.getType());
            if (ul == null){
                //没有记录，直接存入
                likerMapper.insert(like);
                //如果想看
                if(like.getStatus() == 1 && like.getType()==2) {
                    //给MQ发送信息
                    rabbitTemplate.convertAndSend(MyRabbitMQConfig.LIKED_EVENT_EXCHANGE, MyRabbitMQConfig.LIKED_CREATE_WANT, like);
                }

            }else{
                //有记录，需要更新
                ul.setStatus(like.getStatus());
                ul.setGmtCreate(System.currentTimeMillis());
                likerMapper.updateById(ul);
                if(ul.getStatus() == 1 && ul.getType()==2){
                    //给MQ发送信息
                    rabbitTemplate.convertAndSend(MyRabbitMQConfig.LIKED_EVENT_EXCHANGE,MyRabbitMQConfig.LIKED_CREATE_WANT,ul);
                }
            }
        }
    }

    @Override
    @Transactional
    public void transLikedCountFromRedis2DB() {
        List<LikedCountDTO> list = redisLikeService.getLikedCountFromRedis();
        for (LikedCountDTO dto : list) {
            if(dto.getType() == 1){
                Comment comment = commentService.getById(dto.getLikedParentId());
                //点赞数量属于无关紧要的操作，出错无需抛异常
                if (comment != null){
                    Integer likeNum = comment.getLikeCount() + dto.getValue();
                    comment.setLikeCount(likeNum);
                    //更新点赞数量
                    commentService.saveOrUpdate(comment);
                }
            }else if(dto.getType() == 2) {

                Works works = worksService.getById(dto.getLikedParentId());
                if(null != works){
                    Integer likeNum = works.getWantCount() + dto.getValue();
                    works.setWantCount(likeNum);
                    worksService.saveOrUpdate(works);

                }

            }else if(dto.getType() == 3) {
                Works works = worksService.getById(dto.getLikedParentId());
                if(null != works){
                    Integer likeNum = works.getHaveCount() + dto.getValue();
                    works.setHaveCount(likeNum);
                    worksService.saveOrUpdate(works);
                }
            }

        }
    }

    @Override
    public void updateLikedStatusByType(Long likedParentId, Long likedPostId, Integer type) {
        Liker ul = getBylikedParentIdAndLikedPostId(likedParentId,likedPostId,type);
        ul.setStatus(LikedStatusEnum.UNLIKE.getCode());
        likerMapper.updateById(ul);
    }
}
