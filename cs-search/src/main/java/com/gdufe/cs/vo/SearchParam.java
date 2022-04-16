package com.gdufe.cs.vo;

import lombok.Data;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/4/10 20:35
 **/
@Data
public class SearchParam {
    private String keyword; //搜索词（关键字）
    private Long tagcatelogId; //基本类型id
    private Long workscateId; //作品形式id
    private String country; //地区
    private String createTime; //时间、年代
    /*
    * 近期热门
    * 标记最多
    * 评分最高
    * 最新上映*/
    private String sort; //排序条件

    /*其他筛选过滤条件
    * 我没看过
    * 评分筛选*/
    private String scoreSection;

    private Integer pageNum = 1; //页码

}
