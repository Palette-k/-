package com.gdufe.cs.works.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gdufe.cs.dto.ResultDTO;
import com.gdufe.cs.entities.Score;
import com.gdufe.cs.entities.Works;
import com.gdufe.cs.exception.CustomizeErrorCode;
import com.gdufe.cs.works.service.ScoreService;
import com.gdufe.cs.works.service.WorksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/4/19 19:27
 **/
@RestController
@RequestMapping("/works")
public class ScoreController {
    @Autowired
    private ScoreService scoreService;



    @PostMapping("/auth/score")
    public ResultDTO userScore(@RequestBody Score score){

        QueryWrapper<Score> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",score.getUserId()).eq("works_id",score.getWorksId());
        Score one = scoreService.getOne(queryWrapper);
        if(one != null){
            return  ResultDTO.error(CustomizeErrorCode.USER_SCORE_REPETITION);
        }

        boolean flag = false;
         flag = scoreService.addScore(score);
        if(flag == true){
            return ResultDTO.ok();
        }

       return ResultDTO.error(CustomizeErrorCode.USER_SCORE_ERROR);

    }

}
