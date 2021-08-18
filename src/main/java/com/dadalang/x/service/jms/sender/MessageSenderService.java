package com.dadalang.x.service.jms.sender;

import com.dadalang.x.entity.order.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.JMSException;
import javax.jms.Message;

/**
 * @version 1.0
 * @Author lj
 * @date 2021/7/19 4:16 下午
 * @desc
 */
public class MessageSenderService implements MessagingSender {

    private JmsTemplate jms;
    private String destination = "tacocloud.order.queue";

    @Autowired
    public MessageSenderService(JmsTemplate jms) {
        this.jms = jms;
    }

    @Override
    public void sendMessage(Order order) {
//        法二
        jms.send(destination, ses -> ses.createObjectMessage(order));

//       法一
//        MessageCreator temp = new MessageCreator() {
//            @Override
//            public Message createMessage(Session session) throws JMSException {
//                return session.createObjectMessage(order);
//            }
//        };
//        jms.send(temp);
    }

    public void sendMessageAndConvert(Order order)  {
//        法三
        jms.convertAndSend(destination, order, this::addOrderSource);

////        法二
//        jms.convertAndSend(destination, order, message -> {
//            message.setStringProperty("X_ORDER_SOURCE", "WEB");
//            return message;
//        });

////        法一
//        jms.convertAndSend(destination, order);
    }

    private Message addOrderSource(Message message) throws JMSException {
        message.setStringProperty("X_ORDER_SOURCE", "WEB");
        return message;
    }
}
