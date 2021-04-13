package com.demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author root
 * @description TODO
 * @date 2021/4/13 15:40
 */
@Configuration
@Primary
@RefreshScope
@ConfigurationProperties(prefix = "config.db")
@Slf4j
@Setter
@Getter
public class DruidDataSourceConfig extends DruidDataSource{
    @Override
    public void setUrl(String url) {
        log.info("链接地址: {}",url);
        super.setUrl(url);
    }

    @Override
    public void setUsername(String username) {
        log.info("用户名: {}",username);
        super.setUsername(username);
    }

    @Override
    public void setPassword(String password) {
        log.info("密码: {}",password);
        super.setPassword(password);
    }
}
