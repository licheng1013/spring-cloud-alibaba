package com.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.dao.OrderDao;
import com.demo.entity.Order;
import com.demo.feign.GoodsFeign;
import com.demo.feign.UserFeign;
import com.demo.service.OrderService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lc
 * @date 2021-01-15
 * @description
 */
@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderDao, Order> implements OrderService {
    @Autowired
    private UserFeign userFeign;
    @Autowired
    private GoodsFeign goodsFeign;

    @Override
    @GlobalTransactional
    public void create(Integer userId, Integer goodsId) {
        Integer money = goodsFeign.getMoney(goodsId).getData();
        //查询商品
        goodsFeign.updateTotal(goodsId, 1);
        //查询用户
        userFeign.updateMoney(userId,money );

        Order order = new Order();
        order.setGoodsId(goodsId);
        order.setUserId(userId);
        order.setAmount(1);
        order.setDescription("事务");
        order.setMoney(money);
        order.insert();
//        int i = 1/0;//异常测试
    }
}