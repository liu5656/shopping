package com.dadalang.x.mapper.user;

import com.dadalang.x.dto.user.AccountUserDto;
import com.dadalang.x.entity.user.Account;
import com.dadalang.x.entity.user.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

/**
 * @version 1.0
 * @Author lj
 * @date 2021/5/26 2:05 下午
 */
@Mapper
public interface UserMapper {
    @Select("select * from account where mobile=#{mobile}")
    Account findByMobile(String mobile);

    @Select("select " +
            "a.accountId, a.userId, a.mobile, a.created, a.updated, a.lastLoginTime, u.username, u.img " +
            "from account as a " +
            "LEFT JOIN " +
            "user as u on a.userId = u.id " +
            "where a.accountId=#{id}")
    AccountUserDto findByAccountId(String id);

    @Insert("insert into account (userId, mobile, created, updated, lastLoginTime) values (#{userId}, #{mobile}, #{created}, #{updated}, #{lastLoginTime})")
    @SelectKey(before = false, keyColumn = "accountId", keyProperty = "accountId", statement = "select last_insert_id()", resultType = Integer.class)
    int insertAccount(Account account);

    @Insert("insert into user (username, img) values (#{username}, #{img})")
    @SelectKey(before = false, keyColumn = "id", keyProperty = "id", statement = "select last_insert_id()", resultType = Integer.class)
    int insertUser(User user);
}
