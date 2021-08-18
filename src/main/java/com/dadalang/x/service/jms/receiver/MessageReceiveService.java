package com.dadalang.x.service.jms.receiver;

import com.dadalang.x.entity.order.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;

import javax.jms.JMSException;
import javax.jms.Message;

/**
 * @version 1.0
 * @Author lj
 * @date 2021/7/19 5:43 下午
 * @desc
 */
public class MessageReceiveService implements MessageReceiver{

    private String destination = "";
    private JmsTemplate jms;
    private MessageConverter converter;

    @Autowired
    public MessageReceiveService(JmsTemplate jms, MessageConverter converter) {
        this.jms = jms;
        this.converter = converter;
    }

    @Override
    public Order receive() throws JMSException {
        Message message = jms.receive(destination);
        Order order = (Order) converter.fromMessage(message);
        return order;
    }
}
