package com.gdufe.cs.works.enums;

import lombok.Getter;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/4/30 14:24
 **/
@Getter
public enum PostImgsTypeEnum {
    CAROUSEL(0,"轮播图"),
    WORKSIMG(1, "作品长评图片"),
    ;

    private Integer code;

    private String msg;

    PostImgsTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
