package com.gdufe.cs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdufe.cs.dto.NotificationDTO;
import com.gdufe.cs.entities.Notification;
import com.gdufe.cs.entities.User;
import com.gdufe.cs.enums.NotificationStatusEnum;
import com.gdufe.cs.enums.NotificationTypeEnum;
import com.gdufe.cs.exception.CustomizeErrorCode;
import com.gdufe.cs.exception.CustomizeException;
import com.gdufe.cs.mapper.NotificationMapper;
import com.gdufe.cs.service.NotificationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/3/20 18:08
 **/
@Service
public class NotificationServiceImpl extends ServiceImpl<NotificationMapper, Notification> implements NotificationService {

    @Autowired
    private NotificationMapper notificationMapper;

    @Override
    public NotificationDTO read(Long id, User user) {
        Notification notification = notificationMapper.selectById(id);
        if(notification == null){
            throw new CustomizeException(CustomizeErrorCode.NOTIFICATION_NOT_FOUND);
        }
        if(!Objects.equals(notification.getReceiver(),user.getId())){
            throw new CustomizeException(CustomizeErrorCode.READ_NOTIFICATION_FAIL);
        }
        //标为已读
        notification.setStatus(NotificationStatusEnum.READ.getStatus());
        notificationMapper.updateById(notification);

        NotificationDTO notificationDTO = new NotificationDTO();
        BeanUtils.copyProperties(notification,notificationDTO);
        notificationDTO.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));

        return notificationDTO;
    }

    @Override
    public int unreadCount(Long userId) { //未读消息数
        QueryWrapper<Notification> queryWrapper = new QueryWrapper<>();
       queryWrapper.eq("receiver",userId)
                   .eq("status",NotificationStatusEnum.UNREAD.getStatus());

        return notificationMapper.selectCount(queryWrapper);

    }


}
