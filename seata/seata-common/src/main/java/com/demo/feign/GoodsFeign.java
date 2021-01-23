package com.demo.feign;

import com.demo.entity.Goods;
import com.demo.util.JsonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;

/**
 * @author root
 * @description TODO
 * @date 2021/1/15 16:31
 */
@FeignClient(name = "seata-goods")
public interface GoodsFeign {

    /** 查询商品 **/
    @GetMapping( value = "/goods/find")
    JsonResult<Goods> find(@RequestParam("goodsId") Serializable goodsId);

    /** 修改商品 **/
    @PostMapping( "/goods/update")
    JsonResult<Boolean> update(@RequestBody Goods goods);
}
