package com.cs.mall.service.Impl;

import com.cs.mall.common.api.CommonResult;
import com.cs.mall.service.RedisService;
import com.cs.mall.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Random;

/**
 * @Author Caosen
 * @Date 2022/8/7 19:28
 * @Version 1.0
 * 会员管理Service实现类
 */
@Service
public class UmsMemberServiceImpl implements UmsMemberService {

    @Autowired
    RedisService redisService;
    @Value("${redis.key.prefix.authCode}")
    private String REDIS_KEY_PREFIX_AUTH_CODE;
    @Value("${redis.key.expire.authCode}")
    private Long AUTH_CODE_EXPIRE_SECONDS;

    @Override
    public CommonResult generateAuthCode(String telephone) {
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6 ; i++) {
            stringBuilder.append(random.nextInt());
        }
        redisService.set(REDIS_KEY_PREFIX_AUTH_CODE + telephone, stringBuilder.toString());
        redisService.expire(REDIS_KEY_PREFIX_AUTH_CODE + telephone, AUTH_CODE_EXPIRE_SECONDS);


        return CommonResult.success(stringBuilder.toString(), "获取验证码成功");
    }

    @Override
    public CommonResult verifyAuthCode(String telephone, String authCode) {

        if (authCode.length() == 0){
            return CommonResult.failed("请输入验证码");
        }
        String realAuthCode = (String) redisService.get(REDIS_KEY_PREFIX_AUTH_CODE + telephone);
        if (authCode.equals(realAuthCode)) {
            return CommonResult.success(null, "验证码校验成功");
        } else {
            return CommonResult.failed("校验失败");
        }

    }
}
