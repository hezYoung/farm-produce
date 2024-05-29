/**
 * 1. @ClassName DelayedMQSender
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/12/26 9:58
 */
package com.farm.mq;

import com.farm.config.RabbitMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class DelayedMQSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void Send(String orderId, Integer delayTime) {
        log.info("测试发送订单延时消息 {} : {}", delayTime, orderId);
        //将消息携带路由键值
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.exchangeName,//交换机名称
                RabbitMQConfig.routingKey,//路由 key
                orderId,
                message -> {
                    //设置延时的时间  单位毫秒
                    message.getMessageProperties().setDelay(delayTime);
                    return message;
                });
    }

}

