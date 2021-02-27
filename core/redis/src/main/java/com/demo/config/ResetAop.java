package com.demo.config;

import com.demo.annotation.Lock;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;

@Aspect
@Configuration
public class ResetAop {

    @Around(value="@annotation(lock)")
    public Object around(ProceedingJoinPoint joinPoint, Lock lock) throws Throwable {
        Signature signature = joinPoint.getSignature(); //方法签名
        MethodSignature methodSignature = (MethodSignature)signature;//强转
        Method targetMethod = methodSignature.getMethod(); //获取的方法对象
        Lock time = targetMethod.getAnnotation(Lock.class);//获取方法上的注解
        long l = System.currentTimeMillis();
        Object proceed = null ;
        while (true){
            try {
                proceed =  joinPoint.proceed();
                return proceed;
            }catch (Throwable e){
                System.out.println(e.getMessage());
                System.out.println("重试");
            }
            if (System.currentTimeMillis() - l > time.value()) {
                System.out.println(System.currentTimeMillis() - l);
                break;
            }
        }

        System.out.println("执行后");
        return joinPoint.proceed();
    }
}