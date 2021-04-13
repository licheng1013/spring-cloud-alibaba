package com.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.ArrayList;
import java.util.List;

/**
 * @author root
 * @description 重写路由变为动态配置
 * @date 2021/3/13 17:45
 */
@Configuration
@Primary
@RefreshScope
@ConfigurationProperties(prefix = "config")
@Slf4j
public class GatewayPropertiesConfig extends GatewayProperties {
    private static List<RouteDefinition> list = new ArrayList<>();


    /**
     * @param list 动态网关配置 配置模板:
     */
    public void setList(ArrayList<RouteDefinition> list) {
        log.info("数组: {}",list);
        GatewayPropertiesConfig.list = list;
    }

    @Override
    public List<RouteDefinition> getRoutes() {
        return list;
    }

}
