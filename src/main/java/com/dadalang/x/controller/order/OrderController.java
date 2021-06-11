package com.dadalang.x.controller.order;

import com.dadalang.x.dto.user.AccountUserDto;
import com.dadalang.x.entity.order.Order;
import com.dadalang.x.service.order.OrderService;
import com.dadalang.x.service.user.UserService;
import com.dadalang.x.util.jwt.PassToken;
import com.dadalang.x.vo.req.order.OrderReq;
import com.dadalang.x.vo.res.Response;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @version 1.0
 * @Author lj
 * @date 2021/6/1 5:26 下午
 * @desc
 */
@RequestMapping("/order")
@RestController
public class OrderController {

    @Autowired OrderService orderService;
    @Autowired UserService userService;

    @PassToken
    @GetMapping("/{id}")
    public Response getOrder(@PathVariable Integer id) {
        Order order = orderService.selectOrderByOrderId(id.intValue());
        return Response.success(order);
    }

    @GetMapping
    @PassToken
    public Response getOrders() {
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        String accountId = (String) request.getAttribute("accountId");
        int id = Integer.valueOf(accountId);
        List<Order> orders = orderService.selectOrdersByAccountId(id);
        return Response.success(orders);
    }

    @PassToken
    @PostMapping
    public Response createOrder(@RequestBody OrderReq req) throws Exception {
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        String accountId = (String) request.getAttribute("accountId");
        int id = Integer.valueOf(accountId);
        int orderId = orderService.insertOrder(req, id);
        return Response.success(orderId);
    }
}
