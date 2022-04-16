package com.gdufe.cs.enums;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/4/14 11:44
 **/
public enum WorksStatusEnum {
    NEW(0,"新建"),
    UP(1,"作品上架"),
    DOWN(2,"作品下架"),
    ;

    private int code;

    private String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    WorksStatusEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
