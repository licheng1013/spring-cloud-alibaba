package com.demo.controller;


import com.demo.service.OrderService;

import javax.annotation.Resource;

/**
 * @author lc
 * @date 2021-01-15
 * @description
 */
@RestController
@RequestMapping("order")
public class OrderController {

    @Resource
    private OrderService orderService;

}