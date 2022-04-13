package com.gdufe.cs.dto;

import lombok.Data;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/4/2 11:11
 **/
@Data
public class AttrDTO {
    private Long id;
    private Long tagId; //标签id
    private Long worksId; //作品id
}
