package com.gdufe.cs.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@TableName(value = "director")
public class Director {
    private Long directorId;
    private String directorName;
    private String directorIntro;
    private Date directorBirth;
}

