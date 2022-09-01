package com.cs.mall.service;

import com.cs.mall.common.api.CommonResult;
import com.cs.mall.dto.OrderParam;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author Caosen
 * @Date 2022/8/12 14:01
 * @Version 1.0
 */
public interface OmsPortalOrderService {

    /**
     * 根据提交信息生成订单
     */
    @Transactional
    CommonResult generateOrder(OrderParam orderParam);

    /**
     * 取消单个超时订单
     */
    @Transactional
    void cancelOrder(Long orderId);
}
