package com.gdufe.cs.works.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gdufe.cs.entities.Tagcategory;

import java.util.List;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/4/2 20:23
 **/
public interface TagcategoryService extends IService<Tagcategory> {

    List<Tagcategory> showCateTree();

    void updateCate(Tagcategory tagcategory);

    void deleteMenuByIds(List<Long> deleteIds);

}
