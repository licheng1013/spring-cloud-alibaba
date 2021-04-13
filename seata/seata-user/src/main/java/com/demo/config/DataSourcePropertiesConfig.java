package com.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author root
 * @description TODO
 * @date 2021/4/13 13:50
 */

@Configuration
@Primary
@RefreshScope
@ConfigurationProperties(prefix = "config")
@Slf4j
public class DataSourcePropertiesConfig {

}
