package com.dadalang.x.service.rmq;

import com.rabbitmq.client.*;

/**
 * @version 1.0
 * @Author lj
 * @date 2021/7/29 11:39 上午
 * @desc
 */
public class Send {

    private final static String QUEUE_NAME = "fads";
    private final static String EXCHANGE_NAME = "yyds";

//    public static void main(String[] args) {
//        ConnectionFactory factory = new ConnectionFactory();
//        factory.setHost("localhost");
//        try(Connection connection = factory.newConnection()){
//            Channel channel = connection.createChannel();
//
//            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
//
//            String message = "this is a message";
//            channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
//
//            channel.basicQos(1);
//
//            System.out.println(" [x] Sent '" + message + "'");
//        }catch(Exception e) {
//            e.printStackTrace();
//        }
//    }

    public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try(Connection connection = factory.newConnection()){
            Channel channel = connection.createChannel();

            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);

            String message = "this is a message";
            channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes("utf-8"));

            System.out.println(" [x] Sent '" + message + "'");
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
