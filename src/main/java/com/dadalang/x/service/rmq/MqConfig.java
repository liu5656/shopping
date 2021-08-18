package com.dadalang.x.service.rmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @version 1.0
 * @Author lj
 * @date 2021/7/30 9:48 上午
 * @desc
 */
@Configuration
public class MqConfig {
    @Bean
    public Queue orderqueue() {
        return new Queue("myqueue3");
    }
}
