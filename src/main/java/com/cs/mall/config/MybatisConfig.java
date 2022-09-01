package com.cs.mall.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author Caosen
 * @Date 2022/8/7 11:41
 * @Version 1.0
 */

@Configuration
@MapperScan({"com.cs.mall.mbg.mapper","com.cs.mall.dao"})
public class MybatisConfig {
}
