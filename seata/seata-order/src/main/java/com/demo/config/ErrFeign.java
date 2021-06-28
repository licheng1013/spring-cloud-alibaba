package com.demo.config;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Configuration;

/**
 * @author lc
 * @since 2021/6/28
 */
@Configuration
public class ErrFeign implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        System.out.println(response.body().toString());
        return new RuntimeException("feign异常!");
    }
}
