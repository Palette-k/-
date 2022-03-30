package com.gdufe.cs.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gdufe.cs.dto.NotificationDTO;
import com.gdufe.cs.entities.Notification;
import com.gdufe.cs.entities.User;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/3/20 18:08
 **/
public interface NotificationService extends IService<Notification> {

    public NotificationDTO read(Long id, User user);
    public int unreadCount(Long userId);
   // public int insert(Notification notification);

}
