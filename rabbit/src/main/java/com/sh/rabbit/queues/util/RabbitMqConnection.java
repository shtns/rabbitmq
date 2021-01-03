package com.sh.rabbit.queues.util;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * rabbitmq连接工厂
 *
 *
 * @author 盛浩
 * @date 2020/12/30 21:03
 */
public class RabbitMqConnection {

    /**
     * 私有静态化工厂对象
     */
    private static final ConnectionFactory CONNECTION_FACTORY;

    static {
        //创建连接mq的连接工厂对象
        CONNECTION_FACTORY = new ConnectionFactory();
        //设置连接rabbitmq主机
        CONNECTION_FACTORY.setHost("115.159.0.240");
        //设置端口号
        CONNECTION_FACTORY.setPort(5672);
        //设置连接那个虚拟机
        CONNECTION_FACTORY.setVirtualHost("/");
        //设置访问虚拟主机的用户名和密码
        CONNECTION_FACTORY.setUsername("admin");
        CONNECTION_FACTORY.setPassword("admin");
    }

    /**
     * 提供连接对象
     *
     * @return 连接对象
     */
    public static Connection getConnection() {
        try {
            return CONNECTION_FACTORY.newConnection();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 关闭通道和连接
     *
     * @param channel 通道
     * @param connection 连接
     */
    public static void closeChanelAndConnection(Channel channel, Connection connection) {
        try {
            if (channel != null) {
                channel.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
