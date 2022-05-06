package com.gdufe.cs.works.service.impl;

import com.gdufe.cs.entities.Liker;
import com.gdufe.cs.works.service.LikerService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: wzq
 * @Description: 执行点赞的定时任务
 * @DateTime: 2022/4/25 12:32
 **/
@Slf4j
public class LikeTask extends QuartzJobBean {
    @Autowired
    LikerService likerService;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        log.info("LikeTask-------- {}", sdf.format(new Date()));

        //将 Redis 里的点赞信息同步到数据库里
        likerService.transLikedFromRedis2DB();
        likerService.transLikedCountFromRedis2DB();
    }


}
