package com.gdufe.cs.works.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gdufe.cs.dto.ResultDTO;
import com.gdufe.cs.entities.PostImgs;
import com.gdufe.cs.works.enums.PostImgsTypeEnum;
import com.gdufe.cs.works.service.PostImgsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/4/30 11:17
 **/
@RestController
@RequestMapping("/works/carousel")
public class PostImgsController {

    @Autowired
    private PostImgsService postImgsService;

    @GetMapping("/showPostImgs")
    public ResultDTO showPostImgs(){

        QueryWrapper<PostImgs> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("show_status",1).eq("type", PostImgsTypeEnum.CAROUSEL.getCode());
        List<PostImgs> postImgs = postImgsService.list(queryWrapper);

        return ResultDTO.ok(postImgs);
    }

    @GetMapping("/page/showPostImgs")
    public IPage<PostImgs> findPage(@RequestParam(value = "pageCurrent",defaultValue = "1")int pageCurrent,
                                    @RequestParam(value = "pageSize",defaultValue = "2")int pageSize)
    {
        IPage<PostImgs> page = new Page<>(pageCurrent, pageSize);
        QueryWrapper<PostImgs> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type", PostImgsTypeEnum.CAROUSEL.getCode());
        queryWrapper.orderByDesc("id");
        return postImgsService.page(page, queryWrapper);
    }

    @PostMapping("/editPostImg")
    public ResultDTO editPostImg(@RequestBody PostImgs postImgs){

        postImgsService.saveOrUpdate(postImgs);

        return ResultDTO.ok();
    }

    @PostMapping("/addPostImg")
    public ResultDTO addPostImg(@RequestBody PostImgs postImgs){


        postImgsService.save(postImgs);
        return ResultDTO.ok();
    }

    @PostMapping("/deletePostImg")
    public ResultDTO deletePostImg(@RequestBody List<Long> deleteIds){

        postImgsService.removeByIds(deleteIds);
        return ResultDTO.ok();
    }

}
