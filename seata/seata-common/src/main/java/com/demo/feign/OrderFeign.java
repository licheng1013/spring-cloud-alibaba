package com.demo.feign;

import com.demo.util.JsonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author root
 * @description TODO
 * @date 2021/1/15 16:31
 */
@FeignClient(name = "seata-order")
public interface OrderFeign {

    @PostMapping(value = "/order/create")
    JsonResult<String> create(Integer userId,Integer goodsId);


}
