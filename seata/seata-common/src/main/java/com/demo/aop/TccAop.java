package com.demo.aop;

import cn.hutool.core.util.StrUtil;
import com.demo.util.RedisString;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;

/**
 * @author root
 * @description TODO
 * @date 2021/3/19 16:46
 */
@Aspect
@Configuration
@Slf4j
public class TccAop {

    @Autowired
    private RedisString redisString;

    @Around(value = "@annotation(tcc)")
    public Object around(ProceedingJoinPoint joinPoint, Tcc tcc) throws Throwable {
        Signature signature = joinPoint.getSignature(); //方法签名
        MethodSignature methodSignature = (MethodSignature) signature;//强转
        Method method = methodSignature.getMethod(); //获取的方法对象
        Tcc lk = method.getAnnotation(Tcc.class);//获取方法上的注解
        String xid = RootContext.getXID();
        String key = lk.prefix() + xid;
        log.info("aop xid: {}",key);
        if (lk.type()) {
            Object proceed = joinPoint.proceed();
            if ((boolean) proceed) {
                redisString.set(key, "1");// 生产由redis实现
            }
            return proceed;
        } else {
            String s = redisString.get(key);
            if (StrUtil.isBlank(s)) {
                return true; // 空回滚
            }
            Object proceed = joinPoint.proceed();//执行业务逻辑
            if ((boolean)proceed){
                redisString.remove(key);
            }
            //表示这是二阶段..
            return proceed;
        }
    }
}
