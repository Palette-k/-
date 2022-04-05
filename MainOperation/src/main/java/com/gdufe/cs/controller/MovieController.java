package com.gdufe.cs.controller;

import com.gdufe.cs.dto.CommentDTO;
import com.gdufe.cs.dto.IndexDTO;
import com.gdufe.cs.dto.MovieDTO;
import com.gdufe.cs.entities.CommonResult;
import com.gdufe.cs.entities.Movie;
import com.gdufe.cs.enums.CommentTypeEnum;
import com.gdufe.cs.service.CommentService;
import com.gdufe.cs.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/3/20 16:09
 **/
@RestController
public class MovieController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private MovieService movieService;


    /*
     * 首页展示
     * */
    @GetMapping("/mainoperation/indexshow")
    public CommonResult IndexShow(@RequestParam(value = "pageCurrent",defaultValue = "1")int pageCurrent,
                                  @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){

        IndexDTO indexDTO = movieService.moviePage(pageCurrent, pageSize);

        if(indexDTO.getMovies() != null){

            return new CommonResult(200,"首页展示成功",indexDTO);
        }

        return new CommonResult(400,"首页展示失败",indexDTO);


    }


    @GetMapping("/movie/{id}")
    public CommonResult movie(@PathVariable("id") Long id){

        //展示影片详情

        Movie movie = movieService.getById(id);
        List<CommentDTO> comments = commentService.listByTargetId(id, CommentTypeEnum.MOVIE);//罗列影评

        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setMovieDid(movie.getMovieDid());
        movieDTO.setMovieName(movie.getMovieName());
        movieDTO.setMoviePath(movie.getMoviePath());
        movieDTO.setMovieIntro(movie.getMovieIntro());
        movieDTO.setMovieTime(movie.getMovieTime());
        movieDTO.setMovieScore(movie.getMovieScore());
        movieDTO.setMovieCry(movie.getMovieCry());
        movieDTO.setLikeCount(movie.getLikeCount());
        movieDTO.setCommentCount(movie.getCommentCount());
        movieDTO.setCommentDTOList(comments);


        return new CommonResult(200,"展示电影详情成功",movieDTO);
    }


}
