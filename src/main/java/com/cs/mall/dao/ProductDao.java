package com.cs.mall.dao;

import com.cs.mall.dto.Product;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import java.util.List;

/**
 * @Author Caosen
 * @Date 2022/8/15 16:57
 * @Version 1.0
 */
public interface ProductDao {

    List<Product> getAllProductList(@Param("id") Long id);

}
