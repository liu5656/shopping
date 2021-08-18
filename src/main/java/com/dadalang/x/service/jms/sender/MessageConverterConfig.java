package com.dadalang.x.service.jms.sender;

import com.dadalang.x.entity.order.Order;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;

import java.util.HashMap;
import java.util.Map;


/**
 * @version 1.0
 * @Author lj
 * @date 2021/7/19 4:51 下午
 * @desc
 */
@Configuration
public class MessageConverterConfig {
    @Bean
    public MappingJackson2MessageConverter messageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTypeIdPropertyName("_typeId");

        Map<String, Class<?>> map = new HashMap<>();
        map.put("order", Order.class);
        converter.setTypeIdMappings(map);

        return converter;
    }
}
