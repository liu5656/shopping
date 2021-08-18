package com.dadalang.x.service.rmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @version 1.0
 * @Author lj
 * @date 2021/7/29 4:52 下午
 * @desc
 */
@Component
public class MqSender {
    @Autowired private AmqpTemplate amqpTemplate;

    public void send(String message) {
        System.out.println("发送消息：" + message);
        amqpTemplate.convertAndSend("myqueue3", message);
    }
}
