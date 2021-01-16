package com.demo.controller;

import com.demo.entity.User;
import com.demo.feign.UserFeign;
import com.demo.service.UserService;
import com.demo.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
    @PostMapping("update")
    public JsonResult<Boolean> update(User user) {
        return JsonResult.okData(user.updateById());
    }

    @Override
    @GetMapping("find")
    public JsonResult<User> find(Serializable userId) {
        return JsonResult.okData(userService.getById(userId));
    }
}
