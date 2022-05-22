package com.gdufe.cs.member.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gdufe.cs.dto.ResultDTO;

import com.gdufe.cs.entities.CommonResult;
import com.gdufe.cs.entities.User;
import com.gdufe.cs.exception.CustomizeErrorCode;
import com.gdufe.cs.helper.JwtHelper;
import com.gdufe.cs.member.service.UserService;
import org.apache.commons.lang3.StringUtils;
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
    public ResultDTO LoginToIndex(@RequestBody User user){

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", user.getUsername()).eq("password",user.getPassword());
        User one = userService.getOne(queryWrapper);
        if( null == one){
            return ResultDTO.error(CustomizeErrorCode.USER_PARAM_FAULT);
        }

        Map<String,Object> map = userService.login(user);

        return ResultDTO.ok(map);
    }


    /*
    * 注册
    * */
    @PostMapping(value = "/user/register")
    public ResultDTO register(@RequestBody User user){
        Map<String,Object> map = new HashMap<>();
        if(!StringUtils.isEmpty(user.getUsername()) && !StringUtils.isEmpty(user.getPassword())){
            //注册默认为普通用户
            user.setAdmin(0);
            userService.save(user);
            String token = JwtHelper.createToken(user.getId(), user.getUsername());
            map.put("username",user.getUsername());
            map.put("token",token);
           return ResultDTO.ok(map);
        }else{
            return ResultDTO.error(CustomizeErrorCode.NO_LOGIN);
        }

    }

   /*
   * 个人信息修改及保存
   * */
    @PostMapping(value = "/user/update")
    public CommonResult update(@RequestBody User user){

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

    @RequestMapping("/user/finduserId")
    public ResultDTO finduserId(@RequestParam("username") String username){

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        User user = userService.getOne(queryWrapper);

        return ResultDTO.ok(user.getId());
    }

    @RequestMapping("/user/findUserById")
    public User findUserById(@RequestParam("userId")Long userId){

        User user = userService.getById(userId);
        return user;
    }




}