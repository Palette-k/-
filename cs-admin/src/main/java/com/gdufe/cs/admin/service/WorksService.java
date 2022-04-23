package com.gdufe.cs.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdufe.cs.admin.dto.AdminTagDTO;
import com.gdufe.cs.admin.dto.AdminWorksDTO;
import com.gdufe.cs.admin.entity.Comment;
import com.gdufe.cs.admin.entity.Movie;
import com.gdufe.cs.admin.mapper.MovieMapper;
import com.gdufe.cs.admin.mapper.WorksMapper;
import com.gdufe.cs.dto.WorksDTO;
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

    void del(Long id);

    void update(AdminWorksDTO dto);

}
