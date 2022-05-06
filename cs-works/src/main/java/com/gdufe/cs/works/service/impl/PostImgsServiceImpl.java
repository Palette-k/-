package com.gdufe.cs.works.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdufe.cs.dto.ArticleDTO;
import com.gdufe.cs.entities.PostImgs;
import com.gdufe.cs.exception.CustomizeErrorCode;
import com.gdufe.cs.exception.CustomizeException;
import com.gdufe.cs.works.mapper.PostImgsMapper;
import com.gdufe.cs.works.service.PostImgsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/4/26 17:58
 **/
@Service
public class PostImgsServiceImpl extends ServiceImpl<PostImgsMapper, PostImgs> implements PostImgsService {

    @Autowired
    private PostImgsMapper postImgsMapper;

    @Override
    @Transactional
    public void updateImgs(List<String> imgPaths,Long postId,Long userId) {

        QueryWrapper<PostImgs> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("post_id",postId);
        List<PostImgs> postImgs = postImgsMapper.selectList(queryWrapper);


        List<String> BaseImgs = postImgs.stream().map(postImgs1 -> postImgs1.getImg()).collect(Collectors.toList());
        //如果没有修改
        if(BaseImgs == imgPaths){
            return;
        }

        //否则
        //删除原来的所有图片
        postImgsMapper.deleteBatchIds(postImgs.stream().map(postImgs1 -> postImgs1.getId()).collect(Collectors.toList()));


        //新增修改后的所有图片
        for (String imgPath : imgPaths) {
            PostImgs postImgs2 = new PostImgs();
            postImgs2.setPostId(postId);
            postImgs2.setUserId(userId);

            postImgs2.setImg(imgPath);

            int insert = postImgsMapper.insert(postImgs2);
            if(insert != 1){
                throw new CustomizeException(CustomizeErrorCode.POST_IMG_ERROR);
            }
        }





    }
}
