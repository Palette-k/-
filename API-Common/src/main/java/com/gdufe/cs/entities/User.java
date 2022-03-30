package com.gdufe.cs.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: wzq
 * @Description: 用户信息
 * @DateTime: 2022/3/13 17:04
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String username;
    private String password;
    //个人信息
    private String account; //手机号码
    private Integer gender;  //0代表男生，1代表女生
    private String  email;
    private Integer age;
}
