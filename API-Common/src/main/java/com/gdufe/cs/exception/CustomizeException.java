package com.gdufe.cs.exception;

/**
 * @Author: wzq
 * @Description: 处理 评论 产生的异常
 * @DateTime: 2022/3/15 16:50
 **/
public class CustomizeException extends RuntimeException{

    private Integer code;
    private String message;

    public CustomizeException(ICustomizeErrorCode errorCode){
        this.code = errorCode.getCode();
       this.message =  errorCode.getMessage();
    }

    public Integer getCode() {
        return code;
    }

   @Override
    public String getMessage() {
        return message;
    }
}
