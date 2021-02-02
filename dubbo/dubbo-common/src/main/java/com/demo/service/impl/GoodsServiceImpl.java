package com.demo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.dao.GoodsDao;
import com.demo.entity.Goods;
import com.demo.service.GoodsService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author lc
 * @date 2021-01-15
 * @description
 */
@Slf4j
public class GoodsServiceImpl extends ServiceImpl<GoodsDao, Goods> implements GoodsService {


}