package com.gdufe.cs.dto;

import com.gdufe.cs.entities.Tagcategory;
import lombok.Data;

import java.util.List;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/4/2 20:19
 **/
@Data
public class TagDTO {
    public Long catelogId;
    public List<Tagcategory> tags;
}
