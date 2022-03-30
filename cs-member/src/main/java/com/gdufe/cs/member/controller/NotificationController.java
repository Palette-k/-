package com.gdufe.cs.member.controller;

import com.gdufe.cs.dto.NotificationDTO;
import com.gdufe.cs.entities.Notification;
import com.gdufe.cs.entities.User;
import com.gdufe.cs.exception.CustomizeErrorCode;
import com.gdufe.cs.exception.CustomizeException;
import com.gdufe.cs.member.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: wzq
 * @Description: 将消息标为 已读 并 取得消息的状态 在前端实现跳转查看
 * @DateTime: 2022/3/20 21:04
 **/
@RestController
@RequestMapping("/member")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/notification/{id}")
    public NotificationDTO profile(@PathVariable("id")Long id,
                          HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("loginUser");
        if(user == null){
            throw new CustomizeException(CustomizeErrorCode.NO_LOGIN);
        }
        NotificationDTO notificationDTO = notificationService.read(id, user);

        return notificationDTO;
    }

    @RequestMapping("/notification/save")
    public boolean insertNotification(@RequestBody Notification notification){
        return notificationService.save(notification);
    }
}
