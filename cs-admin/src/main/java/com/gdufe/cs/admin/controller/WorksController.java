package com.gdufe.cs.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gdufe.cs.admin.dto.AdminCommentDTO;
import com.gdufe.cs.admin.dto.AdminTagDTO;
import com.gdufe.cs.admin.dto.AdminWorksDTO;
import com.gdufe.cs.admin.entity.Comment;
import com.gdufe.cs.admin.entity.Works;
import com.gdufe.cs.admin.mapper.WorksMapper;
import com.gdufe.cs.admin.mapper.WorkscategoryMapper;
import com.gdufe.cs.admin.service.WorksService;
import com.gdufe.cs.admin.service.WorkscategoryService;
import com.gdufe.cs.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/4/5 14:17
 **/
@RestController
@RequestMapping("/admin/works")
public class WorksController {
    @Autowired
    private WorksService worksService;


    @Autowired
    private WorkscategoryService workscategoryService;

    private Long workscateId = null;

    @PostMapping("/insert")
    public CommonResult insertWorks(@RequestBody AdminWorksDTO adminWorksDTO){

        adminWorksDTO.setWorkscateId(workscateId);
        int b = worksService.saveWorksList(adminWorksDTO);
        if(b == 1){
            return new CommonResult(200,"插入作品数据成功");
        }
        return new CommonResult(400,"插入作品数据失败");
    }
    //修改数据
    @PostMapping("/update")
    public CommonResult update(@RequestBody AdminWorksDTO adminWorksDTO){

        adminWorksDTO.setWorkscateId(workscateId);
        worksService.update(adminWorksDTO);

            return new CommonResult(200,"作品修改成功");

    }






    @PostMapping("/selectTag")
    public CommonResult selectTag(@RequestParam String tagName){


        AdminTagDTO adminTagDTO = worksService.selectTag(tagName);


        if(adminTagDTO != null){
            return new CommonResult(200,"查询标签成功",adminTagDTO);
        }
        return new CommonResult(400,"查询标签失败");
    }

    @PostMapping("/selectWorksCate")
    public CommonResult selectWorksCate(@RequestParam String WorkscateName){

        workscateId = workscategoryService.selectIdByName(WorkscateName);

        if(workscateId != null){
            return new CommonResult(200,"查询作品形式成功");
        }

        return new CommonResult(400,"查询作品形式失败");

    }

    @GetMapping("/selectWorks")
     public CommonResult selectWorks(){
        List<AdminWorksDTO> adminWorksDTOList = worksService.selectWorks();
        if(adminWorksDTOList != null){
            return new CommonResult(200,"作品查询成功",adminWorksDTOList);
        }
        return new CommonResult(400,"作品查询失败");
     }

    /*
    * 发布作品
    * */
    @PostMapping("/up/{worksId}")
    public CommonResult up(@PathVariable("worksId") Long worksId)  {

        worksService.up(worksId);

        return new CommonResult(200,"作品上架成功");
    }
    //删除作品
    @GetMapping("/del/{worksId}")
    public CommonResult del(@PathVariable("worksId") Long worksId)  {

        worksService.del(worksId);

        return new CommonResult(200,"作品删除成功");
    }

}
