package com.dadalang.x.entity.order;

import com.dadalang.x.entity.product.Product;
import lombok.Data;

/**
 * @version 1.0
 * @Author lj
 * @date 2021/6/2 10:15 上午
 * @desc
 */
@Data
public class OrderItem {
    private int id;

    private Product product;
    private int productNum;     // 同样商品数量
    private double price;       // 购买时的价格

    private int orderId;
}
