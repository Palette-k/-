package com.gdufe.cs.works.config;

import com.gdufe.cs.works.service.impl.LikeTask;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/4/25 12:31
 **/
@Configuration
public class QuartzConfig {


    private static final String LIKE_TASK_IDENTITY = "LikeTaskQuartz";

    @Bean
    public JobDetail quartzDetail(){
        return JobBuilder.newJob(LikeTask.class).withIdentity(LIKE_TASK_IDENTITY).storeDurably().build();
    }

    @Bean
    public Trigger quartzTrigger(){
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
               //.withIntervalInSeconds(30)  //设置时间周期单位秒
                .withIntervalInHours(2)  //两个小时执行一次
              //  .withIntervalInMinutes(5) //每五分钟执行一次
                .repeatForever();
        return TriggerBuilder.newTrigger().forJob(quartzDetail())
                .withIdentity(LIKE_TASK_IDENTITY)
                .withSchedule(scheduleBuilder)
                .build();
    }
}
