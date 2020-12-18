package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author root
 * @description TODO
 * @date 2020/12/18 11:09
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class UserApp {


    public static void main(String[] args) {
        SpringApplication.run(UserApp.class, args);
    }
}
