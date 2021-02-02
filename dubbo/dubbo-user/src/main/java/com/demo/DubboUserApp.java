package com.demo;

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
public class DubboUserApp {
    public static void main(String[] args) {
        SpringApplication.run(DubboUserApp.class, args);
    }
}
