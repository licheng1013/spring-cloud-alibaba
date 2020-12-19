package com.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "spring.redis.cluster")
public class ClusterConfigurationProperties {

    /*
     * spring.redis.cluster.nodes[0] = 127.0.0.1:7379
     * spring.redis.cluster.nodes[1] = 127.0.0.1:7380
     * ...
     */
    List<String> nodes;

    /**
     * @author lc
     * @date 2020/12/19
     * @description 节点配置 {@code host:port}
     */
    public List<String> getNodes() {
        return nodes;
    }
    public void setNodes(List<String> nodes) {
        this.nodes = nodes;
    }
}