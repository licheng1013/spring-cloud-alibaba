package com.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.entity.User;

import java.io.Serializable;

/**
 * @author lc
 * @date 2021-01-15
 * @description
 */
public interface UserService extends IService<User> {
    String hello();
    boolean updateMoney(Serializable userId, Integer money);
}