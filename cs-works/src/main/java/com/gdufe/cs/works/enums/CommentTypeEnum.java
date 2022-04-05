package com.gdufe.cs.works.enums;

/**
 * @Author: wzq
 * @Description: 枚举 一级评论 和 二级评论
 * @DateTime: 2022/3/15 16:37
 **/
public enum CommentTypeEnum {

    MOVIE(1),
    COMMENT(2);

    private Integer type;

    public Integer getType() {
        return type;
    }

    CommentTypeEnum(Integer type) {
        this.type = type;
    }

    public static boolean isExist(Integer type) {

        for(CommentTypeEnum commentTypeEnum : CommentTypeEnum.values()){
            if (commentTypeEnum.getType() == type){
                return true;
            }
        }

        return false;
    }


}
