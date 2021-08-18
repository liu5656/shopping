package com.dadalang.x.service.jms.sender;

import com.dadalang.x.entity.order.Order;

/**
 * @version 1.0
 * @Author lj
 * @date 2021/7/19 4:25 下午
 */
public interface MessagingSender {
    void sendMessage(Order order);
}
