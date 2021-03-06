package com.clh.rabbitmq.topic;

import com.clh.rabbitmq.util.ConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Recv2 {
    private static final String EXCHANGE_NAME ="acciGpVatLossExchange";
    private static final String QUEUE_NAME ="test_exchange_topic_2";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();
        final Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        channel.basicQos(1);
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"key.claim.fin.invoice.*");
        //定义一个消费者
        Consumer consumer = new DefaultConsumer(channel) {
            //消息到达触发这个方法
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body, "utf-8");
                System.out.println("[2]  Recv msg:" + msg);

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    System.out.println("[2] done ");

                    channel.basicAck(envelope.getDeliveryTag(),false);
                }
            }
        };
        boolean autoAck = false;//自动应答 false
        channel.basicConsume(QUEUE_NAME,autoAck,consumer);

    }
}
