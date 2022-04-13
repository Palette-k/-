package com.gdufe.cs.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gdufe.cs.admin.entity.Movie;
import org.apache.ibatis.annotations.*;

@Mapper
public interface MovieMapper extends BaseMapper<Movie>{

//    @Select("SELECT * from movie")
//    List<Movie> findAll();
//
//    @Insert("INSERT into movie(movie_id,movie_name,movie_intro,movie_cry,movie_time,movie_score,movie_did,comment_count,like_count)" +
//            " VALUES (#{movie_id},#{movie_name},#{movie_intro},#{movie_cry},#{movie_time},#{movie_score},#{movie_did},#{comment_count},#{like_count})")
//    int insert(Movie movie);
//
//    int update(Movie movie);
//
//    @Delete("delete from movie where movie_id=#{movie_id}")
//    Integer deleteById(@Param("movie_id") Integer id);


}
