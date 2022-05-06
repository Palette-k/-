package com.gdufe.cs.entities;

import lombok.Data;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/4/19 19:10
 **/
@Data
public class Score {
    private Long id;
    private Long userId;
    private Long worksId;
    private Double score;
}
