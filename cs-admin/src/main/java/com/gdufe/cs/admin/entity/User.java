package com.gdufe.cs.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName(value = "user")
public class User {
    private Long id;
    private String username;
    private String password;
    private String account;
    private Integer gender;
    private String email;
    private Integer age;
}

