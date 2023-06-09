package com.demo.controller;

import com.demo.feign.OrderFeign;
import com.demo.service.OrderService;
import com.demo.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author root
 * @description TODO
 * @date 2021/1/16 9:35
 */
@RequestMapping("order")
@RestController
public class OrderController implements OrderFeign {
    @Autowired
    private OrderService orderService;


    @Override
    @PostMapping("create")
    public JsonResult<String> create(Integer userId,Integer goodsId) {
        orderService.createOrder(userId, goodsId);
        return JsonResult.okMsg("创建订单成功");
    }

    @Override
    @PostMapping("redis/create")
    public JsonResult<String> redisCreate(Integer userId, Integer goodsId) {
        orderService.redisCreateOrder(userId, goodsId);
        return JsonResult.okMsg("创建订单成功");
    }


}
