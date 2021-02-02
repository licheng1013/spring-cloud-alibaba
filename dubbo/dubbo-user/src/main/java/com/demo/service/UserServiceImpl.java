package com.demo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.dao.UserDao;
import com.demo.entity.User;
import lombok.extern.slf4j.Slf4j;

/**
 * @author lc
 * @date 2021-01-15
 * @description
 */
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

}