package com.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.entity.User;
import com.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;

/**
 * @author root
 * @description TODO
 * @date 2021/1/9 15:18
 */
@EnableBinding(Sink.class)
@Slf4j
public class PullController {
    @Value("${server.port}")
    private String port;
    @Autowired
    private UserService userService;

    /**
     * @author lc
     * @date 2020/12/1
     * @description 接受消息
     */
    @StreamListener(Sink.INPUT)
    public void input(Message<String> msg) {
       log.info("接受的消息的: {} 端口: {}",msg.getPayload(),port );
        String tel = msg.getPayload();
        User user = userService.getOne(new QueryWrapper<User>().eq("tel", tel));
        if (user == null && StrUtil.isNotBlank(msg.getPayload())) {
            user = new User()
                    .setTel(tel)
                    .setNickName("超强哈市奇")
                    .setUserName(tel + "大熊")
                    .setAge(20)
                    .setMoney(100);
            user.insert();
        }
    }
}
