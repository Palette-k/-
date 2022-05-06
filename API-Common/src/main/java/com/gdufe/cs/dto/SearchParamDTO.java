package com.gdufe.cs.dto;

import com.gdufe.cs.entities.Tagcategory;
import com.gdufe.cs.entities.Workscategory;
import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/4/17 21:03
 **/
@Data
public class SearchParamDTO {
    private List<Tagcategory> tagList;
    private List<Workscategory> workscategories;
    private Set<String> country;
    private Set<Long> createTime;
}
