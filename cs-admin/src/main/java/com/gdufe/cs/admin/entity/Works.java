package com.gdufe.cs.admin.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@TableName(value = "work")
public class Works {
    private Long id;
    private String name;
    private String path;
    private String intro; //简介
    private Long catelogId; //作品所属类型id
    private String tagName;
    private Long workscateId; //作品形式id
    private String catelogName;
    private String country; //制作国家
    private Long createTime;
    private Integer status;

    private String producerName; //创作者名字
    private Long pid; //作者id


    private List<String> tagList; //作品分区
}
