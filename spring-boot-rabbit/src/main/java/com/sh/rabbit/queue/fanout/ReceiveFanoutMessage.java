package com.sh.rabbit.queue.fanout;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 接收广播消息
 *
 *
 * @author 盛浩
 * @date 2021/1/1 11:52
 */
@Component
public class ReceiveFanoutMessage {

    /**
     * 接收消息一
     *
     * @param message 消息
     */
    @RabbitListener(bindings = {@QueueBinding(value = @Queue(autoDelete = "true") , exchange = @Exchange(value = "exchange_fanout", type = "fanout"))})
    public void reveiceMessageOne(String message) {
        System.out.println("fanout1开始接收消息：" + message);
    }

    /**
     * 接收消息二
     *
     * @param message 消息
     */
    @RabbitListener(bindings = {@QueueBinding(value = @Queue(autoDelete = "true"), exchange = @Exchange(value = "exchange_fanout", type = "fanout"))})
    public void reveiceMessageTwo(String message) {
        System.out.println("fanout2开始接收消息：" + message);
    }
}
