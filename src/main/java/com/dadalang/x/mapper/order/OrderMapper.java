package com.dadalang.x.mapper.order;

import com.dadalang.x.entity.order.Order;
import com.dadalang.x.entity.order.OrderItem;
import com.dadalang.x.entity.product.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @version 1.0
 * @Author lj
 * @date 2021/6/1 5:50 下午
 */
@Mapper
public interface OrderMapper {
    @Insert("insert into `order` (created, state, name, address, mobile, account_id ) values (now(), #{state}, #{name}, #{address}, #{mobile}, #{accountId})")
    @SelectKey(before = false, keyColumn = "id", keyProperty = "id", statement = "select last_insert_id()", resultType = Integer.class)
    int insertOrder(Order order);

    @Insert("insert into order_item (order_id, product_id, product_num, product_price) values (#{orderId}, #{product.id}, #{productNum}, #{price})")
    @SelectKey(before = false, keyColumn = "id", keyProperty = "id", statement = "select last_insert_id()", resultType = Integer.class)
    int insertOrderItem(OrderItem item);

    @Select("select * from `order` where account_id=#{accountId}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "account_id", property = "accountId"),
            @Result(property = "items", javaType = List.class, column = "id", many = @Many(select = "com.dadalang.x.mapper.order.OrderMapper.selectOrderItemsByOrderId"))
    })
    List<Order> selectOrderByAccountId(int accountId);

    @Select("select * from order_item where order_id = #{orderId}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "order_id", property = "orderId"),
            @Result(column = "product_num", property = "productNum"),
            @Result(column = "product_price", property = "price"),
            @Result(property = "product", javaType = Product.class, column = "product_id", many = @Many(select = "com.dadalang.x.mapper.product.ProductMapper.selectProductById"))
    })
    List<OrderItem> selectOrderItemsByOrderId(int orderId);

    @Select("select * from `order` where id = #{orderId}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "account_id", property = "accountId"),
            @Result(property = "items", javaType = List.class, column = "id", many = @Many(select = "com.dadalang.x.mapper.order.OrderMapper.selectOrderItemsByOrderId"))
    })
    Order selectOrderByOrderId(int orderId);

}
