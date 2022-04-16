package com.gdufe.cs.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gdufe.cs.admin.dto.AdminTagDTO;
import com.gdufe.cs.admin.dto.AdminWorksDTO;
import com.gdufe.cs.entities.Producer;
import com.gdufe.cs.entities.Tagcategory;
import com.gdufe.cs.entities.Works;
import com.gdufe.cs.entities.Workscategory;

import java.io.IOException;
import java.util.List;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/4/5 14:18
 **/
public interface WorksService extends IService<Works> {

    public int saveWorksList(AdminWorksDTO adminWorksDTO);
    public AdminTagDTO selectTag(String tagName);

    void up(Long worksId);

    List<AdminWorksDTO> selectWorks();
}
