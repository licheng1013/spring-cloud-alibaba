package com.demo;

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
import java.util.List;

/**
 * @author root
 * @description 重写路由变为动态配置
 * @date 2021/3/13 17:45
 */
@Configuration
@Primary
@RestController
public class GatewayPropertiesConfig extends  GatewayProperties {
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
}
