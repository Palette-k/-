package com.gdufe.cs.works.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdufe.cs.dto.IndexDTO;
import com.gdufe.cs.entities.Movie;
import com.gdufe.cs.works.mapper.MovieMapper;
import com.gdufe.cs.works.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/3/15 17:40
 **/
@Service
public class MovieServiceImpl extends ServiceImpl<MovieMapper, Movie> implements MovieService {

    @Autowired
    private MovieMapper movieMapper;

    @Override
    public IndexDTO moviePage(Integer pageCurrent,Integer pageSize) {
        IndexDTO indexDTO = new IndexDTO();
        Page<Movie> page = new Page<>(pageCurrent,pageSize);
        movieMapper.selectPage(page,null);
        indexDTO.setPageSize(pageSize);
        indexDTO.setPageCurrent(pageCurrent);
        indexDTO.setPageTotal(page.getTotal());
        indexDTO.setMovies(page.getRecords());
        indexDTO.setPages(page.getPages());
        return indexDTO;

    }
}
