package com.gdufe.cs.works.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gdufe.cs.dto.WorkscategoryDTO;
import com.gdufe.cs.entities.Workscategory;

import java.util.List;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/4/13 19:58
 **/
public interface WorkscategoryService extends IService<Workscategory> {
    List<WorkscategoryDTO> showCateTree();

    void updateCate(Workscategory workscategory);

    void deleteMenuByIds(List<Long> deleteIds);
}
