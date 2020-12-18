package com.demo.feign;

import com.demo.util.JsonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author root
 * @description TODO
 * @date 2020/12/18 11:43
 */
@FeignClient(name = "alibaba-order")
public interface OrderFeign {
    @RequestMapping(value = "/order/test",method = RequestMethod.GET)
    JsonResult<String> test();

    @RequestMapping(value = "/order/exception",method = RequestMethod.GET)
    JsonResult<String> exception();
}
