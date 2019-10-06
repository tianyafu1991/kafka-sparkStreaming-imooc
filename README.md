# kafka-sparkStreaming-imooc

###
创建topics:
kafka-topics.sh --create --bootstrap-server master:9092,slave01:9092,slave02:9092 --replication-factor 2 --partitions 2 --topic test

查看topics:
kafka-topics.sh --list --bootstrap-server master:9092,slave01:9092,slave02:9092

描述详细的topics信息：
kafka-topics.sh --describe --bootstrap-server master:9092,slave01:9092,slave02:9092 --topic test

生产消息：
kafka-console-producer.sh --broker-list master:9092,slave01:9092,slave02:9092 --topic test

启动消费者：
kafka-console-consumer.sh --bootstrap-server master:9092,slave01:9092,slave02:9092 --topic test --from-beginning
###