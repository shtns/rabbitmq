package com.sh.rabbit;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * spring boot 整合rabbitmq测试类
 *
 *
 * @author 盛浩
 * @date 2021/1/1 11:35
 */
@SpringBootTest
class RabbitApplicationTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送直连消息
     */
    @Test
    void sendDirectMessage() {
        this.rabbitTemplate.convertAndSend("queue_direct", "hello spring boot rabbitmq direct");
    }

    /**
     * 发送任务消息
     */
    @Test
    void sendTaskMessage() {
        for (int i = 0; i < 10; i++) {
            this.rabbitTemplate.convertAndSend("queue_task", "hello spring boot rabbitmq task，" + i);
        }
    }

    /**
     * 发送广播消息
     */
    @Test
    void sendFanoutMessage() {
        this.rabbitTemplate.convertAndSend("exchange_fanout", "", "hello spring boot rabbitmq fanout");
    }

    /**
     * 发送路由消息
     */
    @Test
    void sendRoutingMessage() {
        this.rabbitTemplate.convertAndSend("exchange_routing", "two", "hello spring boot rabbitmq routing");
    }

    /**
     * 发送动态路由消息
     */
    @Test
    void sendTopicMessage() {
        this.rabbitTemplate.convertAndSend("exchange_topic", "one.two.three", "hello spring boot rabbitmq topic");
    }
}
