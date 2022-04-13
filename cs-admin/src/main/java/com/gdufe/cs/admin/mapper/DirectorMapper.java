package com.gdufe.cs.admin.mapper;

import com.gdufe.cs.admin.entity.Director;
import org.apache.ibatis.annotations.*;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

@Mapper
public interface DirectorMapper extends BaseMapper<Director>{

//    @Select("SELECT * from Director")
//    List<Director> findAll();
//
//    @Insert("INSERT into Director(directorId,directorName,directorIntro,directorBirth)" +
//            " VALUES (#{directorId},#{directorName},#{directorIntro},#{directorBirth})")
//    int insert(Director director);
//
//    int update(Director director);
//
//    @Delete("delete from director where directorId=#{directorId}")
//    Integer deleteById(@Param("directorId") Integer id);

}


