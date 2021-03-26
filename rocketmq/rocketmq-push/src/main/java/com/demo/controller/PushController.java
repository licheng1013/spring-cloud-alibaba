package com.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageConst;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author root
 * @description TODO
 * @date 2021/3/26 14:59
 */
@RestController
@RequestMapping("rocket")
@Slf4j
//@EnableBinding({Source.class, Sink.class})
public class PushController {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @GetMapping("msg")
    public Object msg(String msg) {
        msg = msg == null ? "Hello World" : msg;
        MessageBuilder<String> header = MessageBuilder.withPayload("Hello World").setHeader(MessageConst.PROPERTY_TAGS, "testTag");
        Message<String> message = header.build();
        rocketMQTemplate.send(message);
        return msg;
    }


}
