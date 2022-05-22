package com.gdufe.cs.enums;

import lombok.Getter;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/5/11 15:55
 **/
@Getter
public enum LikedTypeEnum {

    LIKE_TO(1,"点赞"),
    WANT_TO(2,"想看、想听"),
    HAVE_DONE(3,"看过、听过");

    private Integer code;

    private String msg;

    LikedTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
