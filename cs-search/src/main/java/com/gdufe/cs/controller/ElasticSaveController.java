package com.gdufe.cs.controller;

import com.gdufe.cs.entities.CommonResult;
import com.gdufe.cs.es.esModel;
import com.gdufe.cs.service.WorksSaveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/4/13 20:56
 **/
@Slf4j
@RestController
@RequestMapping("/search/save")
public class ElasticSaveController {
    @Autowired
    private WorksSaveService worksSaveService;
     //发布作品
    @PostMapping("/works")
    public CommonResult worksStatusUp(@RequestBody esModel esModel) {

        boolean status = true;
        try {
            status =  worksSaveService.worksStatusUp(esModel);
        }catch (IOException e){
            log.info("作品上架错误:{}",e);
            return new CommonResult(400,"作品发布失败");
        }

      if(status == false)
      {
          return new CommonResult(200,"作品发布成功");
      }
        return new CommonResult(400,"作品发布失败");
    }

    //开分
    @PostMapping("/score")
    public CommonResult ESpostScore(@RequestParam Long worksId){

        boolean flag = false;
        try {
            flag = worksSaveService.postScore(worksId);
        }catch (IOException e){
            log.info("作品更新评分错误:{}",e);
            return new CommonResult(400,"作品更新评分失败");
        }

        if(!flag){
            return new CommonResult(200,"作品开分成功");
        }
       return new CommonResult(400,"作品更新评分失败");
    }
}
