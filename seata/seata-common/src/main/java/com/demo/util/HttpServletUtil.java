package com.demo.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

/**
 * 必须在web环境使用,否则会出现空指针异常
 * @author root
 * @description TODO
 * @date 2021/4/9 15:41
 */
public final class HttpServletUtil {
    private HttpServletUtil(){}

    /**
     * @return 当前请求的路径
     */
    public static String getPath(){
        return getHttpServletRequest().getRequestURI();
    }

    /**
     * @return 获取SpringBoot上下文的HttpServletRequest
     */
    public static HttpServletRequest getHttpServletRequest(){
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }

    public static Map<String,Object> getMap(){
        return null;
    }
}
