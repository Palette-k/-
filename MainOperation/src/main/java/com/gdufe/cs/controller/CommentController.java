package com.gdufe.cs.controller;

import com.gdufe.cs.dto.CommentDTO;
import com.gdufe.cs.dto.ResultDTO;
import com.gdufe.cs.dto.UserDTO;
import com.gdufe.cs.entities.Comment;
import com.gdufe.cs.entities.User;
import com.gdufe.cs.enums.CommentTypeEnum;
import com.gdufe.cs.exception.CustomizeErrorCode;
import com.gdufe.cs.service.CommentService;
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
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/comment")
   public ResultDTO post(@RequestBody CommentDTO commentDto,
                         HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("loginUser");
        if(user == null){
            return ResultDTO.error(CustomizeErrorCode.NO_LOGIN);
        }

        Comment comment = new Comment();
        comment.setParentId(commentDto.getParentId());
        comment.setContent(commentDto.getContent());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setType(commentDto.getType());
        comment.setCommentator(user.getId());
        comment.setLikeCount(0);
        commentService.insert(comment,user);
        return ResultDTO.ok();
   }

   @GetMapping("/comment/{id}")
   public ResultDTO<List<CommentDTO>> comments(@PathVariable("id") Long id){
       List<CommentDTO> commentDTOS = commentService.listByTargetId(id, CommentTypeEnum.COMMENT);
       return ResultDTO.ok(commentDTOS);
   }

}
