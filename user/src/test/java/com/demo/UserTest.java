package com.demo;

import com.demo.entity.User;
import com.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author root
 * @description TODO
 * @date 2020/12/31 15:03
 */
@SpringBootTest
@Slf4j
public class UserTest {
    @Autowired
    private UserService userService;
    @Test
    public void test1(){
        User a = userService.getById(5);
        User b = userService.getById(5);

        a.setMoney(a.getMoney()-500);
        b.setMoney(b.getMoney()+200);

        boolean aa = a.updateById();
        boolean bb = b.updateById();
        System.out.println(aa);
        System.out.println(bb);
    }
}
