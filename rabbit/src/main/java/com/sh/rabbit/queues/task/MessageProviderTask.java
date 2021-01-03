package com.sh.rabbit.queues.task;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import com.sh.rabbit.queues.util.RabbitMqConnection;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 消息提供者（任务）
 *
 *
 * @author 盛浩
 * @date 2020/12/31 19:36
 */
public class MessageProviderTask {

    public static void main(String[] args) throws IOException {

        //获取连接对象
        Connection connection  = RabbitMqConnection.getConnection();

        //获取连接通道
        Channel channel = connection.createChannel();

        //声明队列名称
        String queueName = "queue_work";

        //通道绑定对应消息队列
        //参数1：队列名字，如果队列不存在，则自动创建
        //参数2：队列特性是否需要持久化
        //参数3：是否独占队列，当前队列是否可以被其他连接
        //参数4：是否在消息消费完之后自动删除队列
        //参数5：额外附加参数
        channel.queueDeclare(queueName, true, false, true, null);

        //循环发送10消息
        for (int i = 0; i < 10; i++) {
            //发布消息
            //参数1：交换机名称
            //参数2：队列名称
            //参数3：传递消息额外设置 (消息持久化MessageProperties.PERSISTENT_TEXT_PLAIN)
            //参数4：消息内容
            channel.basicPublish("", queueName, MessageProperties.PERSISTENT_TEXT_PLAIN,
                    (i + "：hello rabbitmq task").getBytes(StandardCharsets.UTF_8));
        }

        //关闭通道、连接
        RabbitMqConnection.closeChanelAndConnection(channel, connection);
    }
}
