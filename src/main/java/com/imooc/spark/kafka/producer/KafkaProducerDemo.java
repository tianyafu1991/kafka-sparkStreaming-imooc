package com.imooc.spark.kafka.producer;

import com.imooc.spark.kafka.config.KafkaProperties;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;

/**
 * KAFKA 生产者
 */
public class KafkaProducerDemo extends Thread {

    private String topic;

    private KafkaProducer<String, String> kafkaProducer;

    public KafkaProducerDemo(String topic) {
        this.topic = topic;
        Properties props = new Properties();
        // Kafka服务端的主机名和端口号
        props.put("bootstrap.servers", KafkaProperties.BROKER_LIST);
        // 等待所有副本节点的应答
        props.put("acks", "all");
        // 消息发送最大尝试次数
        props.put("retries", 0);
        // 一批消息处理大小
        props.put("batch.size", 16384);
        // 增加服务端请求延时
        props.put("linger.ms", 1);
        // 发送缓存区内存大小
        props.put("buffer.memory", 33554432);
        // key序列化
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        // value序列化
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProducer = new KafkaProducer<>(props);
    }

    @Override
    public void run() {
        int messageNo=1;
        while (true){
            String message = "message_"+messageNo;
            ProducerRecord<String,String> record = new ProducerRecord<>(topic, "tianyafu", message);
            kafkaProducer.send(record, new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception exception) {
                    if (exception!=null){
                        System.out.println("The offset of the record we just sent is: " + metadata.offset());
                    }else{
                        exception.printStackTrace();
                    }
                }
            });
            messageNo++;
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
