package com.gdufe.cs.dto;

import com.gdufe.cs.entities.Attr;
import com.gdufe.cs.entities.Producer;
import com.gdufe.cs.entities.Works;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/3/20 16:23
 **/
@Data
public class WorksDTO {
    private Long id;
    private String name;
    private String path;
    private String intro; //简介
    private Long catelogId; //作品所属类型id

    public String country; //制作国家
    private Long creatTime;

    private double score; //评分
    private Long pid; //作者id
    private String producerName;

    private Integer commentCount; //评论数
    private Integer likeCount; //收藏数

    private List<CommentDTO> commentDTOList;
    private List<ArticleDTO> articleDTOList;

    private List<Works> worksList;
    private List<String> tagList;

}
