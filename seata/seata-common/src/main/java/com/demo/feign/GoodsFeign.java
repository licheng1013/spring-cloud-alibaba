package com.demo.feign;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author root
 * @description TODO
 * @date 2021/1/15 16:31
 */
@FeignClient(name = "seata-goods")
public interface GoodsFeign {
}
