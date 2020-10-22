package com.clh.rabbitmq.ps;

import com.clh.rabbitmq.util.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
/*** 先 启动生产者创建交换机 ***/
public class Send {

    private static final String EXCHANGE_NAME = "test_exchange_fanout";
    public static void main(String[] args) throws IOException, TimeoutException {

        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        //声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");//分发
        //发送消息
        String msgFanout = "hello ps fanout";

        channel.basicPublish(EXCHANGE_NAME,"",null,msgFanout.getBytes());
        System.out.println("Send :"+msgFanout);

        channel.close();
        connection.close();



    }
}
