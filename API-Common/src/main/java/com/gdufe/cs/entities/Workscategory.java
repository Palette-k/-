package com.gdufe.cs.entities;

import lombok.Data;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/4/2 8:42
 **/
@Data
public class Workscategory {
    private Long id;
    private String catelogName; //作品形式
    private String tagcatelogId; //作品类型
}
