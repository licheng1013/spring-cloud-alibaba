package com.demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @author root
 * @description TODO
 * @date 2021/4/13 15:40
 */
@Configuration
@Primary
@RefreshScope
//@ConfigurationProperties(prefix = "config.db")
@Slf4j
@Setter
@Getter
public class DruidDataSourceConfig  {

    private static String url ;//= "jdbc:mysql://192.168.1.5:3306/t_user?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai";
    private static String username ;//= "root";
    private static String password ;//= "root";

//    @Override
//    public void setUrl(String url) {
//        log.info("链接地址: {}",url);
//        super.setUrl(url);
//    }
//
//    @Override
//    public void setUsername(String username) {
//        log.info("用户名: {}",username);
//        super.setUsername(username);
//    }
//
//    @Override
    @Value("${config.db.password}")
    public void setPassword(String password) {
        log.info("password: {}",password);
        DruidDataSourceConfig.password = password;
    }
    @Value("${config.db.username}")
    public void setUsername(String username) {
        log.info("username: {}",username);
        DruidDataSourceConfig.username = username;
    }
    @Value("${config.db.url}")
    public void setUrl(String url) {
        log.info("url: {}",url);
        DruidDataSourceConfig.url = url;
    }


    @Bean
    @RefreshScope
    public DataSource db(){
        log.info("初始化数据库:....");
        DruidDataSource db = new DruidDataSource();
        db.setUsername(username);
        db.setPassword(password);
        db.setUrl(url);
        return db;
    }
}
