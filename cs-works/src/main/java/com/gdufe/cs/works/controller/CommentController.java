package com.gdufe.cs.works.controller;

import com.gdufe.cs.dto.CommentDTO;
import com.gdufe.cs.dto.ResultDTO;
import com.gdufe.cs.entities.Comment;
import com.gdufe.cs.entities.User;
import com.gdufe.cs.works.enums.CommentTypeEnum;
import com.gdufe.cs.works.service.CommentService;
import com.gdufe.cs.exception.CustomizeErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @PostMapping("/comment")
   public ResultDTO post(@RequestBody CommentDTO commentDto,
                         HttpServletRequest request){
      /*  User user = (User) request.getSession().getAttribute("loginUser");
        if(user == null){
            return ResultDTO.error(CustomizeErrorCode.NO_LOGIN);
        }*/
        Comment comment = new Comment();
        comment.setParentId(commentDto.getParentId());
        comment.setContent(commentDto.getContent());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setType(commentDto.getType());
        comment.setCommentator(1L);
        comment.setLikeCount(0);
        commentService.insert(comment,new User(1L,"Tom","123456","13688889999",0,"Tom@qq.com",18));
        return ResultDTO.ok();
   }

   @GetMapping("/comment/{id}")
   public ResultDTO<List<CommentDTO>> comments(@PathVariable("id") Long id){
       List<CommentDTO> commentDTOS = commentService.listByTargetId(id, CommentTypeEnum.COMMENT);
       return ResultDTO.ok(commentDTOS);
   }

}
