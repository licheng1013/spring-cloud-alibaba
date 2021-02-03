package com.demo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.dao.UserDao;
import com.demo.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;

/**
 * @author lc
 * @date 2021-01-15
 * @description
 */
@DubboService
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {
    @Value("${server.port}")
    private String port;

    @Override
    public String hello() {
        return "Hello World "+port;
    }

    @Override
    public boolean updateMoney(Serializable userId, Integer money) {
        User byId = getById(userId);
        if (byId == null) {
            throw new RuntimeException("用户未找到");
        }
        byId.setMoney(byId.getMoney()-money);
        return byId.updateById();
    }
}