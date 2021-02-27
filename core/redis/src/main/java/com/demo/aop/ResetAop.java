package com.demo.aop;

import com.demo.annotation.Lock;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.util.Arrays;

@Aspect
@Configuration
public class ResetAop {

    @Around(value="@annotation(lock)")
    public Object around(ProceedingJoinPoint joinPoint, Lock lock) throws Throwable {
        Signature signature = joinPoint.getSignature(); //方法签名
        MethodSignature methodSignature = (MethodSignature)signature;//强转
        Method method = methodSignature.getMethod(); //获取的方法对象
        Lock lk = method.getAnnotation(Lock.class);//获取方法上的注解
        System.out.println(Arrays.toString(method.getParameters()));
        return joinPoint.proceed();
    }
}