package com.dadalang.x.service.user;

import com.dadalang.x.dto.user.AccountUserDto;
import com.dadalang.x.entity.user.Account;
import com.dadalang.x.entity.user.User;
import com.dadalang.x.mapper.user.UserMapper;
import com.dadalang.x.util.masterslave.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @version 1.0
 * @Author lj
 * @date 2021/5/24 6:00 下午
 * @desc
 */
@Slf4j
@Service
public class UserService {

    @Autowired UserMapper userMapper;

    public User findByUserId(String id) {
        // 从数据库中获取
        return new User();
    }

    public AccountUserDto findByAccoundId(String id) {
        AccountUserDto userDto = userMapper.findByAccountId(id);
        log.info("写入日志123");
        log.error("错误日志");
        log.warn("警告日志");
        log.debug("debug日志");
        return userDto;
    }
}
