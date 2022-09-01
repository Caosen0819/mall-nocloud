package com.cs.mall.dao;

import org.apache.ibatis.annotations.Mapper;
import org.quartz.JobDetail;

/**
 * @Author Caosen
 * @Date 2022/8/11 18:38
 * @Version 1.0
 */

public interface JobDetailMapper {

    int insertone(JobDetail record);
}
