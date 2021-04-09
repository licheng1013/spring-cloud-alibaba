package com.demo.util;

import cn.hutool.extra.servlet.ServletUtil;
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
     * 获取SpringBoot上下文的HttpServletRequest
     * @author lc
     * @date 2021/4/9
     */
    public static HttpServletRequest getHttpServletRequest(){
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }

    /**
     * 获取请求参数
     * @author lc
     * @date 2021/4/9
     */
    public static Map<String,String> getParamMap(){
        return ServletUtil.getParamMap(getHttpServletRequest());
    }


    /**
     * 获取请求类型GET,POST
     * @author lc
     * @date 2021/4/9
     */
    public static String getMethod(){
        return getHttpServletRequest().getMethod();
    }

    /**
     * 路径+参数等于唯一key
     * @author lc
     * @date 2021/4/9
     */
    public static String getKey(String str){
        return getPath()+str;
    }
}
