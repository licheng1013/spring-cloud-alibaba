package com.demo.authentication;

import cn.hutool.extra.servlet.ServletUtil;
import com.demo.annotation.ParamLog;
import com.demo.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author lc
 * @date 2020/12/18
 * @description 拦截器
 */
@Configuration
@Slf4j
public class AuthenticationInterceptor implements HandlerInterceptor {

    /**
     * @author lc
     * @date 2020/12/18
     * @description
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) {
        //如果不是映射到方法直接通过,用于访问静态文件
        if (!(object instanceof HandlerMethod)) {
            return true;
        }

        //获取请求的方法
        HandlerMethod handlerMethod = (HandlerMethod) object;

        //如果请求接口上有 ParamLog 注解则打印请求参数
        if (handlerMethod.getMethodAnnotation(ParamLog.class) != null) {
            Map<String, String> map = ServletUtil.getParamMap(request);
            log.info("请求参数: {}", map);
        }
        //如果请求接口上有 passToken 主键则运行请求
        if (handlerMethod.getMethodAnnotation(PassToken.class) != null) {
            return true;
        }
        //解密token
        String token = request.getHeader("UserToken"); //获取请求头里面的token
        String userId = TokenUtil.getUserId(token);
        log.info("userId: {}", userId);
        return true;
    }

}
