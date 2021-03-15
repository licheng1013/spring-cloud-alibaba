package com.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author root
 * @description 重写路由变为动态配置
 * @date 2021/3/13 17:45
 */
@Configuration
@Primary
@RestController
@RefreshScope
public class GatewayPropertiesConfig extends GatewayProperties {
    private static final List<RouteDefinition> list = new ArrayList<>();

    @Value("${user.msg}")
    private String msg;

    @Value("${user.list}")
    public void setTest(String[] list) {
        List<Map<String, Object>> maps = Parsing(list);
        System.out.println(maps);

    }

    public List<Map<String, Object>> Parsing(String[] list) {
        List<Map<String, Object>> maps = new ArrayList<>();
        for (String s : list) {
            String[] split = s.split(";"); //分隔符
            Map<String, Object> map = new HashMap<>();
            for (String v : split) {
                String[] t = v.split(":"); //分隔符
                Object l =  t[1];
                if ("path".equals(t[0])){
                    String[] j = t[1].split("-");
                    if (j.length != 0) { //至少有一个
                        l = j;
                    }
                }
                map.put(t[0], l);
            }
            maps.add(map);
        }
        return maps;
    }


    @Override
    public List<RouteDefinition> getRoutes() {
        return list;
    }

    @GetMapping("test")
    public Object routers() {
        return msg;
    }
}
