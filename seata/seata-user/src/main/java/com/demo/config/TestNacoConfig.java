package com.demo.config;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * @author root
 * @description TODO
 * @date 2021/4/13 16:43
 */

@Configuration
@RefreshScope
@ConfigurationProperties(prefix = "config.test")
@Getter
@Slf4j
public class TestNacoConfig {
    private  String name;
    public void setName(String name){
        log.info("刷新: {}",name);
        this.name= name;
    }
}
