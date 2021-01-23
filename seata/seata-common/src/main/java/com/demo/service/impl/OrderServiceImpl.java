package com.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.dao.OrderDao;
import com.demo.entity.Goods;
import com.demo.entity.Order;
import com.demo.entity.User;
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
    public void create() {
        String userId = "1";
        String goodsId = "1";
        //查询商品
        Goods goods = goodsFeign.find(goodsId).getData();
        goods.setTotal(goods.getTotal() - 1);
        goodsFeign.update(goods);

        //查询用户
        User user = userFeign.find(userId).getData();
        user.setMoney(user.getMoney()-goods.getMoney());
        userFeign.update(user);

        Order order = new Order();
        order.setGoodsId(goods.getGoodsId());
        order.setUserId(user.getUserId());
        order.setAmount(1);
        order.setDescription("事务");
        order.setMoney(goods.getMoney());
        order.insert();

//        int i = 1/0;//异常测试
    }
}