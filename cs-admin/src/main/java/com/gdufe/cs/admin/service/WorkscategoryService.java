package com.gdufe.cs.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gdufe.cs.entities.Workscategory;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/4/13 9:20
 **/
public interface WorkscategoryService extends IService<Workscategory> {

    Long selectIdByName(String workscateName);
}
