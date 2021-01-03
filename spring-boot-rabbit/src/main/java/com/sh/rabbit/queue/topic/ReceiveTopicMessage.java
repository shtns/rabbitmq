package com.sh.rabbit.queue.topic;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 接收动态路由消息
 *
 *
 * @author 盛浩
 * @date 2021/1/1 12:09
 */
@Component
public class ReceiveTopicMessage {

    /**
     * 接收满足动态路由key消息一
     *
     * @param message 消息
     */
    @RabbitListener(bindings = @QueueBinding(value = @Queue(autoDelete = "true"), exchange = @Exchange(value = "exchange_topic", type = "topic"), key = "*.two.*"))
    public void receiveMeetKeyMessageOne(String message) {
        System.out.println("topic1开始接收消息：" + message);
    }

    /**
     * 接收满足动态路由key消息二
     *
     * @param message 消息
     */
    @RabbitListener(bindings = @QueueBinding(value = @Queue(autoDelete = "true"), exchange = @Exchange(value = "exchange_topic", type = "topic"), key = "one.#"))
    public void receiveMeetKeyMessageTwo(String message) {
        System.out.println("topic2开始接收消息：" + message);
    }
}
