package com.demo;

import com.demo.entity.User;
import com.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author root
 * @description TODO
 * @date 2021/2/26 14:32
 */
@SpringBootTest
public class UserTest {
    @Autowired
    private UserService userService;

    @Test
    public void test1(){
        User user1 = userService.getById(1);
        User user2 = userService.getById(1);
        user1.setMoney(user1.getMoney()+1000);
        user2.setMoney(user1.getMoney()-1000);
        System.out.println(user1.updateById());
        System.out.println(user2.updateById());
    }

}
