package com.cs.mall.controller;

import com.cs.mall.common.api.CommonResult;
import com.cs.mall.service.UmsMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Caosen
 * @Date 2022/8/7 19:26
 * @Version 1.0
 * 根据电话号码获取验证码的接口和校验验证码的接口
 * 会员登录注册管理Controller
 */

@Controller
@Tag(name = "UmsMemberController会员登录注册管理Controller")
@RequestMapping("/sso")
public class UmsMemberController {
    @Autowired
    private UmsMemberService umsMemberService;

    @Operation(summary= "获取验证码")
    @ResponseBody
    @RequestMapping(value = "/getAuthCode", method = RequestMethod.GET)
    public CommonResult getAuthCode(@RequestParam String telephone){
        return umsMemberService.generateAuthCode(telephone);
    }

    @Operation(summary= "校验验证码")
    @ResponseBody
    @RequestMapping(value = "/vertifyAuthCode", method = RequestMethod.POST)
    public CommonResult updatePassword(@RequestParam String telephone,
                                       @RequestParam String authCode) {
        return umsMemberService.verifyAuthCode(telephone, authCode);
    }



}
