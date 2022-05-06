package com.gdufe.cs.enums;

import lombok.Data;
import lombok.Getter;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/4/25 10:38
 **/
@Getter
public enum LikedStatusEnum {
    LIKE(1, "点赞"),
    UNLIKE(0, "取消点赞/未点赞"),
    ;

    private Integer code;

    private String msg;

    LikedStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }


}
