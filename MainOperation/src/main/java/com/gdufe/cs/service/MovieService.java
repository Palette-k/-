package com.gdufe.cs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gdufe.cs.dto.IndexDTO;
import com.gdufe.cs.entities.Movie;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/3/15 17:39
 **/
public interface MovieService extends IService<Movie> {

    public IndexDTO moviePage(Integer pageCurrent,Integer pageSize);
}
