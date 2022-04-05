package com.gdufe.cs.entities;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author: wzq
 * @Description: 书籍信息
 * @DateTime: 2022/3/14 21:50
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @TableId
    private Long bookId;
    private String bookName;
    private String bookIntro; //书籍简介
    private String bookCry; //国家
    private Date bookTime; //出版时间
    private Integer bookScore; //书籍评分
    private Long bookAid; //作者id
}
