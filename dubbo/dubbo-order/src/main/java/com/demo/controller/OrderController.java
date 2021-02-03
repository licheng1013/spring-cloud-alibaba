package com.demo.controller;

import com.demo.service.OrderService;
import com.demo.service.UserService;
import com.demo.util.JsonResult;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    @DubboReference(loadbalance = "roundrobin")
    private UserService userService;

    @GetMapping("test")
    public Object test(){

        return userService.hello();
    }

    @Autowired(required = false)
    private OrderService orderService;


    @PostMapping("create")
    public JsonResult<String> create(Integer userId, Integer goodsId) {
        orderService.createAt(userId, goodsId);
        return JsonResult.okMsg("创建订单成功");
    }
}
