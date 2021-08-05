package com.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author root
 * @description TODO
 * @date 2021/1/15 14:59
 */
@MapperScan("com.demo.mapper")
@SpringBootApplication
public class FluentApp {
    public static void main(String[] args) {
        SpringApplication.run(FluentApp.class, args);
    }
}
