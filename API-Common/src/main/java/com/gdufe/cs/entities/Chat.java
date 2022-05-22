package com.gdufe.cs.entities;

import lombok.Data;

import java.util.Date;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/5/12 17:32
 **/
@Data
public class Chat {
   // private Long id;
    private Long from;
    private Long to;
    private String message;
    private Date time;

}
