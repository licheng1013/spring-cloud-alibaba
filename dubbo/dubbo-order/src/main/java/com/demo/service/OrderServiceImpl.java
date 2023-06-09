package com.demo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.annotation.Lock;
import com.demo.dao.OrderDao;
import com.demo.entity.Order;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class OrderServiceImpl extends ServiceImpl<OrderDao, Order> implements OrderService {
    @DubboReference(loadbalance = "roundrobin")
    private UserService userService;
    @DubboReference(loadbalance = "roundrobin")
    private GoodsService goodsService;

    /**
     * @author lc
     * @date 2021/2/23
     * @description 同一用户购买同一个物品时,在某段时间内只能执行一次,否则会出现线程不安全问题
     */
    @Override
    @GlobalTransactional
    @Lock(prefix = "createOrder:")
    public void createAt(Integer userId, Integer goodsId) {
        Integer money = goodsService.getMoney(goodsId);
        //查询用户
        boolean c = userService.updateMoney(null,userId, money);
        if (!c) {
            throw new RuntimeException("修改金额失败");
        }
        //查询商品
        boolean b = goodsService.updateTotal(null,goodsId, 1);
        if (!b) {
            throw new RuntimeException("修改商品失败");
        }
        Order order = new Order();
        order.setGoodsId(goodsId);
        order.setUserId(userId);
        order.setAmount(1);
        order.setDescription("事务");
        order.setMoney(money);
        order.insert();
//        int i  = 1/0;
    }


}