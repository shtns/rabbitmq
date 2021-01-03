package com.sh.rabbit.queue.task;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 接收任务消息
 *
 *
 * @author 盛浩
 * @date 2021/1/1 11:47
 */
@Component
public class ReceiveTaskMessage {

    /**
     * 接收消息一
     *
     * @param message 消息
     */
    @RabbitListener(queuesToDeclare = @Queue(value = "queue_task", autoDelete = "true"))
    public void receiveMessageOne(String message) {
        System.out.println("task1开始接收消息：" + message);
    }

    /**
     * 接收消息二
     *
     * @param message 消息
     */
    @RabbitListener(queuesToDeclare = @Queue(value = "queue_task", autoDelete = "true"))
    public void receiveMessageTwo(String message) {
        System.out.println("task2开始接收消息：" + message);
    }
}
