package com.gdufe.cs.enums;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/3/20 17:36
 **/
public enum NotificationTypeEnum {
     REPLY(1,"回复了你的评论"),
    LIKE(2,"点赞了你的评论");
     private Integer type;
     private String name;

    NotificationTypeEnum(Integer type, String name) {
        this.type = type;
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public static String nameOfType(Integer type){
        for(NotificationTypeEnum notificationTypeEnum : NotificationTypeEnum.values()){
            if(notificationTypeEnum.getType() == type){
                return notificationTypeEnum.getName();
            }
        }
        return "";
    }
}
