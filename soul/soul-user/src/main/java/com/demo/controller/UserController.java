package com.demo.controller;

import org.dromara.soul.client.springcloud.annotation.SoulSpringCloudClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author root
 * @description TODO
 * @date 2021/3/11 15:24
 */
@RestController
@RequestMapping("user")
@SoulSpringCloudClient(path = "/user/**")
public class UserController {
    @Value("${server.port}")
    private String port;

    @GetMapping("test")
    public Object test(){
        return "Hello Soul Api port:"+port;
    }
}
