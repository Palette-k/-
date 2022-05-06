package com.gdufe.cs.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gdufe.cs.entities.User;
import lombok.Data;

import java.util.List;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/3/20 10:19
 **/
@Data
public class CommentDTO {

    private Long id;
    private Long parentId;
    private Integer type;
    private Long commentator;
    //private List<String> imgPaths;
    private Double score;

    private Long gmtCreate;

    private Long gmtModified;
    private Integer likeCount;
    private Integer commentCount;
    private String content;
    private User user;
}
