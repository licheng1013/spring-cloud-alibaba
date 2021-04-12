package com.demo;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author root
 * @description TODO
 * @date 2021/4/12 13:45
 */
@Configuration
@EnableConfigurationProperties(AutoConfig.class)
@ConfigurationProperties("spring.auth")
@Setter
@Getter
@Slf4j
public class AutoConfig {
    /** 是否开启认证,默认开启: true **/
    private boolean enable = true;

}
