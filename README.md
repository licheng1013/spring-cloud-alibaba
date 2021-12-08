# spring-cloud-alibaba

### 介绍
阿里巴巴的使用

### 资源
- 根目录的resource下
- nacos-server-1.4.0.zip

## 技术栈
### springboot
- 微服务的地砖
- https://spring.io/projects/spring-boot
### springCloudAlibaba
- 阿里巴巴的微服务框架
- https://spring.io/projects/spring-cloud-alibaba
### springCloud
- springCloud系列
- https://spring.io/projects/spring-cloud
### gateway
- 网关
- https://spring.io/projects/spring-cloud-gateway
- nacos+gateway 动态网关实现
### openfeign
- 服务调用
- https://spring.io/projects/spring-cloud-openfeign
### jwt
- 用户认证
- https://github.com/auth0/java-jwt
### nacos
- 注册中心(单机)
- https://nacos.io/zh-cn/
- 注册中心(集群)
- https://gitee.com/licheng1013/nacos-cluster
### hutool
- 工具类
- https://www.hutool.cn/docs/#/
### redis
- redis集群
- https://spring.io/projects/spring-data-redis
### seata
- 分布式事务(AT完成)
- 分布式事务(XA完成)
### sharding-jdbc
- 数据库分库分表(完成)
### rabbitmq
- 消息队列(单机)(完成)
- 消息队列(集群)(完成)
- https://gitee.com/licheng1013/docker-rabbitmq-cluster
### dubbo
- https://github.com/apache/dubbo-spring-boot-project/tree/master/dubbo-spring-boot-samples/registry-samples/nacos-samples
- dubbo使用

### Sentinel
- 限流框架
- https://sentinelguard.io/zh-cn/docs/quick-start.html

### 使用常见问题
- 使用openfeign的接口的启动类上需要加上 @EnableFeignClients 注解

```
<!--不需要运行但是要被其他项目所依赖的包,需要改成jar包-->
<packaging>jar</packaging>
```



## 项目说明
- common(公共项目)
- gateway(api网关)
- order(示例订单模块)
- user(示例用户模块)
- redis(如果需要使用redis,则在其他模块依赖此模块进去,配置yml即可)

