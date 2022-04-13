package com.gdufe.cs.entities;

import lombok.Data;

/**
 * @Author: wzq
 * @Description: 创作者
 * @DateTime: 2022/4/2 8:33
 **/
@Data
public class Producer {
    private Long id;
    private String name; //名字
    private String intro; //简介
    private Long birth; //生日
}
