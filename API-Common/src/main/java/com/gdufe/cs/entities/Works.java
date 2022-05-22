package com.gdufe.cs.entities;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author: wzq
 * @Description: 作品信息
 * @DateTime: 2022/3/14 21:02
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Works {
    private Long id;
    private String name;
    private String path;
    private String intro; //简介
    private Long catelogId; //作品所属类型id
    private Long workscateId; //作品类型id
    public String country; //制作国家
    private Long createTime;
    private Integer status;
    private double score; //评分
    private Long pid; //作者id

    private Integer commentCount; //评论数
    private Integer haveCount; //看过
    private Integer wantCount; //想看
    private Integer scoreCount; //评分数
}
