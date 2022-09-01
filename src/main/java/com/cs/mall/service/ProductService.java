package com.cs.mall.service;

import com.cs.mall.dto.Product;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @Author Caosen
 * @Date 2022/8/15 16:50
 * @Version 1.0
 */
public interface ProductService {
    /**
     * 从数据库中导入所有商品到ES
     */
    int importAll();

    /**
     * 根据id删除商品
     */
    void delete(Long id);

    /**
     * 根据id创建商品
     */
    Product create(Long id);

    /**
     * 批量删除商品
     */
    void delete(List<Long> ids);

    /**
     * 根据关键字搜索名称或者副标题
     */
    Page<Product> search(String keyword, Integer pageNum, Integer pageSize);
}
