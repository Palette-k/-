package com.gdufe.cs.works.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gdufe.cs.dto.CommentDTO;
import com.gdufe.cs.dto.ResultDTO;
import com.gdufe.cs.entities.Comment;

import com.gdufe.cs.entities.Liker;
import com.gdufe.cs.entities.Score;
import com.gdufe.cs.entities.User;
import com.gdufe.cs.enums.NotificationTypeEnum;
import com.gdufe.cs.exception.CustomizeException;
import com.gdufe.cs.works.config.MyRabbitMQConfig;
import com.gdufe.cs.works.enums.CommentTypeEnum;
import com.gdufe.cs.works.feign.MemberFeignService;
import com.gdufe.cs.works.mapper.LikerMapper;
import com.gdufe.cs.works.service.CommentService;
import com.gdufe.cs.exception.CustomizeErrorCode;
import com.gdufe.cs.works.service.LikerService;
import com.gdufe.cs.works.service.RedisLikeService;
import com.gdufe.cs.works.service.ScoreService;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/3/15 16:02
 **/
@RestController
@RequestMapping("/works")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private RedisLikeService redisLikeService;

    @Autowired
    private MemberFeignService memberFeignService;


    @Autowired
    private LikerService likerService;


    @PostMapping("/auth/comment/{userId}")
   public ResultDTO postComment(@RequestBody CommentDTO commentDto,
                         @PathVariable("userId")Long userId){


        Comment comment = new Comment();
        comment.setParentId(commentDto.getParentId());
        comment.setContent(commentDto.getContent());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setType(commentDto.getType());

        //Long userId = memberFeignService.finduserId(username);

        //设置评论者id
        comment.setCommentator(userId);
        comment.setLikeCount(0);
        commentService.insert(comment,userId);
        return ResultDTO.ok();
   }

   @PostMapping("/auth/like")
   public ResultDTO getLike(@RequestBody Liker liker){

        if(liker.getStatus() == 1){
            //存入缓存
            redisLikeService.saveLiked2Redis(liker.getLikedParentId(),liker.getLikedPostId(),liker.getType());
            redisLikeService.incrementLikedCount(liker.getLikedParentId(),liker.getType());

            //如果是点赞
            if(liker.getType() == 1){
                Comment comment = commentService.getById(liker.getLikedParentId());
                User user = memberFeignService.findUserById(liker.getLikedPostId());

                boolean notify = commentService.createNotify(comment, comment.getCommentator(), user.getUsername(),
                        comment.getContent(), NotificationTypeEnum.LIKE, comment.getId());
                if(notify == true){
                    return ResultDTO.ok();
                }else{
                    return ResultDTO.error(CustomizeErrorCode.ADD_LIKE_ERROR);
                }
            }


        }else if(liker.getStatus() == 0){
            //从缓存中删除  如果此时已经存入数据库？(已解决)
            redisLikeService.deleteLikedFromRedis(liker.getLikedParentId(),liker.getLikedPostId(),liker.getType());
            redisLikeService.decrementLikedCount(liker.getLikedParentId(),liker.getType());

        }


        return ResultDTO.ok();
   }

    @PostMapping("/showWantStatus")
    public ResultDTO showWantStatus(@RequestBody Liker liker){
        //type = 2
        Liker want = likerService.getBylikedParentIdAndLikedPostId(liker.getLikedParentId(), liker.getLikedPostId(), liker.getType());
        if(null != want){
            return ResultDTO.ok("您想看这部作品");
        }
       return ResultDTO.error(CustomizeErrorCode.WANT_NOT_FOUND);
    }

    @PostMapping("/showHaveStatus")
    public ResultDTO showHaveStatus(@RequestBody Liker liker){
        //type = 3
        Liker have = likerService.getBylikedParentIdAndLikedPostId(liker.getLikedParentId(), liker.getLikedPostId(), liker.getType());

        if(null != have){
            return ResultDTO.ok("您看过这部作品");
        }
        return ResultDTO.error(CustomizeErrorCode.HAVE_NOT_FOUND);
    }

   //罗列二级评论
   @GetMapping("/comment/{id}")
   public ResultDTO<List<CommentDTO>> comments(@PathVariable("id") Long id){
       List<CommentDTO> commentDTOS = commentService.listByTargetId(id, CommentTypeEnum.COMMENT);
       return ResultDTO.ok(commentDTOS);
   }

   //删除评论
    @DeleteMapping("auth/comment/del/{commentId}")
    public ResultDTO delete(@PathVariable("commentId") Long commentId) {


        boolean flag = commentService.deleteComment(commentId);

        if(flag==true){
            return ResultDTO.ok();
        }
        return ResultDTO.error(CustomizeErrorCode.DELETE_COMMENT_ERROR);
    }




}
