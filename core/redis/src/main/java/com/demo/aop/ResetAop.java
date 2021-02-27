package com.demo.aop;

import com.demo.annotation.Lock;
import com.demo.util.RedisString;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;

@Aspect
@Configuration
@Slf4j
public class ResetAop {
    @Autowired
    private RedisString redisString;

    @Around(value="@annotation(lock)")
    public Object around(ProceedingJoinPoint joinPoint, Lock lock) throws Throwable {
        Signature signature = joinPoint.getSignature(); //方法签名
        MethodSignature methodSignature = (MethodSignature)signature;//强转
        Method method = methodSignature.getMethod(); //获取的方法对象
        Lock lk = method.getAnnotation(Lock.class);//获取方法上的注解
        Object[] args = joinPoint.getArgs();
        StringBuilder key = new StringBuilder(lk.prefix());
        for (Object o:args) {//遍历
            if (o != null) {
                key.append(o.toString());
            }
        }
        log.info("锁key: {}",key);
        String v = redisString.lock(key.toString(), lk.timeout());
        if (v != null) { //锁被使用,抛出异常
            throw lk.exception().newInstance();
        }
        Object o = joinPoint.proceed();
        redisString.remove(key.toString());//释放锁
        return o;
    }
}