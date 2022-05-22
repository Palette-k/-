package com.gdufe.cs.entities;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.gdufe.cs.enums.LikedStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/4/21 17:02
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Liker {


    private Long id;
    private Long likedPostId;
    private Long likedParentId;
    private Long gmtCreate;
    private Integer type;
    //点赞的状态.默认未点赞
    private Integer status = LikedStatusEnum.UNLIKE.getCode();

    public Liker(Long likedParentId, Long likedPostId, Integer type,Integer status) {
        this.likedParentId = likedParentId;
        this.likedPostId = likedPostId;
        this.type = type;
        this.status = status;
    }


}
