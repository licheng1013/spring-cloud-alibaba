package com.demo.config;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RestController
@RequestMapping("user")
public class TestNacoConfig {
    private  String name;
    public void setName(String name){
        log.info("刷新: {}",name);
        this.name= name;
    }
    @GetMapping("get/name")
    public Object get(){
        return this.name;
    }
}
