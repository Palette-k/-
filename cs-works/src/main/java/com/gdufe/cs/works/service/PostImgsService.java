package com.gdufe.cs.works.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gdufe.cs.dto.ArticleDTO;
import com.gdufe.cs.entities.PostImgs;

import java.util.List;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/4/26 15:37
 **/
public interface PostImgsService extends IService<PostImgs> {
    void updateImgs(List<String> imgPaths, Long postId,Long userId);
}
