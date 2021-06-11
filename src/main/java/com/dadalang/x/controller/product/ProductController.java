package com.dadalang.x.controller.product;

import com.dadalang.x.vo.res.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version 1.0
 * @Author lj
 * @date 2021/6/1 5:24 下午
 * @desc
 */
@RequestMapping("/product")
@RestController
public class ProductController {
    @GetMapping
    public Response products() {
        System.out.println("product");
        return  Response.success();
    }
}
