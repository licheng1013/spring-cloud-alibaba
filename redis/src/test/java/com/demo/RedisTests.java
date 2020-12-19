package com.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author root
 * @description TODO
 * @date 2020/12/19 14:44
 */
@SpringBootTest
public class RedisTests {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    public void test1(){
        //RedisClusterConnection connection = factory.getClusterConnection();
        redisTemplate.opsForValue().set("c", "Hello World hi c");
        System.out.println(redisTemplate.opsForValue().get("a"));
    }
}
