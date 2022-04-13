package com.gdufe.cs.dto;

import com.gdufe.cs.entities.Tagcategory;
import lombok.Data;

import java.util.List;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/4/9 16:29
 **/
@Data
public class TagcategoryDTO {
    private List<Tagcategory> movieTag;
    private List<Tagcategory> musicTag;
    private List<Tagcategory> bookTag;
}
