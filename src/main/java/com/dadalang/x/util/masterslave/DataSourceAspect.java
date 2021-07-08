package com.dadalang.x.util.masterslave;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @version 1.0
 * @Author lj
 * @date 2021/7/5 5:07 下午
 * @desc
 */
@Aspect
@Component
public class DataSourceAspect {
    @Pointcut("@annotation(com.dadalang.x.util.masterslave.DataSource))")       //切点为所有的mapper接口
    public void pointcut(){}

    @Before("pointcut()")
    public void before(JoinPoint point) {
        DataSource data = ((MethodSignature)point.getSignature()).getMethod().getAnnotation(DataSource.class);
        System.out.println("用户选择的数据库类型： " + data.value());
        HandleDataSource.putDataSource(data.value());                           // 数据源放到当前线程中
    }
}
