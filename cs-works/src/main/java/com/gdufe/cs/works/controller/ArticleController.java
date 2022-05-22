package com.gdufe.cs.works.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gdufe.cs.dto.ArticleDTO;
import com.gdufe.cs.dto.CommentDTO;
import com.gdufe.cs.dto.ResultDTO;
import com.gdufe.cs.entities.Article;
import com.gdufe.cs.entities.Comment;
import com.gdufe.cs.entities.Score;
import com.gdufe.cs.exception.CustomizeErrorCode;
import com.gdufe.cs.exception.CustomizeException;
import com.gdufe.cs.works.enums.CommentTypeEnum;
import com.gdufe.cs.works.service.ArticleService;
import com.gdufe.cs.works.service.CommentService;
import com.gdufe.cs.works.service.PostImgsService;
import com.gdufe.cs.works.service.ScoreService;
import com.gdufe.cs.works.webSocket.AWDMatchWebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/4/26 17:13
 **/
@RestController
@RequestMapping("/works/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private PostImgsService postImgsService;

    @Autowired
    private CommentService commentService;



    /**
     * 用户发表长评论
     * 可发多张图
     * 可发表评分
     * */
    @PostMapping("/auth/longComment/{userId}")
    public ResultDTO postLongComment(@PathVariable("userId") Long userId,
                                     @RequestBody ArticleDTO articleDTO){

        //写长影评（可发图多张）

        Article article = articleService.init(articleDTO,userId);

        boolean saveArticle = articleService.save(article);
        if(saveArticle != true){
            throw new CustomizeException(CustomizeErrorCode.SAVE_ARTICLE_ERROR);
        }

       // articleService.insertImgs(articleDTO,article);

        //评分（可评可不评）
        if(articleDTO.getScore() != null){
            Score score = new Score();
            score.setScore(articleDTO.getScore());
            score.setUserId(article.getCreator());
            score.setWorksId(articleDTO.getParentId());

            ResultDTO USER_SCORE_REPETITION = judgeScoreStatus(score);
            if (USER_SCORE_REPETITION != null) return USER_SCORE_REPETITION;

            boolean b = scoreService.addScore(score);

            if(b == false){
                throw new CustomizeException(CustomizeErrorCode.USER_SCORE_ERROR);
            }
        }


        return ResultDTO.ok();
    }

    @RequestMapping("auth/judgeScore")
    private ResultDTO judgeScoreStatus(@RequestBody Score score) {
        QueryWrapper<Score> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", score.getUserId()).eq("works_id", score.getWorksId());
        Score one = scoreService.getOne(queryWrapper);
        if(one != null){
            return  ResultDTO.error(CustomizeErrorCode.USER_SCORE_REPETITION);
        }
        return null;
    }

    @PostMapping("/auth/edit/{articleId}")
    public ResultDTO editLongComment(@PathVariable("articleId")Long articleId,
                                     @RequestBody ArticleDTO articleDTO){

        if(articleDTO.getScore() != null){
            throw new CustomizeException(CustomizeErrorCode.USER_SCORE_REPETITION);
        }

        Article article = articleService.getById(articleId);
        article.setTitle(articleDTO.getTitle());
        article.setContent(articleDTO.getTitle());
        article.setSubTitle(articleDTO.getSubTitle());
        article.setGmtModified(System.currentTimeMillis());

        boolean saveOrUpdate = articleService.saveOrUpdate(article);
        if(saveOrUpdate != true){
            throw new CustomizeException(CustomizeErrorCode.UPDATE_ARTICLE_ERROR);
        }

      //  postImgsService.updateImgs(articleDTO.getImgPaths(),articleId,article.getCreator());


        return ResultDTO.ok();

    }

    @GetMapping("/selectTargetArticle/{articleId}")
    public ResultDTO selectTargetArticle(@PathVariable("articleId")Long articleId){

        Article article = articleService.getById(articleId);

        ArticleDTO articleDTO = articleService.selectTargetArticle(article);

        //罗列article下的评论
        List<CommentDTO> commentDTOS = commentService.listByTargetId(article.getId(), CommentTypeEnum.ARTICLE_COMMENT);

        articleDTO.setCommentDTOS(commentDTOS);

        return ResultDTO.ok(articleDTO);
    }

    @GetMapping("/selectArticle")
    public ResultDTO selectArticle(){


        List<Article> articles = articleService.list();
        List<ArticleDTO> articleDTOS = articleService.listArticles(articles);


        return ResultDTO.ok(articleDTOS);
    }

    @DeleteMapping("auth/deleteArticle")
    public ResultDTO deleteArticle(@RequestParam("userId")Long userId,
                                   @RequestParam("articleId")Long articleId){


        articleService.deleteArticle(userId,articleId);


        return ResultDTO.ok();
    }

}
