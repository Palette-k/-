package com.gdufe.cs.works.controller;

import com.gdufe.cs.dto.ResultDTO;
import com.gdufe.cs.entities.Tagcategory;
import com.gdufe.cs.works.service.TagcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/4/28 22:23
 **/
@RestController
@RequestMapping("/works/tagcategory")
public class TagcategoryController {

    @Autowired
    private TagcategoryService tagcategoryService;

    @GetMapping("/showCateTree")
    public ResultDTO showCateTree(){

         List<Tagcategory> tagcategoryList = tagcategoryService.showCateTree();

        return ResultDTO.ok(tagcategoryList);
    }

    @GetMapping("/getInfo/{id}")
    public ResultDTO getInfo(@PathVariable("id")Long id){

        Tagcategory tagcategory = tagcategoryService.getById(id);
        return ResultDTO.ok(tagcategory);
    }

    @PostMapping("/save")
    public ResultDTO save(@RequestBody Tagcategory tagcategory){
        tagcategoryService.save(tagcategory);

        return ResultDTO.ok();
    }

    @PostMapping("/update")
    public ResultDTO update(@RequestBody Tagcategory tagcategory){


        tagcategoryService.updateCate(tagcategory);

        return ResultDTO.ok();
    }

    @PostMapping("/delete")
    public ResultDTO delete(@RequestBody List<Long> deleteIds){


        tagcategoryService.deleteMenuByIds(deleteIds);

        return ResultDTO.ok();
    }
}
