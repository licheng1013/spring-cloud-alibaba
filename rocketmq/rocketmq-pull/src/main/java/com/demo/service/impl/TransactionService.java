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
@RocketMQMessageListener(topic = "test-transaction", consumerGroup = "my-transaction-group")
public class TransactionService implements RocketMQListener<String> {
    public static int i = 0;

    @Override
    public void onMessage(String message) {
        if (++i == 1){
            throw new RuntimeException("错误异常");
        }
        log.info("received message: {}", message);
    }
}
