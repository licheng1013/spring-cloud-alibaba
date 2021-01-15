package com.demo.controller;


import com.demo.service.UserService;

import javax.annotation.Resource;

/**
 * @author lc
 * @date 2021-01-15
 * @description
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    private UserService userService;

}