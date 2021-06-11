package com.dadalang.x.service.order;

import com.dadalang.x.entity.order.Order;
import com.dadalang.x.entity.order.OrderItem;
import com.dadalang.x.entity.product.Product;
import com.dadalang.x.entity.user.Account;
import com.dadalang.x.mapper.order.OrderMapper;
import com.dadalang.x.mapper.product.ProductMapper;
import com.dadalang.x.vo.req.order.OrderItemReq;
import com.dadalang.x.vo.req.order.OrderReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @version 1.0
 * @Author lj
 * @date 2021/6/1 5:28 下午
 * @desc
 */
@Service
public class OrderService {

    @Autowired ProductMapper productMapper;
    @Autowired OrderMapper orderMapper;

    @Transactional(rollbackFor = Exception.class)
    public int insertOrder(OrderReq req, int accountId) throws Exception {
        // TODO 建立事务
        // TODO order缺少总价的计算
        Order order = new Order();
        order.setState(1);
        order.setAccountId(accountId);
        order.setName(req.getName());
        order.setAddress(req.getAddress());
        order.setMobile(req.getMobile());
        // 插入成功才有orderId
        int result = orderMapper.insertOrder(order);

        for (OrderItemReq itemReq : req.getItems()) {
            OrderItem item = new OrderItem();
            int productId = itemReq.getProductId();
            Product product = productMapper.selectProductById(productId);
            // TODO 处理优惠券相关的问题
            if (product != null && product.getPrice() == itemReq.getPrice()) {
                item.setOrderId(order.getId());
                item.setProduct(product);
                item.setProductNum(itemReq.getNum());
                item.setPrice(product.getPrice());
                result = orderMapper.insertOrderItem(item);
                order.getItems().add(item);
            }else{
                throw new Exception("cann't found product");
            }
        }
        // todo 执行插入订单逻辑
        return result;
    }

    public Order selectOrderByOrderId(int orderId) {
        return orderMapper.selectOrderByOrderId(orderId);
    }

    public List<Order> selectOrdersByAccountId(int accountId) {
        return orderMapper.selectOrderByAccountId(accountId);
    }
}
