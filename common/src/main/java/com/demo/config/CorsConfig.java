package com.demo.config;

import org.springframework.context.annotation.Configuration;

/**
 * @author lc
 * @date 2021/1/11
 * @description 跨域配置, 通过gateway框架来转发,gateway如果配置了跨域,则其他转发项目中不需要配置跨域,
 */
@Configuration
public class CorsConfig {  
//    private CorsConfiguration buildConfig() {
//        CorsConfiguration corsConfiguration = new CorsConfiguration();
//        // 1允许任何域名使用
//        corsConfiguration.addAllowedOrigin("*");
//        // 2允许任何头
//        corsConfiguration.addAllowedHeader("*");
//        // 3允许ajax异步请求带cookie信息
//        corsConfiguration.setAllowCredentials(true);
//        // 4允许任何方法（post、get等）
//        corsConfiguration.addAllowedMethod("*");
//        // 5设置OPTIONS预检请求的过期时间
//        corsConfiguration.setMaxAge(Duration.ofDays(1));
//        return corsConfiguration;
//    }
//
//    @Bean
//    public CorsFilter corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", buildConfig());
//        return new CorsFilter(source);
//    }
}