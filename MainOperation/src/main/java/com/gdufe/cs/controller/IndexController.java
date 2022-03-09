package com.gdufe.cs.controller;

import com.gdufe.cs.entities.User;
import com.gdufe.cs.mapper.UserMapper;
import com.gdufe.cs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @Author: wzq
 * @Description: 处理登陆、注册功能
 * @DateTime: 2022/3/8 18:23
 **/
@Controller
public class IndexController {

  @Autowired
  private UserService userService;

    /*
    * 去 登录页
    * */
    @GetMapping(value = "/login")
    public String loginPage(){
        return "login";
    }

    @PostMapping("/login")
    public String LoginToIndex(User user, HttpSession session, Model model){


        if(userService.equals(user)){

            session.setAttribute("loginUser",user);
            return "redirect:/index.html";
        }else{
            model.addAttribute("msg","账号密码错误");
            return "login";
        }
    }


    /*
    * 注册
    * */
    @PostMapping(value = "/register")
    public String register(User user,Model model){

        if(user.getUsername()!=null && user.getPassword()!=null){
            userService.save(user);
            return "redirect:/index.html";
        }else{
            model.addAttribute("msg","用户名和密码不能为空！");
            return "register";
        }

    }

    /*
     * 去 index页面
     * */
    @GetMapping(value = {"/index","/"})
    public String indexPage(){
        return "index";
    }


}