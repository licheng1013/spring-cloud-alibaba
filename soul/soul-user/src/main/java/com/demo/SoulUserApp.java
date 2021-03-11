package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author root
 * @description TODO
 * @date 2021/3/11 15:21
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SoulUserApp {
    public static void main(String[] args) {
        SpringApplication.run(SoulUserApp.class,args);
    }
}
