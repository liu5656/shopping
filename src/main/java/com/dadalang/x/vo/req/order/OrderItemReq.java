package com.dadalang.x.vo.req.order;

import lombok.Data;

/**
 * @version 1.0
 * @Author lj
 * @date 2021/6/2 5:42 下午
 * @desc
 */
@Data
public class OrderItemReq {
    private int productId;
    private int num;
    private double price;
}
