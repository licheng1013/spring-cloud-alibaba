package com.demo;

import org.springframework.boot.SpringApplication;

/**
 * @author root
 * @description 被其他模块依赖使用时需要注释掉 @SpringBootApplication 注解,否则无法进行测试 并且需要配置其他模块的 application.yml
 * @date 2020/12/19 14:20
 */

//@SpringBootApplication
public class RedisApp {
    public static void main(String[] args) {
        SpringApplication.run(RedisApp.class, args);
    }
}
