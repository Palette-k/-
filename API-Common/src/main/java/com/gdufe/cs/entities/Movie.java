package com.gdufe.cs.entities;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author: wzq
 * @Description: 电影信息
 * @DateTime: 2022/3/14 21:02
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    @TableId
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
}
