package com.demo.controller;

import com.demo.service.UserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author root
 * @description TODO
 * @date 2021/2/2 14:18
 */
@RestController
@RequestMapping("order")
public class OrderController {

    @DubboReference
    private UserService userService;

    @GetMapping("test")
    public Object test(){
        return userService.hello();
    }
}
