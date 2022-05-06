package com.gdufe.cs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/4/25 10:55
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikedCountDTO {
    private Long key;
    private Integer value;
}
