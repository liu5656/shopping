package com.dadalang.x.entity.product;

import lombok.Data;

/**
 * @version 1.0
 * @Author lj
 * @date 2021/6/1 5:33 下午
 * @desc
 */
@Data
public class Product {
    private int id;
    private String title;
    private String subtitle;
    private String shopName;

    private double price;
    private double coupon;
    private int couponNum;
}
