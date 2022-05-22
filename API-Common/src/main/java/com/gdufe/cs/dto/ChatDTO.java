package com.gdufe.cs.dto;

import com.gdufe.cs.entities.Chat;
import com.gdufe.cs.entities.User;
import lombok.Data;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/5/12 16:04
 **/
@Data
public class ChatDTO {

    private Chat chat;
   // private Integer isRead; //是否已读 0未读 1已读

    private Integer isOneself; //是否是自己发送的消息，0否 1是
    private User user; //用户信息
}
