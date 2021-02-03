package com.demo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.dao.OrderDao;
import com.demo.entity.Order;
import io.seata.spring.annotation.GlobalTransactional;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

/**
 * @author lc
 * @date 2021-01-15
 * @description
 */
@DubboService
@Service
public class OrderServiceImpl extends ServiceImpl<OrderDao, Order> implements OrderService {
    @DubboReference(loadbalance = "roundrobin")
    private UserService userService;
    @DubboReference(loadbalance = "roundrobin")
    private GoodsService goodsService;

    @Override
    @GlobalTransactional
    public void createAt(Integer userId, Integer goodsId) {
        Integer money = goodsService.getMoney(goodsId);
        //查询商品
        boolean b = goodsService.updateTotal(goodsId, 1);
        if (!b) {
            throw new RuntimeException("修改商品失败");
        }
        //查询用户
        boolean c = userService.updateMoney(userId, money);
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
    }
}