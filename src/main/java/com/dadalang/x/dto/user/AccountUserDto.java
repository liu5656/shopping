package com.dadalang.x.dto.user;

import com.dadalang.x.entity.user.Account;
import lombok.Data;

/**
 * @version 1.0
 * @Author lj
 * @date 2021/6/1 4:30 下午
 * @desc
 */
@Data
public class AccountUserDto extends Account {
    private String img;
    private String username;
}
