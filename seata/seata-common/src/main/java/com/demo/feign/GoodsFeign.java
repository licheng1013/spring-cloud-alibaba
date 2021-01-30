package com.demo.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;

/**
 * @author root
 * @description TODO
 * @date 2021/1/15 16:31
 */
@FeignClient(name = "seata-goods")
public interface GoodsFeign {



    /** 修改商品数量 **/
    @PostMapping("/goods/update/total")
    boolean updateTotal(@RequestParam("goodsId") Serializable goodsId,@RequestParam("num") Integer num);

    @GetMapping(value = "/goods/find/money")
    Integer getMoney( @RequestParam("goodsId")Integer goodsId);

    /** 修改商品数量 **/
    @PostMapping("/goods/update/total/tcc")
    boolean updateTotalTcc(@RequestParam("goodsId") Serializable goodsId,@RequestParam("num") Integer num);
}
