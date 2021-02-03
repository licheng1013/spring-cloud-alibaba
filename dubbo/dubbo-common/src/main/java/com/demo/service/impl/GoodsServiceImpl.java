package com.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.dao.GoodsDao;
import com.demo.entity.Goods;
import com.demo.service.GoodsService;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * @author lc
 * @date 2021-01-15
 * @description
 */
@Slf4j
public class GoodsServiceImpl extends ServiceImpl<GoodsDao, Goods> implements GoodsService {

    @Override
    public boolean updateTotal(Serializable goodsId, Integer num) {
        Goods byId = getById(goodsId);
        if (byId == null)
            throw new RuntimeException("商品找不到");
        byId.setTotal(byId.getTotal() - num);
        return byId.updateById();
    }
}