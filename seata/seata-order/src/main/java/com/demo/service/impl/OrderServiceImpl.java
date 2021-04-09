package com.demo.service.impl;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.dao.OrderDao;
import com.demo.entity.Order;
import com.demo.feign.GoodsFeign;
import com.demo.feign.UserFeign;
import com.demo.service.OrderService;
import com.demo.util.HttpServletUtil;
import com.demo.util.RedisString;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lc
 * @date 2021-01-15
 * @description
 */
@Service("orderService")
@Slf4j
public class OrderServiceImpl extends ServiceImpl<OrderDao, Order> implements OrderService {
    @Autowired
    private UserFeign userFeign;
    @Autowired
    private GoodsFeign goodsFeign;

    @Override
    @GlobalTransactional
    public void createOrder(Integer userId, Integer goodsId) {
        Integer money = goodsFeign.getMoney(goodsId);
        //查询商品
        boolean b = goodsFeign.updateTotalTcc(goodsId, 1);
        if (!b) {
            throw new RuntimeException("修改商品失败");
        }
        //查询用户
        boolean c = userFeign.updateMoneyTcc(userId, money);
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

    @Autowired
    private RedisString redisString;

    @Override
    public void redisCreateOrder(Integer userId, Integer goodsId) {

        //获取路径唯一key
        String key = HttpServletUtil.getKey(goodsId.toString());
        boolean b = redisString.lock(key, 3000); //加锁
        if (b) {
            String k = key+"num";
            String num = redisString.get(k);
            if (StrUtil.isBlank(num)) {
                //获取库存放入redis
                num = goodsFeign.getTotal(goodsId).toString();
                redisString.set(k, num);
            }
            int i = Integer.parseInt(num) ;
            log.info("计算结果: {}",i);
            i -= 1;

            ThreadUtil.execute(()->{
                //异步通知修改库存
            });

            redisString.set(k, i+"");
            redisString.removeLock(); // 释放锁
        }
    }
}