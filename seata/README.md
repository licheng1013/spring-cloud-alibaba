# spring-cloud-alibaba

## 介绍
- 2020/1/15
- seata的简明教程
- 本次demo AT模式

### seata
- https://seata.io/zh-cn 
- 资源都在resource目录下
- 配置是需要导入到nacos中
- 运行nacos-config.sh脚本
- 创建三个数据库
- seata_goods(商品),seata_order(订单),seata_user(用户)
- 三个库都必须导入undo_log.sql
- 其他的根据名称对应导入sql下面的sql即可如 seata_goods 导入 goods.sql
- 测试接口 http://localhost:9600/order/create
- seata-docker部署
- https://gitee.com/licheng1013/docker-seata   