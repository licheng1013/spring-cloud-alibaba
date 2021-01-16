package com.demo.feign;

import com.demo.entity.Goods;
import com.demo.util.JsonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.Serializable;

/**
 * @author root
 * @description TODO
 * @date 2021/1/15 16:31
 */
@FeignClient(name = "seata-goods")
public interface GoodsFeign {

    /** 查询商品 **/
    @GetMapping("/goods/find")
    JsonResult<Goods> find(Serializable goodsId);

    /** 删除商品 **/
    @PostMapping( "/goods/remove")
    JsonResult<Boolean> update(Goods goods);
}
