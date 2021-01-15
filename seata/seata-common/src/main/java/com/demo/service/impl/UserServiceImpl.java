package com.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.dao.UserDao;
import com.demo.entity.User;
import com.demo.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author lc
 * @date 2021-01-15
 * @description
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

}