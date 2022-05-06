package com.gdufe.cs.entities;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/4/2 8:35
 **/
@Data
public class Tagcategory {
    private Long id;
    private String tagName; //标签名
    private Long catelogId; //作品类型id（父类id）
    private Integer level; //层级

    /**
     * 0不显示
     * 1显示
     * */
    @TableLogic(value = "1",delval = "0")
    private Integer showStatus;

    /**
     * 所有子分类
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @TableField(exist = false)
    private List<Tagcategory> children;
}
