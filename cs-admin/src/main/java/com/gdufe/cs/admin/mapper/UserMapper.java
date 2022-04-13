package com.gdufe.cs.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gdufe.cs.admin.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper extends BaseMapper<User>{

//    @Select("SELECT * from user ")
//    List<User> findAll();
//
//    @Insert("INSERT into user(id,username,password,account,gender,email,age)" +
//            " VALUES (#{id},#{username},#{password},#{account},#{gender},#{email},#{age})")
//    int insert(User user);
//
//    int update(User user);
//
//    @Delete("delete from user where id=#{id}")
//    Integer deleteById(@Param("id") Integer id);
//
//    @Select("select * from user limit #{pageNum},#{pageSize}")
//    List<User> selectPage(Integer pageNum, Integer pageSize);
//
//    @Select("select count(*) from user")
//    Integer selectTotal();
}
