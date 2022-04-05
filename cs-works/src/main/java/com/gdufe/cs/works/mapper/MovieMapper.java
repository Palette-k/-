package com.gdufe.cs.works.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gdufe.cs.entities.Movie;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/3/15 17:38
 **/
@Mapper
public interface MovieMapper extends BaseMapper<Movie> {

    int incCommentCount(Movie record); //增加评论数

}
