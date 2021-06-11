package com.dadalang.x.util.jwt;

import com.auth0.jwt.interfaces.Claim;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Map;

// https://wangmaoxiong.blog.csdn.net/article/details/108164388     Spring @Aspect、@Before、@After 注解实现 AOP 切面功能

/**
 * @version 1.0
 * @Author lj
 * @date 2021/5/21 5:33 下午
 * @desc
 */
@Aspect
@Component
public class JwtAspect {

    @Pointcut("@annotation(com.dadalang.x.util.jwt.PassToken)")
    public void tokenAspect() {
        System.out.println("will begin aop");
    }

    @Before("tokenAspect()")
    public void aspectBefor(JoinPoint joinPoint) throws Exception {
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader("Authorization");
        Map<String, Claim> map = JwtConfig.validate(token);
        String accountId = map.get("accountId").asString();
        if (accountId != null && accountId.length() > 0) {
            request.setAttribute("accountId", accountId);
            System.out.println("token passed for AccountId: " + accountId);
        }else{
            throw new Exception("token failed");
        }
        String url = request.getRequestURI();
        String method = request.getMethod();
        String header = request.getHeaderNames().toString();
        String parameters = request.getParameterMap().toString();
        System.out.println("url: " + method + " " + url + "\nheader: " + header + "\nparameters: " + parameters);
    }

    @After(value = "tokenAspect()")
    public void aspectAfter(JoinPoint joinPoint) {
        System.out.println("after 通知: " + joinPoint.getKind());
    }

    @AfterReturning(value = "tokenAspect()", returning = "obj")
    public void aspectAfterReturning(JoinPoint joinPoint, Object obj) {
        System.out.println("afterReturning 通知：" + obj.toString());
    }

    @AfterThrowing(value = "tokenAspect()", throwing = "ex")
    public void aspectAfterThrowing(JoinPoint joinPoint, Exception ex) {
        System.out.println("afterThrowing 通知：" + ex.toString());
    }

}
