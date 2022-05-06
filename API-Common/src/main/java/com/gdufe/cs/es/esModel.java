package com.gdufe.cs.es;


import lombok.Data;

import java.util.List;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/4/13 16:42
 **/
@Data
public class esModel {

    private Long worksId; //作品

    private String worksName;

    private String img;

    private Long createTime;

    private String country;

    private Long catelogId;

    private List<Tagcategory> tagList;

    private Long workscateId;

    private String catelogName;

    private Long hotScore;

    private double score;

    private String producerName;

    @Data
    public static class Tagcategory{
        private Long id;
        private String tagName;
    }



}
