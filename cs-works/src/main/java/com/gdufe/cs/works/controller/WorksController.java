package com.gdufe.cs.works.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gdufe.cs.dto.*;
import com.gdufe.cs.entities.CommonResult;
import com.gdufe.cs.entities.Producer;
import com.gdufe.cs.entities.Tagcategory;
import com.gdufe.cs.entities.Works;
import com.gdufe.cs.works.enums.CommentTypeEnum;
import com.gdufe.cs.works.service.CommentService;
import com.gdufe.cs.works.service.TagcategoryService;
import com.gdufe.cs.works.service.WorksService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/3/20 16:09
 **/
@RestController
@RequestMapping("/works")
public class WorksController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private WorksService worksService;

    @Autowired
    private TagcategoryService tagcategoryService;



    /*
     * 首页展示
     * */
    @GetMapping("/indexshow")
    public CommonResult IndexShow(@RequestParam(value = "pageCurrent",defaultValue = "1")int pageCurrent,
                                  @RequestParam(value = "pageSize",defaultValue = "10")int pageSize) throws ExecutionException, InterruptedException {

        IndexDTO indexDTO = worksService.indexPage(pageCurrent, pageSize);

        if(indexDTO.getMovie() != null && indexDTO.getBook()!=null && indexDTO.getMusic()!=null){

            return new CommonResult(200,"首页展示成功",indexDTO);
        }

        return new CommonResult(400,"首页展示失败",indexDTO);


    }


    @ApiOperation(value = "展示作品详情")
    @GetMapping("/{id}")
    public CommonResult movie(@PathVariable("id") Long id) throws ExecutionException, InterruptedException {



        WorksDTO worksDTO = worksService.showTargetWorks(id);

       if(worksDTO != null){
           return new CommonResult(200,"展示作品详情成功", worksDTO);
       }

     return  new CommonResult(400,"展示作品详情失败");

    }

    //展示侧边栏标签
    @GetMapping("/showtag")
    public CommonResult showTag(){

        QueryWrapper<Tagcategory> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("catelog_id",40);
        List<Tagcategory> movieTag = tagcategoryService.list(queryWrapper1);

        QueryWrapper<Tagcategory> queryWrapper2 = new QueryWrapper<>();
        queryWrapper1.eq("catelog_id",41);
        List<Tagcategory> musicTag = tagcategoryService.list(queryWrapper2);

        QueryWrapper<Tagcategory> queryWrapper3 = new QueryWrapper<>();
        queryWrapper1.eq("catelog_id",42);
        List<Tagcategory> bookTag = tagcategoryService.list(queryWrapper3);

        TagcategoryDTO tagcategoryDTO = new TagcategoryDTO();
        tagcategoryDTO.setMovieTag(movieTag);
        tagcategoryDTO.setMusicTag(musicTag);
        tagcategoryDTO.setBookTag(bookTag);


        return new CommonResult(200,"展示标签成功",tagcategoryDTO);
    }



    //显示检索页的检索条件
    @GetMapping("/showSearchParam/{catelogId}")
    public CommonResult showSearchParam(@PathVariable("catelogId")Long catelogId) throws ExecutionException, InterruptedException {

        SearchParamDTO searchParamDTO = worksService.showSearchParam(catelogId);

        return new CommonResult(200,"显示检索页的检索条件成功",searchParamDTO);
    }




}
