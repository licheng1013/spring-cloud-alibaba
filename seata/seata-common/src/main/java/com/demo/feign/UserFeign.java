package com.demo.feign;

import com.demo.entity.User;
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
@FeignClient(name = "seata-user")
public interface UserFeign {
    @PostMapping("/user/update/")
    JsonResult<Boolean> update(@RequestBody User user);

    @GetMapping("/user/find")
    JsonResult<User> find(@RequestParam("userId")Serializable userId);

    @PostMapping("/user/update/money")
    JsonResult<Boolean> updateMoney(@RequestParam("userId") Serializable userId, @RequestParam("money") Integer money);
}
