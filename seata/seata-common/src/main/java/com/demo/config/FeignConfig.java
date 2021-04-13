package com.demo.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lc
 * @date 2020/12/19
 * @description feign的全局配置
 */
@Configuration
public class FeignConfig implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            throw new RuntimeException("当前环境不是web环境,请通过接口访问触发业务");
        }
        HttpServletRequest request = attributes.getRequest();
        //添加token
        requestTemplate.header("UserToken", request.getHeader("UserToken"));
    }
}