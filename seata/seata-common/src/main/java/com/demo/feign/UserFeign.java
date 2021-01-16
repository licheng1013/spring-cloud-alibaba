package com.demo.feign;

import com.demo.entity.User;
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
@FeignClient(name = "seata-user")
public interface UserFeign {
    @PostMapping("/user/update/")
    JsonResult<String> update( User user);
    @GetMapping("/user/find")
    JsonResult<User> find(Serializable userId);
}
