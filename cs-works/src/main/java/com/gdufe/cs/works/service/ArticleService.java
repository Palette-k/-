package com.gdufe.cs.works.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gdufe.cs.dto.ArticleDTO;
import com.gdufe.cs.dto.CommentDTO;
import com.gdufe.cs.entities.Article;

import java.util.List;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/4/26 17:14
 **/
public interface ArticleService extends IService<Article> {
    void insertImgs(ArticleDTO articleDTO,Article article);

    Article init(ArticleDTO articleDTO,Long userId);

    ArticleDTO selectTargetArticle(Article article);

    List<ArticleDTO> listArticles(List<Article> articles);

    void deleteArticle(Long userId,Long articleId);
}
