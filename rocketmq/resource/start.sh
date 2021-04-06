#!/bin/bash
# 通用变量
t_start='start' 
t_stop='stop'
t_restart='restart'

if [ $# -eq 0 ];then
    echo "参数命令: "
	echo "启动: $t_start"
	echo "停止: $t_stop"
	echo "重启: $t_restart"
    exit 0
fi

params=$1 #参数1
ports=(8091 8092 8093) #端口
if [ $params = $t_start ];then
	echo "启动"
	f_start
	exit 0
elif [ $params = $t_stop ];then
	echo "停止"
	f_stop
	exit 0
fi

f_stop(){
	for v in ${ports[@]}
	do
		echo "$v端口进程停止"
		pid=`netstat -anp |grep $v | head -n 1 |  awk '{print $7}' | awk -F / '{print $1}'`
		kill -9 $pid
	done
}
f_start(){
	for v in ${ports[@]}
	do
		echo "$v端口进程启动"
		nohup ./seata/bin/seata-server.sh -p $v  > $v.log 2>&1 &
	done
}


