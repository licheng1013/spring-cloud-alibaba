package com.demo;

import com.demo.util.RedisString;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author root
 * @description TODO
 * @date 2020/12/19 14:44
 */
@SpringBootTest
public class RedisTests {

    @Autowired
    private RedisString redisString;

    @Test
    public void test1(){
        //RedisClusterConnection connection = factory.getClusterConnection();
        redisString.set("c", "Hello World hi c");
        System.out.println(redisString.get("a"));
    }
}
