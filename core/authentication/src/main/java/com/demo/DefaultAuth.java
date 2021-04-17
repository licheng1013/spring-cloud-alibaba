package com.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author root
 * @description TODO
 * @date 2021/4/17 12:30
 */
@Slf4j
@Service
public class DefaultAuth implements Auth {

    @Override
    public void isAuth(HttpServletRequest request, HttpServletResponse response, Object object) {
        //解密token
        try {
            String token = request.getHeader("Authentication"); //获取请求头里面的token
            String userId = TokenUtil.getUserId(token);
            log.info("userId: {}", userId);
        }catch (Exception e){
            throw new RuntimeException("请登入");
        }
    }
}
