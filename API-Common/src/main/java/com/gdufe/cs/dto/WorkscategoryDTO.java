package com.gdufe.cs.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.gdufe.cs.entities.Workscategory;
import lombok.Data;

import java.util.List;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/4/30 10:16
 **/
@Data
public class WorkscategoryDTO {
    private Long id;
    private String catelogName; //作品形式
    private Long tagcatelogId; //作品类型

    @TableLogic(value = "1",delval = "0")
    private Integer showStatus;

    /**
     * 所有子分类
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @TableField(exist = false)
    private List<Workscategory> children;
}
