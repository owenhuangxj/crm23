package com.ss.crm.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
/**
 * @Author: 王顺坤
 * @Date: 2019/1/11 10:24
 * @Description: service包切面类
 */
@Aspect
@Component
public class AspectTest {

    @Pointcut("execution(public * com.ss.crm.service.*.*(..))")
    public void aop(){}

    // 在方法之前执行
    @Before("aop()")
    public void before(JoinPoint jp){
        System.out.println("before：" + jp.getSignature().getName());
    }

    // 在方法之后执行
    @After("aop()")
    public void after(JoinPoint jp){
        System.out.println("after：" + jp.getSignature().getName());
    }

    // 在方法返回成功后执行
    @AfterReturning("aop()")
    public void returning(JoinPoint jp){
        System.out.println("returning：" + jp.getSignature().getName());
    }




}
