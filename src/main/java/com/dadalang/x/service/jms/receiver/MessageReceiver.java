package com.dadalang.x.service.jms.receiver;

import com.dadalang.x.entity.order.Order;

import javax.jms.JMSException;

/**
 * @version 1.0
 * @Author lj
 * @date 2021/7/19 5:39 下午
 */
public interface MessageReceiver {
    Order receive() throws JMSException;
}
