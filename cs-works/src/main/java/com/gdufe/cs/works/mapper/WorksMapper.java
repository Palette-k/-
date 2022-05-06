package com.gdufe.cs.works.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gdufe.cs.dto.AttrDTO;
import com.gdufe.cs.dto.ProducerDTO;
import com.gdufe.cs.dto.WorksDTO;
import com.gdufe.cs.entities.Attr;
import com.gdufe.cs.entities.Producer;
import com.gdufe.cs.entities.Works;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/3/15 17:38
 **/
@Mapper
public interface WorksMapper extends BaseMapper<Works> {

    int incCommentCount(Works record); //增加评论数

    int addScoreCount(Works works);

    int updateScore(Works works);


    int decCommentCount(Works works);
}
