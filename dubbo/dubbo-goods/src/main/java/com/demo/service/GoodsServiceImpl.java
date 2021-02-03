package com.demo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.dao.GoodsDao;
import com.demo.entity.Goods;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

import java.io.Serializable;

/**
 * @author lc
 * @date 2021-01-15
 * @description
 */
@Slf4j
@DubboService
public class GoodsServiceImpl extends ServiceImpl<GoodsDao, Goods> implements GoodsService {

    @Override
    public boolean updateTotal(Serializable goodsId, Integer num) {
        Goods byId = getById(goodsId);
        if (byId == null)
            throw new RuntimeException("商品找不到");
        byId.setTotal(byId.getTotal() - num);
        return byId.updateById();
    }

    @Override
    public Integer getMoney(Integer goodsId) {
        System.out.println(goodsId);
        Goods byId = getById(goodsId);
        if (byId == null) {
            throw new RuntimeException("商品未找到");
        }
        return byId.getMoney();
    }
}