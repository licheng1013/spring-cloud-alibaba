package com.demo;

import cn.hutool.extra.servlet.ServletUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author lc
 * @date 2020/12/18
 * @description 拦截器
 */
@Slf4j
@Configuration
@ConditionalOnProperty(prefix = "spring.auth",name = "enable",havingValue = "true",matchIfMissing = true)
public class AuthenticationInterceptor implements HandlerInterceptor {


    @Autowired
    private List<Auth> auths;

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
        // 权限判断
        this.auth(request, response, object);
        return true;//默认
    }

    private void auth(HttpServletRequest request, HttpServletResponse response, Object object){
        for (Auth auth : this.auths) {
            auth.isAuth(request, response, object);
        }
    }

}
