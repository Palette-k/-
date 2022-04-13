package com.gdufe.cs.admin.service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdufe.cs.admin.entity.Comment;
import com.gdufe.cs.admin.mapper.CommentMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

@Service

public class CommentService extends ServiceImpl<CommentMapper, Comment> {
    public boolean saveComment(Comment comment) {
//        if(comment.getId() == null){
//            return save(comment); //mybatis-plus提供的方法，表示插入数据
//        }else{
//            return updateById(comment);
//        }
        return saveOrUpdate(comment);
    }

//   @Autowired
//   private CommentMapper CommentMapper;
//    public int save(Comment comment){
//
//        if(comment.getId() == null){//movie没有id，则表示是新增
//            return CommentMapper.insert(comment);
//        }else{//否则为更新
//            return CommentMapper.update(comment);
//        }}


    }



