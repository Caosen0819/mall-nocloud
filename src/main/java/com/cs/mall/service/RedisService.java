package com.cs.mall.service;

/**
 * @Author Caosen
 * @Date 2022/8/7 19:19
 * @Version 1.0
 * redis操作service
 * 对象和数组都以json的格式储存
 */

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Redis操作Service
 * Created by macro on 2020/3/3.
 */
public interface RedisService {

    /**
     * 存储数据
     */
    void set(String key, String value);

    /**
     * 获取数据
     */
    String get(String key);

    /**
     * 设置超期时间
     */
    boolean expire(String key, long expire);

    /**
     * 删除数据
     */
    void remove(String key);

    /**
     * 自增操作
     * @param delta 自增步长
     */
    Long increment(String key, long delta);
}
