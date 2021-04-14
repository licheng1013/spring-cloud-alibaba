package com.demo.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author root
 * @description TODO
 * @date 2021/4/14 12:47
 */
@Component
@ConfigurationProperties(prefix = "config.redis")
@Setter
@Getter
public class RedisConfig {
    /*
     * spring.redis.cluster.nodes[0] = 127.0.0.1:7379
     * spring.redis.cluster.nodes[1] = 127.0.0.1:7380
     * ...
     */
    List<String> nodes;
}
