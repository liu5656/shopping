package com.dadalang.x.service.rmq;

import com.rabbitmq.client.*;

/**
 * @version 1.0
 * @Author lj
 * @date 2021/7/29 4:24 下午
 * @desc
 */
public class Recv {

    private final static String QUEUE_NAME = "fads";
    private final static String EXCHANGE_NAME = "yyds";


//    public static void main(String[] argv) throws Exception {
//        ConnectionFactory factory = new ConnectionFactory();
//        factory.setHost("localhost");
//        Connection connection = factory.newConnection();
//        Channel channel = connection.createChannel();
//
//        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
//
//        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
//
//        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
//            String message = new String(delivery.getBody(), "UTF-8");
//            System.out.println(" [x] Received '" + message + "'");
////            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
//        };
//        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
//    }

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        String queue = channel.queueDeclare().getQueue();
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);
        channel.exchangeBind(queue, EXCHANGE_NAME, "");

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");
        };
        channel.basicConsume(queue, true, deliverCallback, consumerTag -> { });
    }
}
