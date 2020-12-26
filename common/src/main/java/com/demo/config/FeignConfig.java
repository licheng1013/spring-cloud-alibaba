package com.demo.config;

import com.demo.util.Assert;
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
        Assert.isNull(attributes, "请勿在service层调用");
        HttpServletRequest request = attributes.getRequest();
        //添加token
        requestTemplate.header("UserToken", request.getHeader("UserToken"));
    }
}