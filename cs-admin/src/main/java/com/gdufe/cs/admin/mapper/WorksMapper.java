package com.gdufe.cs.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gdufe.cs.entities.Works;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/4/5 14:23
 **/
@Mapper
public interface WorksMapper extends BaseMapper<Works> {

    void updateStatus(@Param("worksId") Long worksId, @Param("code") int code);


}