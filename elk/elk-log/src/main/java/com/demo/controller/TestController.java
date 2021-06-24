package com.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lc
 * @since 2021/6/24
 */
@RestController
@RequestMapping("test")
@Slf4j
public class TestController {

    @GetMapping("1")
    public Object test1(){
        log.info("请求成功!");
        return "test1";
    }
}
