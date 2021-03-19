package com.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.entity.Order;

/**
 * @author lc
 * @date 2021-01-15
 * @description
 */
public interface OrderService extends IService<Order> {
    void createOrder(Integer userId, Integer goodsId);

}