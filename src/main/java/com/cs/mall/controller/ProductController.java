package com.cs.mall.controller;

import com.cs.mall.common.api.CommonPage;
import com.cs.mall.common.api.CommonResult;
import com.cs.mall.dto.Product;
import com.cs.mall.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author Caosen
 * @Date 2022/8/15 16:50
 * @Version 1.0
 */
@Controller
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @RequestMapping(value = "/import", method = RequestMethod.POST)
    @ResponseBody
    @Operation(summary = "导入所有的信息")
    public CommonResult importall(){
        int i = productService.importAll();
        return CommonResult.success(i, "导入成功");
    }

    @Operation(summary = "简单搜索")
    @RequestMapping(value = "/simple", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<Product>> search(@RequestParam(required = false) String keyword,
                                                    @RequestParam(required = false, defaultValue = "0") Integer pageNum,
                                                    @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        Page<Product> productPage = productService.search(keyword, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(productPage));
    }
}
