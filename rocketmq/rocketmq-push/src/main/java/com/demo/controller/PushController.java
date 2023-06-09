package com.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.MessageConst;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
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
public class PushController {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    public void run(String... args)  {
        rocketMQTemplate.convertAndSend("test-topic-1", "Hello, World!");
        //send spring message
        rocketMQTemplate.send("test-topic-1", MessageBuilder.withPayload("Hello, World! I'm from spring message").build());
        //send messgae asynchronously
        rocketMQTemplate.asyncSend("test-topic-2", "Hello World Mq", new SendCallback() {
            @Override
            public void onSuccess(SendResult var1) {
                System.out.printf("async onSucess SendResult=%s %n", var1);
            }

            @Override
            public void onException(Throwable var1) {
                System.out.printf("async onException Throwable=%s %n", var1);
            }

        });
        //Send messages orderly
        rocketMQTemplate.syncSendOrderly("orderly_topic",MessageBuilder.withPayload("Hello, World").build(),"hashkey");
    }


    @GetMapping("push")
    public Object msg(String msg) {
        msg = msg == null ? "Hello World" : msg;
        MessageBuilder<String> header = MessageBuilder.withPayload(msg).setHeader(MessageConst.PROPERTY_TAGS, "testTag");
        Message<String> message = header.build();
        rocketMQTemplate.send("test",message);
        return msg;
    }

    @GetMapping("transaction")
    public Object transaction(String msg){
        msg = msg == null ? "Hello transaction" : msg;
        MessageBuilder<String> header = MessageBuilder.withPayload(msg);
        Message<String> message = header.build();
        rocketMQTemplate.sendMessageInTransaction("test-transaction",message,null);
        return msg;
    }

    @RocketMQTransactionListener
    static class TransactionListenerImpl implements RocketMQLocalTransactionListener {
        @Override
        public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
            // ... local transaction process, return bollback, commit or unknown
            log.info("中间状态: {}",new String((byte[]) msg.getPayload()));
            return RocketMQLocalTransactionState.UNKNOWN;
        }

        @Override
        public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
            // ... check transaction status and return bollback, commit or unknown
            log.info("提交状态: {}",new String((byte[]) msg.getPayload()));
            return RocketMQLocalTransactionState.COMMIT;
        }
    }


}
