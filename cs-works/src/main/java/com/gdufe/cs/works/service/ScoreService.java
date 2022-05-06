package com.gdufe.cs.works.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gdufe.cs.entities.Score;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/4/19 19:25
 **/
public interface ScoreService extends IService<Score> {
    boolean addScore(Score score);

}
