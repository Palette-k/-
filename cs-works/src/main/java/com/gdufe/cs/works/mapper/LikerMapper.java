package com.gdufe.cs.works.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gdufe.cs.entities.Liker;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/4/20 19:35
 **/
@Mapper
public interface LikerMapper extends BaseMapper<Liker> {


    Page<Liker> findByLikedPostIdAndStatus(@Param("likedPostId") Long likedPostId, @Param("code") Integer code, Pageable pageable);

    Page<Liker> findBylikedCommentIdAndStatus(@Param("likedParentId") Long likedParentId,@Param("code") Integer code, Pageable pageable);

    Liker findBylikedParentIdAndLikedPostIdAndType(@Param("likedParentId") Long likedParentId, @Param("likedPostId") Long likedPostId,@Param("type")Integer type);
}
