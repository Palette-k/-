package com.gdufe.cs.dto;

import lombok.Data;

import java.util.List;

/**
 * @Author: wzq
 * @Description: 作品新增、查询、更新的传输数据
 * @DateTime: 2022/4/7 16:03
 **/
@Data
public class AdminWorksDTO {
    private Long id;
    private String name;
    private String path;
    private String intro; //简介
    private Long catelogId; //作品所属类型id
    private Long workscateId; //作品形式id
    public String country; //制作国家
    private Long createTime;

    private String producerName; //创作者名字
    private Long pid; //作者id


   private List<String> tagList; //作品分区


}
