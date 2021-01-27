package com.demo.util;

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
     * @description 增加超时单位秒
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
}
