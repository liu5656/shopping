package com.dadalang.x.service.login;

import com.dadalang.x.entity.SmsScene;
import com.dadalang.x.util.masterslave.DataSource;
import com.dadalang.x.vo.req.SmsVerifyReq;
import com.dadalang.x.vo.req.WxReq;
import com.dadalang.x.entity.user.Account;
import com.dadalang.x.entity.user.User;
import com.dadalang.x.mapper.user.UserMapper;
import com.dadalang.x.util.jwt.JwtConfig;
import com.dadalang.x.util.redis.Redis;
import com.dadalang.x.vo.res.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0
 * @Author lj
 * @date 2021/5/24 5:27 下午
 * @desc
 */
@Service
public class LoginService {

    @Autowired Redis redis;
    @Autowired UserMapper userMapper;

    @Value("${x.redis.index.sms}")
    private int smsIndex;


    public Response loginSms(SmsVerifyReq req) {
        try{
            String code = redis.get(SmsScene.login.getRaw() + req.getMobile(), smsIndex);
            if (code.equals(req.getCode())) {
                Account account = findByMobile(req.getMobile());
                if (account == null) {
                    account = insertUser(req.getMobile());
                }
                Map<String, String> content = new HashMap<>();
                content.put("mobile", account.getMobile());
                content.put("accountId", String.valueOf(account.getAccountId()));
                String token = JwtConfig.token(content);
                Response res = Response.success(token);
                return res;
            }else{
                return Response.failed("wrong code","验证码错误");
            }
        }catch (Exception e) {
            return Response.failed(String.valueOf(e.hashCode()), e.toString());
        }
    }

    public Response loginWx(WxReq req) {
        // 从微信获取信息
        // 区分不同微信商户号的账号
        // 根据微信id去找用户，找到就返回
        // 未找到用户就创建
        return Response.success();
    }

    private Account findByMobile(String mobile) {
        return userMapper.findByMobile(mobile);
    }

    private Account insertUser(String mobile) {
        User user = new User();
        // TODO 先插入用户信息，如果异常的时候回滚
        int result = userMapper.insertUser(user);

        Account account = new Account();
        account.setUserId(user.getId());

        // 再插入账户信息
        account.setMobile(mobile);
        result = userMapper.insertAccount(account);
        return account;
    }

    private User findByWxId(String id) {
        return new User();
    }
}
