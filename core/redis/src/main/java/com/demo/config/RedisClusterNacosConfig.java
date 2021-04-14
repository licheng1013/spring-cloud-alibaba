package com.demo.config;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
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
@ConditionalOnProperty(prefix = "config.redis",name = "nodes[0]")
@Setter
@Getter
@Slf4j
public class RedisClusterNacosConfig implements RedisConfig{
    /*
     * spring.redis.cluster.nodes[0] = 127.0.0.1:7379
     * spring.redis.cluster.nodes[1] = 127.0.0.1:7380
     * ...
     */
    private List<String> nodes;

    public void setNodes(List<String> nodes) {
        log.info("redis的nacos配置: {}",nodes);
        this.nodes = nodes;
    }
}
