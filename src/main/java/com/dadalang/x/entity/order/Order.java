package com.dadalang.x.entity.order;

import com.dadalang.x.entity.user.User;
import com.dadalang.x.util.tools.DateEx;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @version 1.0
 * @Author lj
 * @date 2021/6/1 5:32 下午
 * @desc
 */
@Data
public class Order implements Serializable {
    private int id;

    private int state;                          // 订单状态：1未付款，2已付款，未发货，3已发货，没收货，4收货，订单结束
    private Date created = DateEx.timestamp();
//    private double total;
    private String name;
    private String address;
    private String mobile;

    private int accountId;
    private List<OrderItem> items = new ArrayList<>();
}
