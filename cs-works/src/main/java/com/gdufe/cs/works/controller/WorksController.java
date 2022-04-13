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
                                  @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){

        IndexDTO indexDTO = worksService.indexPage(pageCurrent, pageSize);

        if(indexDTO.getMovie() != null && indexDTO.getBook()!=null && indexDTO.getMusic()!=null){

            return new CommonResult(200,"首页展示成功",indexDTO);
        }

        return new CommonResult(400,"首页展示失败",indexDTO);


    }



    @ApiOperation(value = "展示作品详情")
    @GetMapping("/{id}")
    public CommonResult movie(@PathVariable("id") Long id){

        WorksDTO worksDTO = new WorksDTO();

        Works works = worksService.getById(id);
        List<CommentDTO> comments = commentService.listByTargetId(id, CommentTypeEnum.MOVIE);//罗列影评


        worksDTO.setPid(works.getPid());
        worksDTO.setName(works.getName());
        worksDTO.setPath(works.getPath());
        worksDTO.setIntro(works.getIntro());
        worksDTO.setCreatTime(works.getCreateTime());
        worksDTO.setScore(works.getScore());
        worksDTO.setCountry(works.getCountry());
        worksDTO.setLikeCount(works.getLikeCount());
        worksDTO.setCommentCount(works.getCommentCount());
        worksDTO.setCommentDTOList(comments);


        return new CommonResult(200,"展示作品详情成功", worksDTO);
    }

    //展示侧边栏标签
    @GetMapping("/showtag")
    public CommonResult showTag(){

        QueryWrapper<Tagcategory> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("catelog_id",1);
        List<Tagcategory> movieTag = tagcategoryService.list(queryWrapper1);

        QueryWrapper<Tagcategory> queryWrapper2 = new QueryWrapper<>();
        queryWrapper1.eq("catelog_id",2);
        List<Tagcategory> musicTag = tagcategoryService.list(queryWrapper2);

        QueryWrapper<Tagcategory> queryWrapper3 = new QueryWrapper<>();
        queryWrapper1.eq("catelog_id",3);
        List<Tagcategory> bookTag = tagcategoryService.list(queryWrapper3);

        TagcategoryDTO tagcategoryDTO = new TagcategoryDTO();
        tagcategoryDTO.setMovieTag(movieTag);
        tagcategoryDTO.setMusicTag(musicTag);
        tagcategoryDTO.setBookTag(bookTag);


        return new CommonResult(200,"展示标签成功",tagcategoryDTO);
    }

    //查找标签下的作品
    @GetMapping("/showWorksByTag/{tagId}")
    public CommonResult showWorksByTag(@PathVariable("tagId")long tagId,
                                       @RequestParam(value = "pageCurrent",defaultValue = "1")int pageCurrent,
                                       @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){


        List<Works> worksList = worksService.selectWorksByTagId(tagId,pageCurrent,pageSize);

        return new CommonResult(200,"查找标签下的作品成功",worksList);
    }

}
