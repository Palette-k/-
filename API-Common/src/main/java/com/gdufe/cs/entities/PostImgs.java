package com.gdufe.cs.entities;

import lombok.Data;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/4/26 15:25
 **/
@Data
public class PostImgs {
    private Long id;
    private Long userId;
    private Long postId;
    private String img;
    private Integer type;
    private Integer showStatus;

}
