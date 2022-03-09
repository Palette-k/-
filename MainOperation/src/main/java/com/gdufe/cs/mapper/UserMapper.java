package com.gdufe.cs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gdufe.cs.entities.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/3/8 21:10
 **/
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
