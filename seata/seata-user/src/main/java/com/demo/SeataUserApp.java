package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author root
 * @description TODO
 * @date 2021/1/15 14:59
 */
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class SeataUserApp {
    public static void main(String[] args) {
        SpringApplication.run(SeataUserApp.class, args);
    }
}
