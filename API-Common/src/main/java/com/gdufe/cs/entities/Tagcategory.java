package com.gdufe.cs.entities;

import lombok.Data;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/4/2 8:35
 **/
@Data
public class Tagcategory {
    private Long id;
    private String tagName; //标签名
    private Long catelogId; //作品类型id（父类id）
    private Integer level; //层级
}
