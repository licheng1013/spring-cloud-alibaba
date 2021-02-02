package com.demo;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author root
 * @description TODO
 * @date 2021/2/2 11:28
 */
@SpringBootApplication
@EnableDubbo
public class DubboOrderApp {
    public static void main(String[] args) {
        SpringApplication.run(DubboOrderApp.class, args);
    }
}
