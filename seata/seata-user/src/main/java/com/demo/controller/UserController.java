package com.demo.controller;

import com.demo.annotation.Cache;
import com.demo.entity.User;
import com.demo.feign.UserFeign;
import com.demo.service.UserService;
import com.demo.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

/**
 * @author root
 * @description TODO
 * @date 2021/1/15 17:44
 */
@RequestMapping("user")
@RestController
public class UserController implements UserFeign {
    @Autowired
    private UserService userService;

    @Override
    @PostMapping("update/money")
    public boolean updateMoney(Serializable userId, Integer money) {
        return userService.updateMoney(userId, money);
    }

    @Override
    @PostMapping("update/money/tcc")
    public boolean updateMoneyTcc(Serializable userId, Integer money) {
        return userService.updateMoneyTcc(null,userId, money);
    }

    @GetMapping("getInfo/{id}")
    @Cache
    public JsonResult<User> getInfo(@PathVariable String id)  {
        return JsonResult.okData(userService.getById(id));
    }
}
