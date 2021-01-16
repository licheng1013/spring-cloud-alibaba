package com.demo.controller;

import com.demo.entity.Goods;
import com.demo.feign.GoodsFeign;
import com.demo.service.GoodsService;
import com.demo.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

/**
 * @author root
 * @description TODO
 * @date 2021/1/16 9:39
 */
@RequestMapping("goods")
@RestController
public class GoodsController implements GoodsFeign {

    @Autowired
    private GoodsService goodsService;

    @Override
    public JsonResult<Goods> find(Serializable goodsId) {
        return JsonResult.okData(goodsService.getById(goodsId));
    }

    @Override
    public JsonResult<Boolean> update(Goods goods) {
        return JsonResult.okData(goods.updateById());
    }

}
