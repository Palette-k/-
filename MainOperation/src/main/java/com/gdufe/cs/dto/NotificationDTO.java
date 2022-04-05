package com.gdufe.cs.dto;

import lombok.Data;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/3/20 20:33
 **/
@Data
public class NotificationDTO {
    private Long id;
    private Long gmtCreate;
    private Integer status;
    private Long notifier;
    private String notifierName;
    private String outerTitle;
    private Long outerId;
    private String typeName;
    private Integer type;
}
