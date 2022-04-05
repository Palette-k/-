package com.gdufe.cs.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



/**
 * @Author: wzq
 * @Description: 通知
 * @DateTime: 2022/3/20 17:33
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    private Long id;
    private Long notifier; //通知者
    private Long receiver; //接受者
    private Long commentId; //评论的id
    private Integer type; //回复还是点赞
    private Long gmtCreate; //消息的创建时间
    private Integer status; //已读还是未读

    private String notifierName;
    private String outerTitle;
}
