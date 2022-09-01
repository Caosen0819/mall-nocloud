package com.cs.mall.service;

import org.quartz.SchedulerException;

/**
 * @Author Caosen
 * @Date 2022/8/11 17:45
 * @Version 1.0
 */
public interface QuartzService {
    void test();
    void addjob(String jName, String jGroup, String tName, String tGroup, String cron);
    void deletejob(String jName, String jGroup) throws SchedulerException;
}
