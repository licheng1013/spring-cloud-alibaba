package com.demo.aop;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.demo.annotation.ApiTotal;
import com.demo.util.RedisString;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author root
 * @description TODO
 * @date 2021/4/23 15:03
 */
@Aspect
@Configuration
@Slf4j
public class ApiTotalAop {
    @Autowired
    private RedisString redisString;

    @Before(value = "@annotation(total)")
    public void around(ApiTotal total) {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String requestURI = request.getRequestURI() + DateUtil.date().toString(DatePattern.NORM_DATETIME_MINUTE_PATTERN);
        Long incr = redisString.incr(requestURI);
        if (incr >= 200000) {
            log.info("请求测试: 时间 {},数量 {}", requestURI,incr);
        }
    }
}
