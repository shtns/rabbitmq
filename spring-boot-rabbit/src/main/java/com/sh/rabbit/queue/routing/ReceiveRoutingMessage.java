package com.sh.rabbit.queue.routing;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 接收路由消息
 *
 *
 * @author 盛浩
 * @date 2021/1/1 12:02
 */
@Component
public class ReceiveRoutingMessage {

    /**
     * 接收路由key为one的消息
     *
     * @param message 消息
     */
    @RabbitListener(bindings = {@QueueBinding(value = @Queue(autoDelete = "true"), exchange = @Exchange(value = "exchange_routing"), key = "one")})
    public void receiveMessageOne(String message) {
        System.out.println("routing1开始接收消息：" + message);
    }

    /**
     * 接收路由key为two的消息
     *
     * @param message 消息
     */
    @RabbitListener(bindings = {@QueueBinding(value = @Queue(autoDelete = "true"), exchange = @Exchange(value = "exchange_routing"), key = "two")})
    public void receiveMessageTwo(String message) {
        System.out.println("routing2开始接收消息：" + message);
    }

    /**
     * 接收路由key为one、two、three的消息
     *
     * @param message 消息
     */
    @RabbitListener(bindings = {@QueueBinding(value = @Queue(autoDelete = "true"), exchange = @Exchange(value = "exchange_routing"), key = {"one", "two", "three"})})
    public void receiveMessageOneTwoThree(String message) {
        System.out.println("routing3开始接收消息：" + message);
    }
}
