package com.gdufe.cs.admin.entity;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@TableName(value = "movie")
public class Movie {
    @TableId(value = "movie_id")
    private Long movieId;
    private String movieName;
    private String movieIntro;
    private String moviePath;
    private String movieCry;
    private Date movieTime;
    private Integer movieScore;
    private Long movieDid;
    private Integer commentCount;
    private Integer likeCount;
}
