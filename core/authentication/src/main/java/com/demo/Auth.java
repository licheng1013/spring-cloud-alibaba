package com.demo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author root
 * @description TODO
 * @date 2021/4/17 11:56
 */
public interface Auth {
    void isAuth(HttpServletRequest request, HttpServletResponse response, Object object);
}
