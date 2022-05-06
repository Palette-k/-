package com.gdufe.cs.member.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.gdufe.cs.dto.NotificationDTO;
import com.gdufe.cs.dto.ResultDTO;
import com.gdufe.cs.entities.Notification;
import com.gdufe.cs.entities.User;
import com.gdufe.cs.exception.CustomizeErrorCode;
import com.gdufe.cs.exception.CustomizeException;
import com.gdufe.cs.member.service.NotificationService;
import com.gdufe.cs.member.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    @Autowired
    private UserService userService;

    @GetMapping("/notification")
    public NotificationDTO profile(@RequestParam("id")Long id,@RequestParam("userId")Long userId){

        User user = userService.getById(userId);

        NotificationDTO notificationDTO = notificationService.read(id, user);

        return notificationDTO;
    }

    @RequestMapping("/notification/save")
    public boolean insertNotification(@RequestBody Notification notification){
        boolean v = notificationService.save(notification);
        return v;
    }

    @RequestMapping("/auth/notification/list/{userId}")
    public ResultDTO list(@PathVariable("userId")Long userId){

        QueryWrapper<Notification> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("receiver",userId);
        queryWrapper.orderByAsc("status");

        List<Notification> notifications = notificationService.list(queryWrapper);

        return ResultDTO.ok(notifications);
    }

    @RequestMapping("/auth/notification/read/{id}")
    public ResultDTO readNotification(@PathVariable("id")Long id){
       // Notification notification = notificationService.getById(id);

        UpdateWrapper<Notification> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("status",1).eq("id",id);

        notificationService.update(updateWrapper);
        return ResultDTO.ok();
    }

    @RequestMapping("/auth/notification/readAll/{receiverId}")
    public ResultDTO readAllNotification(@PathVariable("receiverId")Long receiverId){

        UpdateWrapper<Notification> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("status",1).eq("receiver",receiverId);
        notificationService.update(updateWrapper);

        return ResultDTO.ok();
    }

    @RequestMapping("/auth/notification/delete")
    public ResultDTO deleteBatchNotification(@RequestBody List<Long> deleteIds){

        notificationService.removeByIds(deleteIds);

        return ResultDTO.ok();
    }
}
