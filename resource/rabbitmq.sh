docker exec -it mq1 bash
rabbitmqctl stop_app && rabbitmqctl reset && rabbitmqctl start_app
exit

docker exec -it mq2 bash
rabbitmqctl stop_app && rabbitmqctl reset && rabbitmqctl join_cluster  rabbit@rabbitmq1 && rabbitmqctl start_app
exit

docker exec -it mq3 bash
rabbitmqctl stop_app && rabbitmqctl reset && rabbitmqctl join_cluster  rabbit@rabbitmq1 && rabbitmqctl start_app
exit