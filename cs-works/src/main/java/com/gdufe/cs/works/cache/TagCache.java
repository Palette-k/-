package com.gdufe.cs.works.cache;

import com.gdufe.cs.dto.TagDTO;
import com.gdufe.cs.entities.Tagcategory;
import com.gdufe.cs.works.service.TagcategoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/4/2 20:06
 **/
public class TagCache {
    @Autowired
    private static TagcategoryService tagcategoryService;

    public static String filterInvalid(String tags) {
        String[] split = StringUtils.split(tags, ",");
        List<Tagcategory> tagList = tagcategoryService.list();

        //List<String> tagList = tagDTOS.stream().flatMap(tag -> tag.getTags().stream()).collect(Collectors.toList());
        String invalid = Arrays.stream(split).filter(t -> StringUtils.isBlank(t) || !tagList.contains(t)).collect(Collectors.joining(","));
        return invalid;
    }

}
