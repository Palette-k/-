package com.gdufe.cs.entities;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
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
