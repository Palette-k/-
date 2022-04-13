package com.gdufe.cs.admin.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdufe.cs.admin.mapper.WorkscategoryMapper;
import com.gdufe.cs.admin.service.WorkscategoryService;
import com.gdufe.cs.entities.Workscategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/4/13 9:21
 **/
@Service
public class WorkscategoryImpl extends ServiceImpl<WorkscategoryMapper, Workscategory> implements WorkscategoryService {

    @Autowired
    private WorkscategoryMapper workscategoryMapper;

    @Override
    public Long selectIdByName(String workscateName) {
        QueryWrapper<Workscategory> queryWrapper = new QueryWrapper();
        queryWrapper.eq("catelog_name",workscateName);
        Workscategory workscategory = workscategoryMapper.selectOne(queryWrapper);
        Long workscategoryId = workscategory.getId();

        return workscategoryId;
    }
}
