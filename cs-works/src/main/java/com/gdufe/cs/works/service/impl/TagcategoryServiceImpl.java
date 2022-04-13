package com.gdufe.cs.works.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdufe.cs.entities.Tagcategory;
import com.gdufe.cs.works.mapper.TagcategoryMapper;
import com.gdufe.cs.works.service.TagcategoryService;
import org.springframework.stereotype.Service;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/4/2 20:25
 **/
@Service
public class TagcategoryServiceImpl extends ServiceImpl<TagcategoryMapper, Tagcategory> implements TagcategoryService {
}
