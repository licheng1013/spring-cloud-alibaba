package com.demo.service;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.binder.PollableMessageSource;
import org.xnio.channels.MessageChannel;

/**
 * @author root
 * @description TODO
 * @date 2021/3/26 15:00
 */
public interface MsgService {

    @Input
    PollableMessageSource source();

    @Output
    MessageChannel dest();
}
