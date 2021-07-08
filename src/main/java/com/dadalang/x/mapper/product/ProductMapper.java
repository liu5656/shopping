package com.dadalang.x.mapper.product;

import com.dadalang.x.entity.product.Product;
import com.dadalang.x.util.masterslave.DataSource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

/**
 * @version 1.0
 * @Author lj
 * @date 2021/6/3 9:38 上午
 * @desc
 */
@Mapper
public interface ProductMapper {
//    @Select("select * from product where id=#{id} and id2=#{xxid}")
//    Product selectProductById(int id, @Param("xxid") String xxid);
    @DataSource("slave")
    @Select("select * from product where id=#{id}")
    Product selectProductById(int id);
}
