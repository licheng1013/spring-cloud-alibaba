package com.demo.controller;

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


    @GetMapping("test")
    public Object test(){
        return null;
    }
}
