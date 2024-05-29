/**
 * 1. @ClassName RabbitMQConfig
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/12/26 9:47
 */
package com.farm.config;

import org.springframework.amqp.core.*;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

@SpringBootConfiguration
public class RabbitMQConfig {
    public static final String exchangeName = "order_exchange";
    public static final String queueName = "order_queue";
    public static final String routingKey = "order_queue";

    /**
     * 创建延迟队列
     */
    @Bean
    public Queue createQueue() {
        return QueueBuilder.durable(queueName).build();
    }

    /**
     * 创建路由
     */
    @Bean
    public CustomExchange createExchange() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("x-delayed-type", "direct");
        return new CustomExchange(exchangeName, "x-delayed-message", true, false, map);
    }

    /**
     * 绑定路由与队列
     */
    @Bean
    public Binding exchangeBindingQueue() {
        return   BindingBuilder.bind(createQueue()).to(createExchange()).with(routingKey).noargs();
    }
}
