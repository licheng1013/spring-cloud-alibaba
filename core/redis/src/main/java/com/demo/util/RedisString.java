package com.demo.util;

import com.demo.aop.LockAop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @author root
 * @description 针对String存取简单封装
 * @date 2020/12/19 15:50
 */
@Configuration
public class RedisString {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * @author lc
     * @date 2020/12/19
     * @description 插入
     */
    public void set(String k,String v){
       stringRedisTemplate.opsForValue().set(k, v);
    }

    /**
     * @author lc
     * @date 2020/12/19
     * @param time 超时时间,单位秒
     * @param k 键
     * @param v 值
     */
    public void set(String k,String v,long time){
        set(k, v, time, TimeUnit.SECONDS);
    }

    /**
     * @author lc
     * @date 2020/12/19
     * @description 超时时间,超时时间单位
     */
    public void set(String k,String v,long time,TimeUnit timeUnit){
        stringRedisTemplate.opsForValue().set(k, v, time, timeUnit);
    }

    /**
     * @author lc
     * @date 2020/12/19
     * @description 获取k的值
     */
    public String get(String k){
        return stringRedisTemplate.opsForValue().get(k);
    }

    /**
     * @author lc
     * @date 2021/2/27
     * @param k 释放锁
     */
    public void remove(String k) {
        stringRedisTemplate.delete(k);
    }


    /**
     * @param k 键
     * @param time 锁超时时间
     * @return true表示加锁成功,false加锁失败,单位(毫秒),1000毫秒 = 1秒
     */
    public boolean lock(String k,long time){
        String s = get(k);
        if (s == null) {
            set(k, k, time,TimeUnit.MILLISECONDS);
            return true;
        }
        return false;
    }

    /**
     * @author lc
     * @date 2021/2/27
     * @description 删除当前线程的锁
     */
    public void removeLock(){
        remove(LockAop.get());
    }
}
