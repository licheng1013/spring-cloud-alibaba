package com.demo.controller;

import com.demo.feign.OrderFeign;
import com.demo.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author root
 * @description TODO
 * @date 2020/12/18 11:39
 */
@RestController
@RequestMapping("user")
@Slf4j
public class UserController {
    @Value("${server.port}")
    private String port;

    @Autowired
    private OrderFeign orderFeign;

    @GetMapping("test")
    public JsonResult<String> test() {
        return JsonResult.okMsg("Hello World " + port);
    }

    @GetMapping("feign/test")
    public JsonResult<String> feignTest(){
        log.info("feign调用: {}",port);
        return orderFeign.test();
    }

    @GetMapping("exception")
    public JsonResult<String> exception(){
        int i = 1/0;
        return JsonResult.okMsg("Hello exception");
    }

    @GetMapping("feign/exception")
    public JsonResult<String> feignException(){
        return orderFeign.exception();
    }

}
