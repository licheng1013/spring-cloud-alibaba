package com.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.dao.GoodsDao;
import com.demo.entity.Goods;
import com.demo.service.GoodsService;
import org.springframework.stereotype.Service;

/**
 * @author lc
 * @date 2021-01-15
 * @description
 */
@Service("goodsService")
public class GoodsServiceImpl extends ServiceImpl<GoodsDao, Goods> implements GoodsService {

}