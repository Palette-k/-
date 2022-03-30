package com.gdufe.cs.entities;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author: wzq
 * @Description: 作者信息
 * @DateTime: 2022/3/14 21:52
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author {
    @TableId
    private Long authorId;
    private String authorName;
    private String authorIntro;
    private Date authorBirth;
}
