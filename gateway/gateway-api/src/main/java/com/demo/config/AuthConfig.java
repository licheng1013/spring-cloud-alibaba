package com.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author lc
 * @since 2021/6/22
 */
@Configuration
@Slf4j
public class AuthConfig implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("请求参数: {}", exchange.getRequest().getQueryParams().toSingleValueMap());
        return chain.filter(exchange);
    }
}
