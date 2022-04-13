package com.gdufe.cs.entities;

import lombok.Data;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/4/2 8:42
 **/
@Data
public class Attr {
    private Long id;
    private Long tagId; //标签id
    private Long worksId; //作品id
}
