package com.cs.mall.controller;

import com.cs.mall.common.api.CommonResult;
import com.cs.mall.service.QuartzService;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Caosen
 * @Date 2022/8/11 17:50
 * @Version 1.0
 */

@Controller
@RequestMapping(value = "/quartz")
public class QuartzController {

    @Autowired
    QuartzService quartzService;

    /**
     * 新增定时任务
     *
     * @param jName 任务名称
     * @param jGroup 任务组
     * @param tName 触发器名称
     * @param tGroup 触发器组
     * @param cron cron表达式
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult addjob(@RequestParam String jName, String jGroup, String tName, String tGroup, String cron){
        System.out.println("开始controller添加");
        quartzService.addjob(jName, jGroup, tName, tGroup, cron);

        return CommonResult.success("添加成功");
    }

    /**
     * 删除任务
     *
     * @param jName 任务名称
     * @param jGroup 任务组
     * @return ResultMap
     */
    @PostMapping(value = "/delete")
    @ResponseBody
    public CommonResult deletejob(String jName, String jGroup) throws SchedulerException {
        quartzService.deletejob(jName, jGroup);
        return CommonResult.success("删除成功");
    }

}
