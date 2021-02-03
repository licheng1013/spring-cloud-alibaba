package com.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.entity.Goods;

import java.io.Serializable;

/**
 * @author lc
 * @date 2021-01-15
 * @description
 */
public interface GoodsService extends IService<Goods> {

    boolean updateTotal(Serializable goodsId, Integer num);
}