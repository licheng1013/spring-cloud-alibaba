package com.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import java.util.UUID;

/**
 * @author root
 * @description TODO
 * @date 2021/1/9 15:23
 */
@Slf4j
@EnableBinding(Source.class)
public class PushServiceImpl {
    @Value("${server.port}")
    private String port;

    @Autowired
    private MessageChannel output;

    public String push(){
        String uuid = UUID.randomUUID().toString();
        log.info("消息制作: {} 端口: {}",uuid,port);
        output.send(MessageBuilder.withPayload(uuid).build());
        return uuid + port;
    }
}
