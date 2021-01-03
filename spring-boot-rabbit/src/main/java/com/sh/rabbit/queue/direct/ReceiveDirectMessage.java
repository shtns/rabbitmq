package com.sh.rabbit.queue.direct;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 接收直连消息
 *
 *
 * @author 盛浩
 * @date 2021/1/1 11:38
 */
@Component
@RabbitListener(queuesToDeclare = @Queue(value = "queue_direct", autoDelete = "true"))
public class ReceiveDirectMessage {

    /**
     * 接收消息
     *
     * @param message 消息
     */
    @RabbitHandler
    public void receiveMessage(String message) {
        System.out.println("direct开始接收消息：" + message);
    }
}
