package com.gdufe.cs.exception;

/**
 * @Author: wzq
 * @Description: 列举用户进行评论时 可能发生的异常
 * @DateTime: 2022/3/15 16:46
 **/
public enum CustomizeErrorCode implements ICustomizeErrorCode{

    TARGET_NOT_FOUND(401,"未选中电影进行回复"),
    COMMENT_TYPE_WRONG(402,"评论类型错误或不存在"),
    COMMENT_NOT_FOUND(403,"此评论已不存在"),
    MOVIE_NOT_FOUND(404,"此电影已不存在"),
    SYS_ERROR(405,"服务器冒烟了，请稍后再试"),
    NO_LOGIN(406,"当前操作需要登录，请登录后重试"),
    NOTIFICATION_NOT_FOUND(407,"消息不见了"),
    READ_NOTIFICATION_FAIL(408,"读取消息失败了"),
    ;



    private Integer code;
    private String message;

     CustomizeErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
