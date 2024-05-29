/**
 * 1. @ClassName DelayedMQRecieve
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/12/26 10:01
 */
package com.farm.mq;

import com.farm.config.RabbitMQConfig;
import com.farm.model.entity.order.OrderInfo;
import com.farm.service.OrderInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RabbitListener(queues = RabbitMQConfig.queueName)//监听队列名称
public class DelayedMQRecieve {
    public static final int ZERO = 0;
    @Autowired
    private OrderInfoService orderInfoService;
    @RabbitHandler
    public void process(String orderId){
        log.info("接收到延时取消订单id消息: 订单ID={}", orderId);
        OrderInfo orderInfo = orderInfoService.getOrderInfo(orderId);
        if (orderInfo.getOrderStatus() == ZERO) {
        orderInfoService.cancleOrder(orderId);
            log.info("订单已经取消");
        }
    }
}

