package com.demo.controller;

import com.demo.entity.Goods;
import com.demo.feign.GoodsFeign;
import com.demo.service.GoodsService;
import com.demo.util.JsonResult;
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
    @GetMapping("find")
    public JsonResult<Goods> find( Serializable goodsId) {
        return JsonResult.okData(goodsService.getById(goodsId));
    }

    @Override
    @PostMapping("update")
    public JsonResult<Boolean> update(Goods goods) {
        return JsonResult.okData(goods.updateById());
    }

    @Override
    @PostMapping("update/total")
    public JsonResult<Boolean> updateTotal(Serializable goodsId, Integer num) {
        return JsonResult.okData(goodsService.updateTotal(goodsId, num));
    }

    @Override
    @GetMapping("find/money")
    public JsonResult<Integer> getMoney(Integer goodsId) {
        Goods byId = goodsService.getById(goodsId);
        if (byId == null) {
            throw new RuntimeException("商品未找到");
        }
        return JsonResult.okData(byId.getMoney());
    }

}
