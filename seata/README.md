# spring-cloud-alibaba

## 介绍
- 2020/1/15
- seata的简明教程
- 本次demo AT模式TCC模式

### seata
- https://seata.io/zh-cn 
- 资源都在resource目录下
- 配置是需要导入到nacos中
- 运行nacos-config.sh脚本(单机seata服务器可以不用运行)
- 创建三个数据库
- seata_goods(商品),seata_order(订单),seata_user(用户)
- 三个库都必须导入undo_log.sql
- 其他的根据名称对应导入sql下面的sql即可如 seata_goods 导入 goods.sql
- 测试接口 http://localhost:9600/order/create?userId=1&goodsId=1
- freeze 字段是tcc事务下面使用

### seata部署
- seata-docker部署
- https://gitee.com/licheng1013/docker-seata   

### config.txt
- 简化配置在seata/resource
- 完整配置在script目录下

### 目标要求
- seata分布式事务(ok)
- seata单机(ok)
- seata集群

### seata集群
- docker下无法解决ip互通问题
- 在linux下手动运行集群

### 问题seata
- mysql8 最简配置
```
service.vgroupMapping.my_test_tx_group=default
service.default.grouplist=192.168.1.99:8091
store.mode=db
store.db.url=jdbc:mysql://192.168.1.99:3306/t_seata_config?useUnicode=true
store.db.user=root
store.db.password=root
store.db.dbType=mysql
store.db.datasource=druid
store.db.driverClassName=com.mysql.cj.jdbc.Driver
```
- mysql5 只需要替换className即可
store.db.driverClassName=com.mysql.jdbc.Driver
  
### seata集群数据库脚本
- t_seata_config.sql