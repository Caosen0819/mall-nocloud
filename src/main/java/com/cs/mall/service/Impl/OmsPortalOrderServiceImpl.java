package com.cs.mall.service.Impl;

import com.cs.mall.common.api.CommonResult;
import com.cs.mall.component.CancelOrderSender;
import com.cs.mall.dto.OrderParam;
import com.cs.mall.service.OmsPortalOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Caosen
 * @Date 2022/8/12 14:01
 * @Version 1.0
 */
@Service
@Slf4j
public class OmsPortalOrderServiceImpl implements OmsPortalOrderService {
    @Autowired
    private CancelOrderSender cancelOrderSender;

    @Override
    public CommonResult generateOrder(OrderParam orderParam) {
        //todo 执行一系类下单操作，具体参考mall项目
        log.info("process generateOrder");
        //下单完成后开启一个延迟消息，用于当用户没有付款时取消订单（orderId应该在下单后生成）
        sendDelayMessageCancelOrder(11L);
        return CommonResult.success(null, "下单成功");
    }

    @Override
    public void cancelOrder(Long orderId) {
        //todo 执行一系类取消订单操作，具体参考mall项目
        log.info("process cancelOrder orderId:{}",orderId);
    }

    private void sendDelayMessageCancelOrder(Long orderId) {
        //获取订单超时时间，假设为60分钟
        long delayTimes = 30 * 1000;
        //发送延迟消息
        cancelOrderSender.sendMessage(orderId, delayTimes);
    }

}