package com.dadalang.x.entity.user;

import lombok.Data;

import java.io.Serializable;

/**
 * @version 1.0
 * @Author lj
 * @date 2021/5/20 4:19 下午
 * @desc
 */
@Data
public class User implements Serializable {
    public int id;              // id
    public String username;     // 用户名
    public String img;          // 用户头像
}
