package com.gdufe.cs.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/3/8 18:20
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long id;
    private String username;
    private String password;

}
