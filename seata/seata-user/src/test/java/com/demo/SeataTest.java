package com.demo;

import com.demo.dao.UserDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author root
 * @description TODO
 * @date 2021/4/13 14:49
 */

@SpringBootTest
public class SeataTest {

    public static void main(String[] args) throws Exception {
    }

    @Autowired
    private UserDao userDao;

    @Test
    @Transactional
    public void test1() {
        boolean b = userDao.updateByIdSetMoney("1", 10);
        if (b) {
            System.out.println("修改成功");
        }else {
            System.out.println("修改失败");
        }
    }
}
