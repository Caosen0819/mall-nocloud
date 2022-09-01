package com.cs.mall.service;

import com.cs.mall.common.api.CommonResult;

/**
 * @Author Caosen
 * @Date 2022/8/7 19:28
 * @Version 1.0
 */
public interface UmsMemberService {
    /**
     * 获取验证码并且储存
     * @param telephone
     * @return
     */
    CommonResult generateAuthCode(String telephone);

    /**
     * 校验验证码是否正确
     * @param telephone
     * @param authCode
     * @return
     */
    CommonResult verifyAuthCode(String telephone, String authCode);
}
