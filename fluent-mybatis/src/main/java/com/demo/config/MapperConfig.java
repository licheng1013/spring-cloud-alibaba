package com.demo.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author lc
 * @since 2021/8/5
 */
@MapperScan("com.demo.mapper")
@Configuration
public class MapperConfig {

}
