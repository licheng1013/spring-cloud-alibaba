package com.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @author root
 * @description TODO
 * @date 2021/4/13 13:50
 */

@Configuration
@Primary
@RefreshScope
@ConfigurationProperties(prefix = "config.db")
@Slf4j
public class DataSourcePropertiesConfig extends DataSourceProperties {

    @Override
    public void setType(Class<? extends DataSource> type) {
        super.setType(type);
    }

    @Override
    public void setUrl(String url) {
        super.setUrl(url);
    }

    @Override
    public void setUsername(String username) {
        super.setUsername(username);
    }

    @Override
    public void setPassword(String password) {
        log.info("密码: {}",password);
        super.setPassword(password);
    }
}
