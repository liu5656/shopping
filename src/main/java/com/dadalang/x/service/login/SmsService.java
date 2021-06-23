package com.dadalang.x.service.login;

import com.dadalang.x.vo.req.SmsReq;
import com.dadalang.x.entity.SmsScene;
import com.dadalang.x.util.redis.Redis;
import com.dadalang.x.util.tools.StringEx;
import com.dadalang.x.vo.res.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @version 1.0
 * @Author lj
 * @date 2021/5/17 5:56 下午
 * @desc 仅仅处理短信发送相关的逻辑
 */
@Service
public class SmsService {

    @Autowired Redis redis;

    @Value("${x.redis.index.sms}")
    private int smsIndex;

    @Value("${x.sms.max}")
    private int smsMax;

    public Response sendSms(SmsReq req) {
        // 处理业务逻辑，每天最大限制，IP最大限制
        String mobile = req.getMobile();
        String cachedNum = redis.get(mobile, smsIndex);
        if (cachedNum == null) {
            cachedNum = "0";
        }
        int num = Integer.parseInt(cachedNum);
        if (num >= smsMax) {
            return Response.failed("MAX_SMS", "达单日验证码次数上限");
        }else{
            num++;
            redis.set(mobile, String.valueOf(num), smsIndex, 300);
        }

        String code = StringEx.randomNum(5);
        SmsScene scene = SmsScene.form(req.getType());
        redis.set(scene.getRaw() + mobile, code, smsIndex, 300);

        System.out.println("did sended to mobile:" + mobile + " and code:" + code);
        return Response.success(code + "-x");
    }





}
