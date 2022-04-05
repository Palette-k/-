package com.gdufe.cs.member.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gdufe.cs.entities.CommonResult;
import com.gdufe.cs.entities.User;
import com.gdufe.cs.helper.JwtHelper;
import com.gdufe.cs.member.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: wzq
 * @Description: 处理用户登录、注册、修改及展示个人信息功能
 * @DateTime: 2022/3/8 18:23
 **/

@RestController
@RequestMapping("/member")
public class IndexController {

  @Autowired
  private UserService userService;


  /*
  * 登录
  * */
    @PostMapping("/user/login")
    public CommonResult LoginToIndex(@RequestBody User user){
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername,user.getUsername()).eq(User::getPassword,user.getPassword());
        User user1 = userService.getOne(queryWrapper);
        Map<String,Object> map = new HashMap<>();
        if(user1 != null){

            String token = JwtHelper.createToken(user.getId(), user.getUsername());
            map.put("username",user.getUsername());
            map.put("token",token);

            return new CommonResult(200,"登录成功",map);
        }else{

            return new CommonResult(400,"账号或密码错误");
        }
    }


    /*
    * 注册
    * */
    @PostMapping(value = "/user/register")
    public CommonResult register(@RequestBody User user){
        Map<String,Object> map = new HashMap<>();
        if(user.getUsername()!=null && user.getPassword()!=null){
            userService.save(user);
            String token = JwtHelper.createToken(user.getId(), user.getUsername());
            map.put("username",user.getUsername());
            map.put("token",token);
           return new CommonResult(200,"注册成功",map);
        }else{
           // model.addAttribute("msg","用户名和密码不能为空！");
            return new CommonResult(400,"用户名和密码不能为空！");
        }

    }

   /*
   * 个人信息修改及保存
   * */
    @PostMapping(value = "/user/update")
    public CommonResult update(User user){

        boolean b = userService.saveOrUpdate(user);
        if(b == true){
            return new CommonResult(200,"修改及保存个人成功");
        }else{
            return new CommonResult(400,"保存失败");
        }
    }

    /*
    * 个人信息展示
    * */
    @GetMapping("/user/showInfo/{id}")
    public CommonResult showInfo(@PathVariable("id") Long id){

        User user = userService.getById(id);

        if(user != null){
            return new CommonResult(200,"查询成功",user);
        }else {
            return new CommonResult(400,"查询失败");
        }

    }

    //查找用户数据
    @RequestMapping("/user/selectuserlist")
    public List<User> selectUserList(QueryWrapper<User> queryWrapper){
        List<User> users = userService.list(queryWrapper);
        return users;
    }


}