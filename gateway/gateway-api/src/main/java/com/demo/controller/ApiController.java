package com.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author root
 * @description TODO
 * @date 2021/3/16 9:47
 */
@RestController
@RequestMapping("api")
public class ApiController {

    @GetMapping("test")
    public Object test(){
//        int i = 1/0;
        return "Test 1";
    }
}
