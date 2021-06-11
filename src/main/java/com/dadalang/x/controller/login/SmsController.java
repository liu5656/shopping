package com.dadalang.x.controller.login;

import com.dadalang.x.vo.req.SmsReq;
import com.dadalang.x.service.login.SmsService;
import com.dadalang.x.vo.res.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @version 1.0
 * @Author lj
 * @date 2021/5/17 4:56 下午
 */
@RestController
@RequestMapping("/sms")
public class SmsController {


    @Autowired SmsService smsService;

    @GetMapping("/get")
    public Response sendSms(@Valid SmsReq req) {
        return smsService.sendSms(req);
    }
}
