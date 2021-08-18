package com.dadalang.x.service.rmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @version 1.0
 * @Author lj
 * @date 2021/7/29 5:20 下午
 * @desc
 */

@Component
public class MqReceiver {
    @RabbitListener(queues = "myqueue3")
    public void receive(String msg) {
        System.out.println("接收到消息：" + msg);
    }
}
