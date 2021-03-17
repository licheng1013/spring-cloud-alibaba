package com.demo.aop;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.json.JSONUtil;
import com.demo.annotation.Cache;
import com.demo.util.AopUtil;
import com.demo.util.RedisString;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

/**
 * @author root
 * @description TODO
 * @date 2021/3/17 11:34
 */
@Aspect
@Configuration
@Slf4j
public class CacheAop {
    @Autowired
    private RedisString redisString;

    @Around(value = "@annotation(cache)")
    public Object around(ProceedingJoinPoint joinPoint, Cache cache) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        Map<String, String> map = ServletUtil.getParamMap(request);
        String requestURI = request.getRequestURI();
        StringBuilder sb = new StringBuilder(requestURI);
        map.forEach((k,v) ->{
            sb.append(k).append(":").append(v);
        });
        String key = sb.toString();
        String v = redisString.get(key);
        if (StrUtil.isBlank(v)) { //
            Cache invoke = AopUtil.invoke(joinPoint, Cache.class); //这里获取的对象应必须存在
            long random = RandomUtil.randomLong(invoke.minTime(), invoke.maxTime());
            Object proceed = joinPoint.proceed();
            redisString.set(key, JSONUtil.toJsonStr(proceed),random);
        }
        return v;
    }
}
