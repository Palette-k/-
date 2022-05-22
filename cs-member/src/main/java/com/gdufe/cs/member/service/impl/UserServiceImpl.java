package com.gdufe.cs.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.gdufe.cs.entities.User;
import com.gdufe.cs.exception.CustomizeErrorCode;
import com.gdufe.cs.exception.CustomizeException;
import com.gdufe.cs.helper.JwtHelper;
import com.gdufe.cs.member.mapper.UserMapper;
import com.gdufe.cs.member.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/3/8 21:09
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Map<String, Object> login(User user) {

       String username = user.getUsername();
       String password = user.getPassword();
        //校验参数
        if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
            throw new CustomizeException(CustomizeErrorCode.LOGIN_PARAM_ERROR);
        }

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        User user1 = userMapper.selectOne(queryWrapper);

        Map<String,Object> map = new HashMap<>();


        map.put("username",username);
        String token = JwtHelper.createToken(user1.getId(),username);
        map.put("token",token);
        map.put("admin",user1.getAdmin());

        return map;
    }
}
