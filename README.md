# spring-cloud-alibaba

### 介绍
阿里巴巴的使用

### 资源
- 根目录的resource下
- nacos-server-1.4.0.zip

### 技术栈
- spring-boot
- spring-cloud
- spring-cloud-alibaba
- spring-cloud-gateway
- spring-cloud-openfeign
- hutool
- nacos
- jwt

### api网关
- gateway
- https://spring.io/projects/spring-cloud-gateway
### openfeign调用
- openfeign
- https://spring.io/projects/spring-cloud-openfeign

### 使用结果
- api网关转发成功
- api网关+openfeign转发调用成功
- 全局异常处理成功
- jwt全局拦截成功
- openFeign全局token成功

### 使用常见问题
- 使用openfeign的接口的启动类上需要加上 @EnableFeignClients 注解