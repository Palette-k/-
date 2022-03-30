package com.gdufe.cs.entities;

import lombok.Data;

/**
 * @Author: wzq
 * @Description: 评论 信息
 * @DateTime: 2022/3/15 10:02
 **/
@Data
public class Comment {
    private Long id;
    private Long parentId; //父类id 即对应的电影、书籍id
    private Integer type;  //父类类型
    private  Long commentator; //评论人id
    private Long gmtCreate; //创建时间
    private Long gmtModified; //更新时间
    private Integer likeCount; //点赞数
    private Integer commentCount;  //评论数
    private String content; //评论内容

}
