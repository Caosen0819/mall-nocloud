package com.cs.mall.service.Impl;

import com.cs.mall.dao.ProductDao;
import com.cs.mall.dao.ProductRepository;
import com.cs.mall.dto.Product;
import com.cs.mall.service.ProductService;
import io.lettuce.core.ScriptOutputType;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

/**
 * @Author Caosen
 * @Date 2022/8/15 16:50
 * @Version 1.0
 */

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDao productDao;

    @Autowired
    ProductRepository productRepository;

    @Override
    public int importAll() {
        List<Product> allProductList = productDao.getAllProductList(null);
        Iterable<Product> products = null ;

        try{
            products = productRepository.saveAll(allProductList);
        }catch (Exception exception){
            if(!(exception.getMessage()).contains("OK")){
                System.out.println("nonononon");
                throw exception;
            }
            System.out.println("200 ok");
        }
//        Iterator<Product> iterator = products.iterator();
//        int result = 0;
//        while (iterator.hasNext()) {
//            result++;
//            iterator.next();
//        }
        return allProductList.size();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Product create(Long id) {
        return null;
    }

    @Override
    public void delete(List<Long> ids) {

    }

    @Override
    public Page<Product> search(String keyword, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return productRepository.findByNameOrSubTitleOrKeywords(keyword, keyword, keyword, pageable);
    }
}
