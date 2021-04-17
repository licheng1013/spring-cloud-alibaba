package com.demo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 认证拦截的顶级接口
 * @author root
 * @date 2021/4/17 11:56
 */
public interface Auth {
    /**
     * 认证拦截判断器
     * @author lc
     * @date 2021/4/17
     */
    void isAuth(HttpServletRequest request, HttpServletResponse response, Object object);
}
