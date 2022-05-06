package com.gdufe.cs.works.enums;

import lombok.Getter;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/4/26 19:04
 **/
@Getter
public enum ArticleTypeEnum {
    WORKS(1, "作品长评"),

    ;

    private Integer code;

    private String msg;

    ArticleTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
