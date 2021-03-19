package com.demo.controller;

import com.demo.entity.Goods;
import com.demo.feign.GoodsFeign;
import com.demo.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

/**
 * @author root
 * @description TODO
 * @date 2021/1/16 9:39
 */
@RequestMapping("goods")
@RestController
@Slf4j
public class GoodsController implements GoodsFeign {

    @Autowired
    private GoodsService goodsService;


    @Override
    @PostMapping("update/total")
    public boolean updateTotal(Serializable goodsId, Integer num) {
        return goodsService.updateTotal(goodsId, num);
    }

    @Override
    @GetMapping("find/money")
    public Integer getMoney(Integer goodsId) {
//        System.out.println(goodsId);
        Goods byId = goodsService.getById(goodsId);
        if (byId == null) {
            throw new RuntimeException("商品未找到");
        }
        return byId.getMoney();
    }

    @Override
    @PostMapping("update/total/tcc")
    public boolean updateTotalTcc(Serializable goodsId, Integer num) {
        return goodsService.updateTotalTcc(null,goodsId, num);
    }

}
