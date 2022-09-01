package com.cs.mall.controller;

import com.cs.mall.common.api.CommonPage;
import com.cs.mall.common.api.CommonResult;
import com.cs.mall.mbg.model.PmsBrand;
import com.cs.mall.service.PmsBrandService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author Caosen
 * @Date 2022/8/7 11:24
 * @Version 1.0
 *
 * 品牌管理
 */

@Tag(name = "PmsBrandController品牌管理")
@Slf4j
@Controller
@RequestMapping(value = "/brand")
public class PmsBrandController {

    @Autowired
    private PmsBrandService pmsBrandService;

    private static final Logger LOGGE = LoggerFactory.getLogger(PmsBrandController.class);

    @Operation(summary="获取所有品牌列表")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<PmsBrand>> getBrandList(){
        log.info("success gain listbrand");
        log.debug("success gain listbrand");
        return CommonResult.success(pmsBrandService.listAllBrand());
    }

    @Operation(summary="创建一个品牌")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult createRrand(@RequestBody PmsBrand pmsBrand){
        /**
         * 1 添加信息（后台），2 刷新
         */
        CommonResult commonResult;
        int count = pmsBrandService.createBrand(pmsBrand);
        if (count == 1) {
            commonResult = CommonResult.success(pmsBrand);
            LOGGE.debug("create success:{}", pmsBrand);
        } else {
            commonResult = CommonResult.failed("操作失败");
            LOGGE.debug("create failed:{}", pmsBrand);
        }
        return commonResult;
    }

    @Operation(summary="根据指定id更新信息")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateBrand(@PathVariable("id") Long id, @RequestBody PmsBrand pmsBrandDto, BindingResult result) {

        CommonResult commonResult;
        int count = pmsBrandService.updateBrand(id, pmsBrandDto);
        if (count == 1) {
            commonResult = CommonResult.success(pmsBrandDto);
            LOGGE.debug("update success:{}", pmsBrandDto);
        } else {
            commonResult = CommonResult.failed("更新失败");
            LOGGE.debug("udpate failed:{}", pmsBrandDto);
        }
        return commonResult;
    }

    @Operation(summary="删除指定的id品牌")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult deleteBrand(@PathVariable("id") Long id) {
        int count = pmsBrandService.deleteBrand(id);
        if (count == 1) {
            LOGGE.debug("deleteBrand success :id={}", id);
            return CommonResult.success(null);
        } else {
            LOGGE.debug("deleteBrand failed :id={}", id);
            return CommonResult.failed("操作失败");
        }
    }

    @Operation(summary="分页查询")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<PmsBrand>> listBrand(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                        @RequestParam(value = "pageSize", defaultValue = "3") Integer pageSize) {

        List<PmsBrand> pmsBrands = pmsBrandService.listBrand(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(pmsBrands));
    }

//    @ApiImplicitParam(name = "id", paramType = "path", dataType = "Long")
    @Operation(summary="获取指定id的品牌")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<PmsBrand> brand(@PathVariable("id") Long id) {
        return CommonResult.success(pmsBrandService.getBrand(id));
    }
}
