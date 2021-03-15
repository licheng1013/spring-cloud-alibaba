package com.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
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
    private static  List<RouteDefinition> list = new ArrayList<>();

    @Value("${user.msg}")
    private String msg;
    public static final String ID = "id"; // id
    public static final String URI = "uri"; // uri
    public static final String predicates = "path"; // 路径

    @Value("${user.list}")
    public void setTest(String[] list) {
        List<RouteDefinition> routeDefinitions = new ArrayList<>();

        List<Map<String, Object>> maps = Parsing(list);
        maps.forEach(i -> {
            RouteDefinition definition = new RouteDefinition();
            definition.setId(i.get(ID).toString());
            try {
                definition.setUri(new URI(i.get(URI).toString()));
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            ArrayList<PredicateDefinition> p = new ArrayList<>();
            if (i.get(predicates) instanceof String[]){
                String[] o = (String[]) i.get(predicates);
                for (String s : o) {
                    p.add(new PredicateDefinition(s));
                }
            }else {
                p.add(new PredicateDefinition(i.get(predicates).toString()));
            }

            definition.setPredicates(p);
            routeDefinitions.add(definition);
        });

        GatewayPropertiesConfig.list = routeDefinitions;
        System.out.println(GatewayPropertiesConfig.list);
    }

    public List<Map<String, Object>> Parsing(String[] list) {
        List<Map<String, Object>> maps = new ArrayList<>();
        for (String s : list) {
            String[] split = s.split(";"); //分隔符
            Map<String, Object> map = new HashMap<>();
            for (String v : split) {
                String[] t = v.split(":"); //分隔符
                Object l = t[1];
                if ("path".equals(t[0])) {
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
