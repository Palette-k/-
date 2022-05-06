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
    PERMISSION(800,"没有访问权限，请进行管理员登陆"),
    USER_SCORE_ERROR(409,"用户评分失败"),
    USER_SCORE_REPETITION(410,"您已经给这部作品评过分了"),
    CREATE_NOTIFY_ERROR(411,"创建通知失败"),
    ADD_LIKE_ERROR(412,"点赞失败"),
    INC_LIKE_COUNT_ERROR(413,"增加点赞数失败"),
    LOGIN_PARAM_ERROR(414,"登录参数缺失"),
    USER_PARAM_FAULT(415,"用户名或密码错误"),
    DELETE_COMMENT_ERROR(416,"删除评论失败"),
    DEC_WORKS_COMMENTCOUNT_ERROR(417,"减少作品评论数失败"),
    DELETE_BATCH_COMMENT_ERROR(418,"批量删除评论失败"),
    DEC_COMMENTCOUNT_ERROR(419,"减少父评论的评论数失败"),
    POST_IMG_ERROR(420,"添加图片失败"),
    SAVE_ARTICLE_ERROR(421,"上传长文章失败"),
    UPDATE_ARTICLE_ERROR(422,"修改长文章失败"),
    DELETE_IMG_ERROR(423,"删除图片失败"),
    INSERT_IMG_ERROR(424,"插入图片失败"),
    UPDATE_IMG_ERROR(425,"更改图片失败"),
    DELETE_ARTICLE_ERROR(426,"删除文章失败"),
    DELETE_SCORE_ERROR(427,"删除评分失败"),
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
