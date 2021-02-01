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
    public void createAt(Integer userId, Integer goodsId) {
        Integer money = goodsFeign.getMoney(goodsId);
        //查询商品
        boolean b = goodsFeign.updateTotal(goodsId, 1);
        if (!b) {
            throw new RuntimeException("修改商品失败");
        }
        //查询用户
        boolean c = userFeign.updateMoney(userId, money);
        if (!c) {
            throw new RuntimeException("修改金额失败");
        }
        Order order = new Order();
        order.setGoodsId(goodsId);
        order.setUserId(userId);
        order.setAmount(1);
        order.setDescription("事务");
        order.setMoney(money);
        order.insert();
//        int i = 1/0;//异常测试
    }

    @Override
    @GlobalTransactional
    public void createTcc(Integer userId, Integer goodsId) {
        Integer money = goodsFeign.getMoney(goodsId);
        //查询商品
        boolean b = goodsFeign.updateTotal(goodsId, 1);
        if (!b) {
            throw new RuntimeException("修改商品失败");
        }
        //查询用户
        boolean c = userFeign.updateMoney(userId, money);
        if (!c) {
            throw new RuntimeException("修改金额失败");
        }
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