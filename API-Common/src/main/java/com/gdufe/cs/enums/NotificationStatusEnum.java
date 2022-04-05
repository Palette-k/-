package com.gdufe.cs.enums;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/3/20 18:05
 **/
public enum NotificationStatusEnum {
    UNREAD(0),READ(1);

    private Integer status;

    NotificationStatusEnum(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }
}
