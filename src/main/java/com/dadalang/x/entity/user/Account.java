package com.dadalang.x.entity.user;

import com.dadalang.x.util.tools.DateEx;
import lombok.Data;

import java.util.Date;
/**
 * @version 1.0
 * @Author lj
 * @date 2021/5/26 2:42 下午
 * @desc
 */
@Data
public class Account {
    private int accountId;
    private int userId;
    private String mobile;
    private Date created = DateEx.timestamp();
    private Date updated = DateEx.timestamp();
    private Date lastLoginTime = DateEx.timestamp();
}
