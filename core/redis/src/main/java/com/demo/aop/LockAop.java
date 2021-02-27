package com.demo.aop;

import com.demo.annotation.Lock;
import com.demo.util.AopUtil;
import com.demo.util.RedisString;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Map;

@Aspect
@Configuration
@Slf4j
public class LockAop {
    @Autowired
    private RedisString redisString;

    /** 配套手动释放锁 **/
    public static ThreadLocal<String> lock = new ThreadLocal<>();
    public static void set(String key){
        lock.set(key);
    }
    public static String get(){
        return lock.get();
    }

    @Around(value = "@annotation(lock)")
    public Object around(ProceedingJoinPoint joinPoint, Lock lock) throws Throwable {
        Signature signature = joinPoint.getSignature(); //方法签名
        MethodSignature methodSignature = (MethodSignature) signature;//强转
        Method method = methodSignature.getMethod(); //获取的方法对象
        Lock lk = method.getAnnotation(Lock.class);//获取方法上的注解
        Object[] args = joinPoint.getArgs();
        StringBuilder key = new StringBuilder(lk.prefix());
        for (Object o : args) {//遍历
            if (o != null) {
                key.append(o.toString()).append(":");
            }
        }
        log.info("锁key: {}", key);
        Map<String, Object> map = AopUtil.getMap(joinPoint.getArgs(), method.getParameters());
        log.info("参数map: {}",map);
        String v = redisString.lock(key.toString(), lk.timeout());
        if (v != null) { //锁被使用,抛出异常
            Constructor<? extends Throwable> constructor = lk.exception().getConstructor(String.class);
            throw constructor.newInstance(lk.msg());
        }
        Object o;
        try {
            o = joinPoint.proceed();
        }catch (Throwable e) {
            throw new RuntimeException(e);
        } finally {//不管业务结果,都必须释放锁
            if (!lk.isLock()){
                redisString.remove(key.toString());//释放锁
            }
        }
        return o;
    }
}