package com.gdufe.cs.member.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.gdufe.cs.entities.User;

import java.util.Map;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/3/8 21:09
 **/
public interface UserService extends IService<User> {


    Map<String, Object> login(User user);

}
