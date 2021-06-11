package com.dadalang.x.service.user;

import com.dadalang.x.dto.user.AccountUserDto;
import com.dadalang.x.entity.user.Account;
import com.dadalang.x.entity.user.User;
import com.dadalang.x.mapper.user.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @version 1.0
 * @Author lj
 * @date 2021/5/24 6:00 下午
 * @desc
 */
@Service
public class UserService {

    @Autowired UserMapper userMapper;

    public User findByUserId(String id) {
        // 从数据库中获取
        return new User();
    }
    public AccountUserDto findByAccoundId(String id) {
        return userMapper.findByAccountId(id);
    }
}
