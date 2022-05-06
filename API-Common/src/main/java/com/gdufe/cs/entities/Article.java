package com.gdufe.cs.entities;

import lombok.Data;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/4/26 16:57
 **/
@Data
public class Article {
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
}
