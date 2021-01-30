package com.demo.controller;

import com.demo.feign.UserFeign;
import com.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
