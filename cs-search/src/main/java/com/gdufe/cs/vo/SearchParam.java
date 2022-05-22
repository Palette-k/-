package com.gdufe.cs.vo;

import com.gdufe.cs.entities.Tagcategory;
import lombok.Data;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/4/10 20:35
 **/
@Data
public class SearchParam {
    private String keyword; //搜索词（关键字）
    private Long catelogId; //作品基本分类（书籍、影视、音乐）

    private Long workscateId; //作品形式id
    private String country; //地区
    private String createTime; //时间、年代

    private Long tagcatelogId; //作品分类分区id
    /*
    * 近期热门
    * 标记最多
    * 评分最高
    * 最新上映*/
    private String sort; //排序条件 形式是 createTime_desc/asc  score_desc/asc hotScore_desc hotScore_asc

    /*其他筛选过滤条件
    * 我没看过
    * 评分筛选*/
    private String scoreSection; // 1_5  2_3   2_   _4

    private Integer pageNum = 1; //页码

}
