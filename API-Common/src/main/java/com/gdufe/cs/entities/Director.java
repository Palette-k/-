package com.gdufe.cs.entities;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author: wzq
 * @Description: 导演信息
 * @DateTime: 2022/3/14 21:37
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Director {
    @TableId
    private Long directorId;
    private String directorName;
    private String directorIntro; //简介
    private Date directorBirth; //生日
}
