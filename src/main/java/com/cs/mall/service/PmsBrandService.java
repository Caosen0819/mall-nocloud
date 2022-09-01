package com.cs.mall.service;

import com.cs.mall.mbg.model.PmsBrand;

import java.util.List;

/**
 * @Author Caosen
 * @Date 2022/8/7 11:28
 * @Version 1.0
 */
public interface PmsBrandService {
    List<PmsBrand> listAllBrand();

    int createBrand(PmsBrand pmsBrand);

    int updateBrand(Long id, PmsBrand pmsBrandDto);

    int deleteBrand(Long id);

    List<PmsBrand> listBrand(int pageNum, int pageSize);

    PmsBrand getBrand(Long id);
}
