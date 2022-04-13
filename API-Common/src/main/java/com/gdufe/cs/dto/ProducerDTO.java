package com.gdufe.cs.dto;

import lombok.Data;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/4/2 11:12
 **/
@Data
public class ProducerDTO {
    private Long id;
    private String name; //名字
    private String intro; //简介
    private Long birth; //生日
}
