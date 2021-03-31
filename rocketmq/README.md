# rocketMQ

## 介绍
- 2021/3/31
- 断断续续踩了几天坑...QAQ
- 学习版本 rocketmq-all-4.8.0-bin-release.zip

### 使用
- 本次在linux下使用..
- 首先需要修改 rocketmq-all-4.8.0-bin-release/bin 下的脚本文件
- runserver.sh 和 runbroker.sh 脚本文件 vim打开修改jvm参数,默认的太大了...
- 运行脚本 -n 本机ip:端口

```shell
# 启动服务
sh bin/mqnamesrv -n 192.168.1.30:9876 &
# 日志 
tail -f ~/logs/rocketmqlogs/namesrv.log

# 启动经纪人...
# 需要修改一个文件  conf/broker.conf 往最下面增加一行 brokerIP1 = 192.168.1.30 #本地ip不配置则会超时异常..
nohup sh bin/mqbroker -n 192.168.1.30:9876 -c conf/broker.conf autoCreateTopicEnable=true &
#日志
tail -f ~/logs/rocketmqlogs/broker.log  
```

