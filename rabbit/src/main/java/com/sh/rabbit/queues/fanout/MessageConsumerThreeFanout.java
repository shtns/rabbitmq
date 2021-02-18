package com.sh.rabbit.queues.fanout;

import com.rabbitmq.client.*;
import com.sh.rabbit.queues.util.RabbitMqConnection;
import java.io.IOException;

/**
 * 消息消费者三（广播）
 *
 *
 * @author 盛浩
 * @date 2020/12/31 22:13
 */
public class MessageConsumerThreeFanout {

    public static void main(String[] args) throws IOException {

        //获取连接对象
        Connection connection  = RabbitMqConnection.getConnection();

        //获取连接通道
        Channel channel = connection.createChannel();

        //每次只能消费一个消息
        channel.basicQos(1);

        //声明交换机名称
        String exchangeName = "exchange_fanout";

        //将通道绑定到指定交换机上
        //参数1：交换机名称
        //参数2：交换机类型
        //参数3：队列特性是否需要持久化
        //参数4：是否在消息消费完之后自动删除队列
        //参数5：额外附加参数
        channel.exchangeDeclare(exchangeName, "fanout", Boolean.TRUE, Boolean.TRUE, null);

        //临时队列
        String queueName = channel.queueDeclare().getQueue();

        //绑定交换机和临时队列
        //参数1：临时队列名称
        //参数2：交换机名称
        //参数3：路由key
        //参数4：额外附加参数
        channel.queueBind(queueName, exchangeName, "", null);

        //消费消息
        //参数1：队列名称，消费那个队列的消息
        //参数2：消息自动确认机制，是否自动确认消息消费完成
        //参数3：消费消息时的回调接口
        channel.basicConsume(queueName, Boolean.FALSE, new DefaultConsumer(channel) {

            /**
             * 消费消息回调方法
             *
             * @param consumerTag 消费标签
             * @param envelope rabbitmq基本方法参数
             * @param properties 消息属性
             * @param body 消息队列中取出的消息
             * @throws IOException io异常
             */
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者3开始消费信息：" + new String(body));
                //手动确认消息已消费
                //参数1：确认队列那个具体消息
                //参数2：是否开启多个消息同时确认
                channel.basicAck(envelope.getDeliveryTag(), Boolean.FALSE);
            }
        });
    }
}
