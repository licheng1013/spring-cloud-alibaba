package com.demo.aop;

import com.demo.util.HttpServletUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

/**
 * @author lc
 * @since 2021/6/25
 */
@Slf4j
@Aspect
@Configuration
public class ElkAop {
    @Around(value="execution(* com.demo.controller..*.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("请求参数: {}", HttpServletUtil.getParamMap());
        Object proceed = joinPoint.proceed();
        log.info("请求结果: {}",proceed);
        return proceed;
    }
}
