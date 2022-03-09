package com.gdufe.cs;

import com.gdufe.cs.entities.User;
import com.gdufe.cs.service.UserService;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import javax.annotation.Resource;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/3/8 22:12
 **/
@SpringBootTest
public class UserTest {

    @Autowired
    private UserService userService;

    @Test
    public void test01(){
        if(userService.equals(new User(null,"Tom","123456"))){
            System.out.println("登录成功！");
        }
    }

    @Test
    public void test02(){
        userService.save(new User(null,"Ben","123456"));
    }
}
