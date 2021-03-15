package com.demo;

import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.web.bind.annotation.GetMapping;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author root
 * @description 重写路由变为动态配置
 * @date 2021/3/13 17:45
 */
//@Configuration
//@Primary
//@RestController
//@RefreshScope
public class GatewayPropertiesConfig extends  GatewayProperties {

//    @Value("${routes}")
    public String map;

    private  static final ArrayList<RouteDefinition> list = new ArrayList<>();
    @Override
    public List<RouteDefinition> getRoutes() {
        return list;
    }

    @GetMapping("add")
    public Object add(){
        RouteDefinition definition = new RouteDefinition();
        definition.setId("user-route");
        try {
            definition.setUri(new URI("lb://alibaba-user"));
        } catch (URISyntaxException e) {
        }
        ArrayList<PredicateDefinition> p = new ArrayList<>();
        p.add(new PredicateDefinition("Path=/user/**"));
        definition.setPredicates(p);
        list.add(definition);
        return list;
    }
    @GetMapping("get")
    public Object get(){
        return list;
    }

    @GetMapping("del")
    public Object delete(){
        list.clear();
        return list;
    }

    @GetMapping("routes")
    public Object routers(){
        return map;
    }
}
