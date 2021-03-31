package com.demo.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * @author root
 * @description TODO
 * @date 2021/3/31 14:22
 */
@Slf4j
@Service
@RocketMQMessageListener(topic = "test", consumerGroup = "my-group")
public class PullService implements RocketMQListener<String> {
    @Override
    public void onMessage(String message) {
        log.info("received message: {}", message);
    }
}
