package com.gdufe.cs.works.feign;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gdufe.cs.entities.Notification;
import com.gdufe.cs.entities.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Author: wzq
 * @Description: 对 cs-member 的远程调用
 * @DateTime: 2022/3/28 9:09
 **/
/*
* 1.@FeignClient(要调用的服务名)
* 2.到 cs-member controller里找对应的 请求方法（写下完整的请求路径）
* 3. 开启远程调用功能（在主程序中）
* 4.在 本服务中 调用*/
@FeignClient("cs-member")
@Component
public interface MemberFeignService {

    @RequestMapping ("/member/notification/save")
    public boolean insertNotification(@RequestBody Notification notification);

    @RequestMapping("/member/user/selectuserlist")
    public List<User> selectUserList(QueryWrapper<User> queryWrapper);
}
