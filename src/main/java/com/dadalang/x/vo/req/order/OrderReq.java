package com.dadalang.x.vo.req.order;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;


/**
 * @version 1.0
 * @Author lj
 * @date 2021/6/1 5:44 下午
 * @desc
 */
@Data
public class OrderReq {
    // TODO 表单中数据添加约束
    @NotBlank(message = "姓名不能为空")
    private String name;

    @NotBlank(message = "请输入收货地址")
    private String address;

    @NotBlank(message = "请输入收件人手机号")
    private String mobile;

    private List<OrderItemReq> items;

}
