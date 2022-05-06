package com.gdufe.cs.dto;

import com.gdufe.cs.entities.User;
import lombok.Data;

import java.util.List;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/4/26 17:09
 **/
@Data
public class ArticleDTO {
    private Long id;
    private Long parentId;
    private String title;
    private String subTitle;
    private Long gmtCreate;
    private Long gmtModified;
    private Long creator;
    private String content;
    private Integer commentCount;
    private Integer likeCount;
    private Integer viewCount;
    private Integer type;

    private List<String> imgPaths;
    private Double score;
    private User user;
    List<CommentDTO> commentDTOS;

}
