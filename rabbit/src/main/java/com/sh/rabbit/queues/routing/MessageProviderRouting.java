package com.sh.rabbit.queues.routing;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import com.sh.rabbit.queues.util.RabbitMqConnection;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 消息提供者（路由）
 *
 *
 * @author 盛浩
 * @date 2020/12/31 22:44
 */
public class MessageProviderRouting {

    public static void main(String[] args) throws IOException {

        //获取连接对象
        Connection connection  = RabbitMqConnection.getConnection();

        //获取连接通道
        Channel channel = connection.createChannel();

        //声明交换机名称
        String exchangeName = "exchage_routing";

        //将通道绑定到指定交换机上
        //参数1：交换机名称
        //参数2：交换机类型
        //参数3：队列特性是否需要持久化
        //参数4：是否在消息消费完之后自动删除队列
        //参数5：额外附加参数
        channel.exchangeDeclare(exchangeName, "direct", true, true, null);

        //发布消息
        //参数1：交换机名称
        //参数2：路由key
        //参数3：传递消息额外设置 (消息持久化MessageProperties.PERSISTENT_TEXT_PLAIN)
        //参数4：消息内容
        channel.basicPublish(exchangeName, "one", MessageProperties.PERSISTENT_TEXT_PLAIN,
                "hello rabbitmq routing".getBytes(StandardCharsets.UTF_8));

        //关闭通道、连接
        RabbitMqConnection.closeChanelAndConnection(channel, connection);
    }
}
