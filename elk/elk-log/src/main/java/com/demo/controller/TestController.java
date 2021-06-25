package com.demo.controller;

import com.demo.exception.JsonResult;
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
        return JsonResult.okMsg("test1");
    }

    @GetMapping("err")
    public Object err(){
        int i = 1/0;
        return JsonResult.okMsg("err");
    }
}
