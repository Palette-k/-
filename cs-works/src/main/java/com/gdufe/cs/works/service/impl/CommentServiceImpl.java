package com.gdufe.cs.works.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdufe.cs.dto.CommentDTO;
import com.gdufe.cs.entities.*;
import com.gdufe.cs.works.enums.CommentTypeEnum;
import com.gdufe.cs.works.feign.MemberFeignService;
import com.gdufe.cs.works.mapper.CommentMapper;
import com.gdufe.cs.works.mapper.PostImgsMapper;
import com.gdufe.cs.works.mapper.WorksMapper;
import com.gdufe.cs.works.service.CommentService;
import com.gdufe.cs.enums.NotificationStatusEnum;
import com.gdufe.cs.enums.NotificationTypeEnum;
import com.gdufe.cs.exception.CustomizeErrorCode;
import com.gdufe.cs.exception.CustomizeException;
import com.gdufe.cs.works.service.PostImgsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/3/15 16:03
 **/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private WorksMapper worksMapper;

    @Autowired
    private MemberFeignService memberFeignService;

    @Autowired
    private PostImgsMapper postImgsMapper;

    //插入评论
    @Override
    @Transactional
    public void insert(Comment comment,Long userId){
        if(comment.getParentId() == null || comment.getParentId() == 0){
            //没找到对应的 书影音 对象
            throw new CustomizeException(CustomizeErrorCode.TARGET_NOT_FOUND);
        }

        if(comment.getType() == null || !CommentTypeEnum.isExist(comment.getType())){
            //没找到对应评论类型
            throw new CustomizeException(CustomizeErrorCode.COMMENT_TYPE_WRONG);
        }

        if(comment.getType() == CommentTypeEnum.COMMENT.getType()){
            //回复评论  二级评论
            Comment dbComment = commentMapper.selectById(comment.getParentId()); //一级评论
            if(dbComment == null){
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }

            //影评
            Works works = worksMapper.selectById(dbComment.getParentId());
            if(works == null){  //找不到电影
                throw new CustomizeException(CustomizeErrorCode.MOVIE_NOT_FOUND);
            }

            comment.setCommentCount(0);
            commentMapper.insert(comment); //插入评论
            //增加评论数
            Comment parentComment = new Comment();
            parentComment.setId(comment.getParentId());
            parentComment.setCommentCount(1);
            commentMapper.incCommentCount(parentComment);

            User user = memberFeignService.findUserById(userId);



            //创建通知
            boolean notify = createNotify(comment, dbComment.getCommentator(), user.getUsername(),
                    comment.getContent(), NotificationTypeEnum.REPLY, works.getPid());
            if(notify == false){
                throw new CustomizeException(CustomizeErrorCode.CREATE_NOTIFY_ERROR);
            }


        }else if(comment.getType() == CommentTypeEnum.MOVIE.getType()){
            //影评
            Works works = worksMapper.selectById(comment.getParentId());
            if(works == null){  //找不到电影
                throw new CustomizeException(CustomizeErrorCode.MOVIE_NOT_FOUND);
            }

            comment.setCommentCount(0);
            commentMapper.insert(comment); //插入评论成功

            works.setCommentCount(1);
            worksMapper.incCommentCount(works); //插入电影对应的 评论数


        }
    }

    //给一级评论点赞
  /*  @Override
    @Transactional
    public void like(Comment comment,Long userId){

        comment.setLikeCount(1);
        int i = commentMapper.incLikeCount(comment);
        Integer likeCount = comment.getLikeCount();
        if(i != 1){
            throw new CustomizeException(CustomizeErrorCode.INC_LIKE_COUNT_ERROR);
        }

        User user = memberFeignService.findUserById(userId);


        //创建通知
        boolean notify = createNotify(comment, comment.getCommentator(), user.getUsername(),
                comment.getContent(), NotificationTypeEnum.LIKE, comment.getId());
        if(notify != true){
            throw new CustomizeException(CustomizeErrorCode.CREATE_NOTIFY_ERROR);
        }

    }*/

    //创建通知
    @Override
    public boolean createNotify(Comment comment, Long receiver, String notifierName, String outerTitle, NotificationTypeEnum notificationType, Long outerId) {

       /* if (receiver == comment.getCommentator()) {
            return false;
        }*/

        Notification notification = new Notification();
        notification.setGmtCreate(System.currentTimeMillis());
        notification.setType(notificationType.getType());
        notification.setCommentId(outerId);
        notification.setNotifier(comment.getCommentator());
        notification.setStatus(NotificationStatusEnum.UNREAD.getStatus());
        notification.setReceiver(receiver);
        notification.setNotifierName(notifierName);
        notification.setOuterTitle(outerTitle);
        boolean b = memberFeignService.insertNotification(notification);
        if(b==true) return true;

        return false;
    }

    @Override
    @Transactional
    public boolean deleteComment(Long commentId) {


        Comment comment = commentMapper.selectById(commentId);

        if(comment.getType() == 1){
            //作品下的评论数-1
            Works works = worksMapper.selectById(comment.getParentId());
            works.setCommentCount(-1);
            int decCommentCount = worksMapper.decCommentCount(works);
            if(decCommentCount != 1){
                throw new CustomizeException(CustomizeErrorCode.DEC_WORKS_COMMENTCOUNT_ERROR);
            }
            //该评论的二级评论全部删除
            QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("parent_id",commentId);
            List<Comment> comments = commentMapper.selectList(queryWrapper);
            if(null != comments){
                List<Long> delCommentIds = comments.stream().map(comment1 ->
                        comment1.getId()).collect(Collectors.toList());
                int deleteBatchIds = commentMapper.deleteBatchIds(delCommentIds);
                if(deleteBatchIds != 1){
                    throw new CustomizeException(CustomizeErrorCode.DELETE_BATCH_COMMENT_ERROR);
                }
            }

        }else{
            //父评论的评论数-1
            Comment parentComment = commentMapper.selectById(comment.getParentId());
            parentComment.setCommentCount(-1);
            int decCommentCount = commentMapper.decCommentCount(parentComment);
            if(decCommentCount != 1){
                throw new CustomizeException(CustomizeErrorCode.DEC_COMMENTCOUNT_ERROR);
            }
        }

        int i = commentMapper.deleteById(commentId);
        if(i != 1){
            throw new CustomizeException(CustomizeErrorCode.DELETE_COMMENT_ERROR);
        }

        return true;
    }


    @Override
    public List<CommentDTO> listByTargetId(Long id, CommentTypeEnum type) { //罗列评论
        QueryWrapper<Comment> queryWrapper = new QueryWrapper();
        queryWrapper.orderByDesc("gmt_create"); //时间倒序排列
        queryWrapper.eq("parent_id",id).eq("type",type.getType());
        List<Comment> comments = commentMapper.selectList(queryWrapper); //获取二级评论

        if(comments.size() == 0){
            return new ArrayList<>();
        }
        //获取去重的评论人
        Set<Long> commentators = comments.stream().map(comment -> comment.getCommentator()).collect(Collectors.toSet());
        List<Long> userIds = new ArrayList();
        userIds.addAll(commentators);

        //获取评论人并转换为Map
        QueryWrapper<User> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.in("id",userIds);
        List<User> users = memberFeignService.selectUserList(queryWrapper1);
        Map<Long, User> userMap = users.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));

        //转换comment为CommentDTO
        List<CommentDTO> commentDTOS = comments.stream().map(comment -> {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment, commentDTO);
            commentDTO.setUser(userMap.get(comment.getCommentator()));
            return commentDTO;
        }).collect(Collectors.toList());
        return commentDTOS;
    }


}
