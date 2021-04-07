package com.demo;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.demo.entity.User;
import com.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

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
    public void test1() {
        User a = userService.getById(5);
        User b = userService.getById(5);
        a.setMoney(a.getMoney() - 500);
        b.setMoney(b.getMoney() + 200);

        boolean aa = a.updateById();
        boolean bb = b.updateById();
        System.out.println(aa);
        System.out.println(bb);
    }

    @Test
    public void test2() throws FileNotFoundException {
        File file = new File("C:\\Users\\root\\Desktop\\全部号码_100000条.txt");
        String read = IoUtil.read(new BufferedReader(new FileReader(file)));
        String[] split = read.split("\n");
        for (int i = 0; i < split.length; i++) {
            if (i == 500) {
                break;
            }
            String body = HttpUtil.get("http://localhost:9100/push/test?tel=" + split[i]);
            log.info("请求信息: {}", body);
        }

    }

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<User> users = new ArrayList<>();
        for (int i = 0; i < 2000000; i++) {
            String tel = RandomUtil.randomNumbers(11);
            User user = new User();
            user.setTel(tel);
            user.setUserName(tel + "大熊");
            user.setNickName("超强哈市奇");
            user.setCreateTime(new Date());
            users.add(user);
            log.info("列表大小: {}",users.size());
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("RECORDS", users);
        String str = JSONUtil.toJsonStr(map);
        FileOutputStream stream = new FileOutputStream("E:\\sql.json");
        IoUtil.write(stream, true, str.getBytes());
    }
}
