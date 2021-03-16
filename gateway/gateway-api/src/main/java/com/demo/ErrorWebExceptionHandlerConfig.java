package com.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author root
 * @description TODO
 * @date 2021/3/16 9:57
 */
@Configuration
public class ErrorWebExceptionHandlerConfig implements ErrorWebExceptionHandler {
    /**
     * @param exchange 上下文
     * @param ex 异常信息
     * @return 处理结果
     */
    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        ex.printStackTrace();
        ServerHttpResponse response = exchange.getResponse();
        return response.writeWith(Mono.fromSupplier(() -> {
            DataBufferFactory dataBufferFactory = response.bufferFactory();
            try {
                byte[] value = new ObjectMapper().writeValueAsBytes(JsonResult.fail(ex.getMessage()));
                return dataBufferFactory.wrap(value);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return dataBufferFactory.wrap(ex.getMessage().getBytes());
        }));
    }


}
