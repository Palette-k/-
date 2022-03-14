package com.gdufe.cs.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gdufe.cs.entities.CommonResult;
import com.gdufe.cs.entities.User;
import com.gdufe.cs.mapper.UserMapper;
import com.gdufe.cs.service.UserService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @Author: wzq
 * @Description: 处理登陆、注册功能
 * @DateTime: 2022/3/8 18:23
 **/
@RestController
@RequestMapping("/user")
public class IndexController {

  @Autowired
  private UserService userService;



    @PostMapping("/login")
    public CommonResult LoginToIndex(@RequestBody User user){
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername,user.getUsername()).eq(User::getPassword,user.getPassword());
        User user1 = userService.getOne(queryWrapper);
        if(user1 != null){
          //  session.setAttribute("loginUser",user);
            return new CommonResult(200,"登录成功");
        }else{
          //  model.addAttribute("message","账号密码错误");
            return new CommonResult(400,"账号或密码错误");
        }
    }


    /*
    * 注册
    * */
    @PostMapping(value = "/register")
    public CommonResult register(@RequestBody User user){

        if(user.getUsername()!=null && user.getPassword()!=null){
            userService.save(user);
           return new CommonResult(200,"注册成功");
        }else{
           // model.addAttribute("msg","用户名和密码不能为空！");
            return new CommonResult(400,"用户名和密码不能为空！");
        }

    }




}