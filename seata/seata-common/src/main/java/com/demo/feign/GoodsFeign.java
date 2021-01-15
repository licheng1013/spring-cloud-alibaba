package com.demo.feign;

import com.demo.util.JsonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author root
 * @description TODO
 * @date 2021/1/15 16:31
 */
@FeignClient(name = "seata-goods")
public interface GoodsFeign {

    /** 查询商品 **/
    @GetMapping("/goods/find")
    JsonResult<String> find();

    /** 删除商品 **/
    @PostMapping( "/goods/remove")
    JsonResult<String> remove();
}
