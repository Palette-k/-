package com.gdufe.cs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdufe.cs.dto.CommentDTO;
import com.gdufe.cs.entities.Comment;
import com.gdufe.cs.entities.Movie;
import com.gdufe.cs.entities.Notification;
import com.gdufe.cs.entities.User;
import com.gdufe.cs.enums.CommentTypeEnum;
import com.gdufe.cs.enums.NotificationStatusEnum;
import com.gdufe.cs.enums.NotificationTypeEnum;
import com.gdufe.cs.exception.CustomizeErrorCode;
import com.gdufe.cs.exception.CustomizeException;
import com.gdufe.cs.mapper.CommentMapper;
import com.gdufe.cs.mapper.MovieMapper;
import com.gdufe.cs.mapper.NotificationMapper;
import com.gdufe.cs.mapper.UserMapper;
import com.gdufe.cs.service.CommentService;
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
    private MovieMapper movieMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private NotificationMapper notificationMapper;

    //插入评论
    @Override
    @Transactional
    public void insert(Comment comment,User commentator){
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
            Comment dbComment = commentMapper.selectById(comment.getParentId());
            if(dbComment == null){
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }

            //影评
            Movie movie = movieMapper.selectById(dbComment.getParentId());
            if(movie == null){  //找不到电影
                throw new CustomizeException(CustomizeErrorCode.MOVIE_NOT_FOUND);
            }

            comment.setCommentCount(0);
            commentMapper.insert(comment); //插入评论
            //增加评论数
            Comment parentComment = new Comment();
            parentComment.setId(comment.getParentId());
            parentComment.setCommentCount(1);
            commentMapper.incCommentCount(parentComment);

            //创建通知
            createNotify(comment, dbComment.getCommentator(),commentator.getUsername(),
                    movie.getMovieName(),NotificationTypeEnum.REPLY,movie.getMovieId());


        }else if(comment.getType() == CommentTypeEnum.MOVIE.getType()){
            //影评
            Movie movie = movieMapper.selectById(comment.getParentId());
            if(movie == null){  //找不到电影
                throw new CustomizeException(CustomizeErrorCode.MOVIE_NOT_FOUND);
            }

            comment.setCommentCount(0);
            commentMapper.insert(comment); //插入评论成功

            movie.setCommentCount(1);
            movieMapper.incCommentCount(movie); //插入电影对应的 评论数


        }
    }

    //给一级评论点赞
    @Override
    public void like(Comment comment,User commentator){

        comment.setLikeCount(1);
        commentMapper.incLikeCount(comment);

        //创建通知
        createNotify(comment,comment.getCommentator() ,commentator.getUsername(),
                comment.getContent(),NotificationTypeEnum.LIKE,comment.getId());
    }

    //创建通知
    @Override
    public void createNotify(Comment comment, Long receiver, String notifierName, String outerTitle, NotificationTypeEnum notificationType, Long outerId) {

        if (receiver == comment.getCommentator()) {
            return;
        }

        Notification notification = new Notification();
        notification.setGmtCreate(System.currentTimeMillis());
        notification.setType(notificationType.getType());
        notification.setCommentId(outerId);
        notification.setNotifier(comment.getCommentator());
        notification.setStatus(NotificationStatusEnum.UNREAD.getStatus());
        notification.setReceiver(receiver);
        notification.setNotifierName(notifierName);
        notification.setOuterTitle(outerTitle);
        notificationMapper.insert(notification);
    }

    @Override
    public List<CommentDTO> listByTargetId(Long id, CommentTypeEnum type) { //罗列评论
        QueryWrapper<Comment> queryWrapper = new QueryWrapper();
        queryWrapper.orderByDesc("gmt_create"); //时间倒序排列
        queryWrapper.eq("parent_id",id);
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
        List<User> users = userMapper.selectList(queryWrapper1);
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
