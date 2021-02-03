package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author root
 * @description TODO
 * @date 2021/2/3 14:01
 */
@SpringBootApplication
@EnableDiscoveryClient
public class DubboGoodsApp {
    public static void main(String[] args) {
         SpringApplication.run(DubboGoodsApp.class, args);
    }
}
