package com.demo.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;

/**
 * @author root
 * @description TODO
 * @date 2021/1/15 16:31
 */
@FeignClient(name = "seata-user")
public interface UserFeign {

    @PostMapping("/user/update/money")
    boolean updateMoney(@RequestParam("userId") Serializable userId, @RequestParam("money") Integer money);
}
