package com.demo;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author root
 * @description TODO
 * @date 2021/2/2 11:29
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableDubbo
public class DubboUserApp {
    public static void main(String[] args) {
        SpringApplication.run(DubboUserApp.class, args);
    }
}
