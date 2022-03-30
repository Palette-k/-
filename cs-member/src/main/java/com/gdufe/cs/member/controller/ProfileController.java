package com.gdufe.cs.member.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gdufe.cs.entities.Notification;
import com.gdufe.cs.member.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: wzq
 * @Description: 查看各种 消息和通知 的界面
 * @DateTime: 2022/3/20 21:10
 **/
@RestController
public class ProfileController {
    @Autowired
    private NotificationService notificationService;

    @GetMapping("/profile")
   public Page<Notification> profile(){

        Page<Notification> page = new Page<>(1,5); //当前页是第一页，每页五条数据

       return page;
   }

}
