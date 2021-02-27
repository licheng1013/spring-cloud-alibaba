package com.demo.controller;

import com.demo.entity.User;
import com.demo.service.UserService;
import com.demo.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author root
 * @description TODO
 * @date 2021/2/27 14:20
 */
@RequestMapping("user")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("test1")
    public JsonResult<Boolean> test1(Integer money) throws InterruptedException {
        User user = userService.getById(1);
//        user.setMoney(userService.getMoney(user.getUserId())-money);

        if (user.getMoney() < money) {
            throw new RuntimeException("金额不足");
        }
        Thread.sleep(2000);
        user.setMoney(user.getMoney()-money);
        return JsonResult.okData(user.updateById());
    }
    @GetMapping("test2")
    public JsonResult<Boolean> test2(Integer money) throws InterruptedException {
        User user = userService.getById(1);
//        user.setMoney(userService.getMoney(user.getUserId())-money);
        if (user.getMoney() < money) {
            throw new RuntimeException("金额不足");
        }
        Thread.sleep(2000);
        user.setMoney(user.getMoney()-money);
        return JsonResult.okData(user.updateById());
    }

}
