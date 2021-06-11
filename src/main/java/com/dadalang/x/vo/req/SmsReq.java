package com.dadalang.x.vo.req;

import lombok.Data;
//import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @version 1.0
 * @Author lj
 * @date 2021/5/17 4:11 下午
 */
@Data
public class SmsReq {

    @NotBlank(message = "mobile num cann't be empty")
    @Pattern(regexp = "^(\\+?0?86\\-?)?1[345789]\\d{9}$", message = "请正确输入手机号码")
    private String mobile;

    @NotNull(message = "type cann't be empty")
    private int type;
}
