package com.dadalang.x.controller.login;

import com.dadalang.x.vo.req.SmsVerifyReq;
import com.dadalang.x.vo.req.WxReq;
import com.dadalang.x.service.login.LoginService;
import com.dadalang.x.vo.res.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @version 1.0
 * @Author lj
 * @date 2021/5/17 3:51 下午
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired LoginService loginService;

    @GetMapping("/sms")
    public Response sms(@Valid SmsVerifyReq req) {
        return loginService.loginSms(req);
    }

    @GetMapping("/wx")
    public Response sms(@Valid WxReq req) {
        return loginService.loginWx(req);
    }
}
