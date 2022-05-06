package com.gdufe.cs.works.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdufe.cs.dto.ArticleDTO;
import com.gdufe.cs.dto.CommentDTO;
import com.gdufe.cs.entities.*;
import com.gdufe.cs.exception.CustomizeErrorCode;
import com.gdufe.cs.exception.CustomizeException;
import com.gdufe.cs.works.enums.CommentTypeEnum;
import com.gdufe.cs.works.feign.MemberFeignService;
import com.gdufe.cs.works.mapper.ArticleMapper;
import com.gdufe.cs.works.mapper.PostImgsMapper;
import com.gdufe.cs.works.mapper.ScoreMapper;
import com.gdufe.cs.works.service.ArticleService;
import com.gdufe.cs.works.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/4/26 17:15
 **/
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private PostImgsMapper postImgsMapper;

    @Autowired
    private ScoreMapper scoreMapper;

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private MemberFeignService memberFeignService;

    @Autowired
    private CommentService commentService;

    @Override
    public void insertImgs(ArticleDTO articleDTO,Article article) {


            List<String> imgPaths = articleDTO.getImgPaths();
            for (String imgPath : imgPaths) {
                PostImgs postImgs = new PostImgs();
                postImgs.setPostId(article.getId());
                postImgs.setUserId(article.getCreator());

                postImgs.setImg(imgPath);

                int insert = postImgsMapper.insert(postImgs);
                if(insert != 1){
                    throw new CustomizeException(CustomizeErrorCode.POST_IMG_ERROR);
                }
            }




    }

    @Override
    public Article init(ArticleDTO articleDTO,Long userId) {
        Article article = new Article();
        article.setParentId(articleDTO.getParentId());
        article.setTitle(articleDTO.getTitle());
        article.setContent(articleDTO.getContent());
        article.setGmtCreate(System.currentTimeMillis());
        article.setGmtModified(System.currentTimeMillis());
        article.setType(articleDTO.getType());
        article.setSubTitle(articleDTO.getSubTitle());

        article.setCreator(userId);
        article.setLikeCount(0);
        article.setCommentCount(0);
        article.setViewCount(0);
        return article;
    }

    @Override
    public ArticleDTO selectTargetArticle(Article article) {
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setId(article.getId());
        articleDTO.setTitle(article.getTitle());
        articleDTO.setSubTitle(article.getSubTitle());
        articleDTO.setContent(article.getContent());
        articleDTO.setCreator(article.getCreator());
        articleDTO.setGmtCreate(article.getGmtCreate());
        articleDTO.setGmtModified(article.getGmtModified());
        articleDTO.setCommentCount(article.getCommentCount());
        articleDTO.setViewCount(article.getViewCount());
        articleDTO.setLikeCount(article.getLikeCount());
        articleDTO.setParentId(article.getParentId());
        articleDTO.setType(article.getType());

        User user = memberFeignService.findUserById(article.getCreator());
        articleDTO.setUser(user);


        QueryWrapper<Score> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",article.getCreator()).eq("works_id",article.getParentId());
        Score score = scoreMapper.selectOne(queryWrapper);
        articleDTO.setScore(score.getScore());

        QueryWrapper<PostImgs> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("user_id",article.getCreator())
                        .eq("post_id",article.getId());
        List<PostImgs> postImgs = postImgsMapper.selectList(queryWrapper1);
        List<String> imgPaths = postImgs.stream().map(postImgs1 -> postImgs1.getImg()).collect(Collectors.toList());

        articleDTO.setImgPaths(imgPaths);


        return articleDTO;
    }

    @Override
    public List<ArticleDTO> listArticles(List<Article> articles) {
        List<ArticleDTO> articleDTOS = new ArrayList<>();

        for (Article article : articles) {
            ArticleDTO articleDTO = selectTargetArticle(article);
            articleDTOS.add(articleDTO);
        }

        return articleDTOS;
    }

    @Override
    @Transactional
    public void deleteArticle(Long userId,Long articleId) {

        Article article = articleMapper.selectById(articleId);
        //删除文章
        int deleteArticle = articleMapper.deleteById(articleId);
        if(deleteArticle != 1){
            throw new CustomizeException(CustomizeErrorCode.DELETE_ARTICLE_ERROR);
        }
        //删除评分
        QueryWrapper<Score> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId).eq("works_id",article.getParentId());
        int delete = scoreMapper.delete(queryWrapper);
        if(delete != 1){
            throw new CustomizeException(CustomizeErrorCode.DELETE_SCORE_ERROR);
        }
        //删除图片
       /* QueryWrapper<PostImgs> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("post_id",articleId).eq("user_id",userId);
        List<PostImgs> postImgs = postImgsMapper.selectList(queryWrapper1);
        List<Long> deleteIds = postImgs.stream().map(postImgs1 -> postImgs1.getId()).collect(Collectors.toList());

        int deleteBatchImgs = postImgsMapper.deleteBatchIds(deleteIds);
        if(deleteBatchImgs != deleteIds.size()){
            throw new CustomizeException(CustomizeErrorCode.DELETE_IMG_ERROR);
        }*/

        QueryWrapper<Comment> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("parent_id",article.getId()).eq("type", CommentTypeEnum.ARTICLE_COMMENT.getType());
        List<Comment> comments = commentService.list(queryWrapper2);

        for (Comment comment : comments) {
            //删除评论
            commentService.deleteComment(comment.getId());
        }


    }


}
