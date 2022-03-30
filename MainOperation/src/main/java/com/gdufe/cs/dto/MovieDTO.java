package com.gdufe.cs.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/3/20 16:23
 **/
@Data
public class MovieDTO {
    private Long movieId;
    private String movieName;
    private String moviePath;
    private String movieIntro; //简介
    public String movieCry; //制作国家
    private Date movieTime;
    private Integer movieScore; //电影评分
    private Long movieDid; //导演id

    private Integer commentCount; //评论数
    private Integer likeCount; //收藏数

    private List<CommentDTO> commentDTOList;
}
