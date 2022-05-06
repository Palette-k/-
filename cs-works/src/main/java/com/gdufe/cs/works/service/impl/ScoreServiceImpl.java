package com.gdufe.cs.works.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdufe.cs.entities.Score;
import com.gdufe.cs.entities.Works;
import com.gdufe.cs.works.mapper.ScoreMapper;
import com.gdufe.cs.works.mapper.WorksMapper;
import com.gdufe.cs.works.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/4/19 19:25
 **/
@Service
public class ScoreServiceImpl extends ServiceImpl<ScoreMapper, Score> implements ScoreService {

    @Autowired
    private ScoreMapper scoreMapper;

    @Autowired
    private WorksMapper worksMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addScore(Score score) {


        int insert = scoreMapper.insert(score);//插入评分到评分表上
        if(insert != 1){
          throw new RuntimeException("评分数据插入失败");
      }

        Works works = worksMapper.selectById(score.getWorksId());
        works.setScoreCount(1);

        works.setScore(score.getScore());
        int updateScoreInt = worksMapper.updateScore(works);


        if(updateScoreInt != 1){
            throw new RuntimeException("作品评分更新失败");
        }


        int addScoreCount = worksMapper.addScoreCount(works);//增加作品的评分数
        if(addScoreCount != 1){
            throw new RuntimeException("作品评分人数更新失败");
        }


        return true;
    }
}
