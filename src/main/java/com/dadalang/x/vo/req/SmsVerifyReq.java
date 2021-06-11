package com.dadalang.x.vo.req;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @version 1.0
 * @Author lj
 * @date 2021/5/19 5:02 下午
 * @desc
 */
@Data
public class SmsVerifyReq {


    @NotBlank(message = "验证码不能为空")
    @Pattern(regexp = "^\\d{5}$", message = "请正确输入验证码")
    private String code;

    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^(\\+?0?86\\-?)?1[345789]\\d{9}$", message = "请正确输入手机号码")
    private String mobile;
}
