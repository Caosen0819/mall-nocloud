package com.cs.mall.service.Impl;

import com.cs.mall.dao.JobDetailMapper;
import com.cs.mall.job.HelloJob;
import com.cs.mall.service.QuartzService;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Caosen
 * @Date 2022/8/11 17:47
 * @Version 1.0
 */

@Service
public class QuartServiceImpl implements QuartzService {
    @Autowired
    private JobDetailMapper jobDetailMapper;

    @Autowired
    Scheduler scheduler;



    @Override
    public void test() {
        System.out.println("QuartServiceImpl");
    }

    @Override
    public void addjob(String jName, String jGroup, String tName, String tGroup, String cron) {


        try {
            //构建jobdetail详细的
            JobDetail jobdetail = JobBuilder.newJob(HelloJob.class)
                    .withIdentity(jName, jGroup)
                    .build();

            //按照新的cronexpression表达式构建一个新的trigger触发器
            CronTrigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity(tName, tGroup)
                    .startNow()
                    .withSchedule(CronScheduleBuilder.cronSchedule(cron))
                    .build();

            //启动schedule调度器
            scheduler.start();
            scheduler.scheduleJob(jobdetail, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletejob(String jName, String jGroup) throws SchedulerException {
        scheduler.pauseTrigger(TriggerKey.triggerKey(jName, jGroup));
        scheduler.unscheduleJob(TriggerKey.triggerKey(jName, jGroup));
        scheduler.deleteJob(JobKey.jobKey(jName, jGroup));
    }


}
