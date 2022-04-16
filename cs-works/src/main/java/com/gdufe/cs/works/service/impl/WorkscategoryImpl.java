package com.gdufe.cs.works.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdufe.cs.entities.Workscategory;
import com.gdufe.cs.works.mapper.WorkscategoryMapper;
import com.gdufe.cs.works.service.WorkscategoryService;
import org.springframework.stereotype.Service;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/4/13 19:59
 **/
@Service
public class WorkscategoryImpl extends ServiceImpl<WorkscategoryMapper, Workscategory> implements WorkscategoryService {
}
