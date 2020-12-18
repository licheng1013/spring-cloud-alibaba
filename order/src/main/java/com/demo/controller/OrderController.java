package com.demo.controller;

import com.demo.feign.OrderFeign;
import com.demo.util.JsonResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author root
 * @description TODO
 * @date 2020/12/18 11:49
 */
@RequestMapping("order")
@RestController
public class OrderController implements OrderFeign {

    @Value("${server.port}")
    private String port;

    @Override
    @RequestMapping("test")
    public JsonResult<String> test() {
        return JsonResult.okMsg("Hello Order "+port);
    }

    @Override
    @RequestMapping("exception")
    public JsonResult<String> exception() {
        int i = 1 / 0;
        return JsonResult.okMsg("Hello Order Exception");
    }
}
