package com.demo.controller;

import cn.hutool.core.text.UnicodeUtil;
import cn.hutool.core.util.StrUtil;
import com.demo.annotation.Cache;
import com.demo.dao.UserDao;
import com.demo.entity.User;
import com.demo.feign.UserFeign;
import com.demo.service.UserService;
import com.demo.util.JsonResult;
import com.demo.util.RedisString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

/**
 * @author root
 * @description TODO
 * @date 2021/1/15 17:44
 */
@RequestMapping("user")
@RestController
@Slf4j
public class UserController implements UserFeign {
    @Autowired
    private UserService userService;

    @Override
    @PostMapping("update/money")
    public boolean updateMoney(Serializable userId, Integer money) {
        return userService.updateMoney(userId, money);
    }

    @Override
    @PostMapping("update/money/tcc")
    public boolean updateMoneyTcc(Serializable userId, Integer money) {
        return userService.updateMoneyTcc(null,userId, money);
    }

    @GetMapping("getInfo/{id}")
    @Cache
    public JsonResult<User> getInfo(@PathVariable String id)  {
        return JsonResult.okData(userService.getById(id));
    }
    @Autowired
    private RedisString redisString;

    @GetMapping("redis")
    public JsonResult<Integer> redis(){

        boolean b = redisString.lock("test", 1000); //加锁
        if (b) {
            String redis = redisString.get("redis");
            if (StrUtil.isBlank(redis)) {
                redis = "100";
                redisString.set("redis", redis);
            }
            int i = Integer.parseInt(redis) ;
            log.info("计算结果: {}",i);
            if (i <= 0){
                return JsonResult.fail(-1);
            }
            i -= 1;
            redisString.set("redis", i+"");

            redisString.removeLock(); // 释放锁
        }

        return JsonResult.okData(1);
    }

    @GetMapping("sleep")
    public JsonResult<String> sleep() throws InterruptedException {
        Thread.sleep(10000);
        return JsonResult.okMsg("Hello World");
    }

    @GetMapping("json")
    public JsonResult<String> json(String json){
        //解析后的json
        String string = UnicodeUtil.toString(json);
        return JsonResult.okData("解码后的:"+string);
    }

    @Value("${server.port}")
    public String port;

    @GetMapping("port")
    public JsonResult<String> port(){
        return JsonResult.okMsg(port);
    }


    @Autowired
    private UserDao userDao;

    /**
     * 自定义注解update语句,需要修改
     * @author lc
     * @date 2021/4/26
     */
    @GetMapping("test/update/money")
    @Transactional
    public JsonResult<Boolean> updateMoney(String userId,Integer money){

        boolean b = userDao.updateByIdSetMoney("1", 10);
        if (userId == null) {
            throw new RuntimeException("异常");
        }
        return JsonResult.okData(b);
    }
}
