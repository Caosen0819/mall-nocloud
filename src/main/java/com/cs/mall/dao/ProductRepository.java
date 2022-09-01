package com.cs.mall.dao;

import com.cs.mall.dto.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * 商品ES操作类
 * .
 */
public interface ProductRepository extends ElasticsearchRepository<Product, Long> {
    /**
     * 搜索查询
     *
     * @param name              商品名称
     * @param subTitle          商品标题
     * @param keywords          商品关键字
     * @param page              分页信息
     * @return
     */
  Page<Product> findByNameOrSubTitleOrKeywords(String name, String subTitle, String keywords, Pageable page);

}
