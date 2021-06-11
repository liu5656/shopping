package com.dadalang.x.vo.req;

import javax.validation.constraints.NotBlank;

/**
 * @version 1.0
 * @Author lj
 * @date 2021/5/24 5:40 下午
 * @desc
 */
public class WxReq {
    @NotBlank(message = "微信token不能为空")
    public String token;
}
