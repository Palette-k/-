package com.gdufe.cs.works.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gdufe.cs.dto.ResultDTO;
import com.gdufe.cs.dto.WorkscategoryDTO;
import com.gdufe.cs.entities.Tagcategory;
import com.gdufe.cs.entities.Workscategory;
import com.gdufe.cs.works.service.TagcategoryService;
import com.gdufe.cs.works.service.WorkscategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/4/29 20:31
 **/
@RestController
@RequestMapping("/works/workscategory")
public class WorkscategoryController {
    @Autowired
    private WorkscategoryService workscategoryService;

    @GetMapping("/showCateTree")
    public ResultDTO showCateTree(){

        List<WorkscategoryDTO> workscategoryList = workscategoryService.showCateTree();

        return ResultDTO.ok(workscategoryList);
    }

    @GetMapping("/getInfo/{id}")
    public ResultDTO getInfo(@PathVariable("id")Long id){

        Workscategory workscategory = workscategoryService.getById(id);

        return ResultDTO.ok(workscategory);
    }

    @PostMapping("/save")
    public ResultDTO save(@RequestBody Workscategory workscategory){
        workscategoryService.save(workscategory);

        return ResultDTO.ok();
    }

    @PostMapping("/update")
    public ResultDTO update(@RequestBody Workscategory workscategory){


        workscategoryService.updateCate(workscategory);

        return ResultDTO.ok();
    }

    @PostMapping("/delete")
    public ResultDTO deleteMenuByIds(@RequestBody List<Long> deleteIds){


        workscategoryService.deleteMenuByIds(deleteIds);

        return ResultDTO.ok();
    }
}
