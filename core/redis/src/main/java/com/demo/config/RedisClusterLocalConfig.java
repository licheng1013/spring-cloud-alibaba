package com.demo.config;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConditionalOnMissingBean(RedisClusterNacosConfig.class)
@ConfigurationProperties(prefix = "spring.redis.cluster")
@Setter
@Getter
@Slf4j
public class RedisClusterLocalConfig implements RedisConfig {

    /*
     * spring.redis.cluster.nodes[0] = 127.0.0.1:7379
     * spring.redis.cluster.nodes[1] = 127.0.0.1:7380
     * ...
     */
    List<String> nodes;

    public void setNodes(List<String> nodes) {
        log.info("redis的本地配置: {}",nodes);
        this.nodes = nodes;
    }
}