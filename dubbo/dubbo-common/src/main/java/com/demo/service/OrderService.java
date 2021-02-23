package com.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.entity.Order;
import io.seata.rm.tcc.api.BusinessActionContext;

/**
 * @author lc
 * @date 2021-01-15
 * @description
 */
public interface OrderService extends IService<Order> {
    void createAt(BusinessActionContext actionContext,Integer userId, Integer goodsId);

}