# spring-cloud-alibaba

## 介绍
- 2020/1/15
- seata的简明教程-dubbo
- 本次demo AT模式

### seata
- https://seata.io/zh-cn 
- 资源都在resource目录下
- 配置是需要导入到nacos中
- 运行nacos-config.sh脚本(单机seata服务器可以不用运行)
- 创建三个数据库
- seata_goods(商品),seata_order(订单),seata_user(用户)
- 三个库都必须导入undo_log.sql
- 其他的根据名称对应导入sql下面的sql即可如 seata_goods 导入 goods.sql
- 测试接口 http://localhost:8100/order/create?userId=1&goodsId=1


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
- 乐观锁测试

### tcc事务
- try阶段检查资源,并冻结资源,可能会触发乐观锁,根据乐观锁结果在决定向map插入xid
- commit阶段,要重新读取资源,可能会触发乐观锁,根据乐观锁结果在决定向map删除xid
- rollback阶段,要重新读取资源,可能会触发乐观锁,根据乐观锁结果在决定向map删除xid
- 理论只要try阶段修改成功,其他阶段如果触发乐观锁,根据乐观锁结果在决定向map删除xid,否则则进行执行