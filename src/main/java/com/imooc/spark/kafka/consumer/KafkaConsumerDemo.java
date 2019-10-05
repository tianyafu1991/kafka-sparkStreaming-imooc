package com.imooc.spark.kafka.consumer;

import com.imooc.spark.kafka.config.KafkaProperties;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class KafkaConsumerDemo extends Thread  {

    private String topic;

    public KafkaConsumerDemo(String topic){
        this.topic= topic;
    }

    public KafkaConsumer createConsumer(){
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", KafkaProperties.BROKER_LIST);
        props.setProperty("group.id", KafkaProperties.GROUP_ID);
        props.setProperty("enable.auto.commit", "false");
        props.setProperty("key.deserializer", KafkaProperties.KEY_DESERIALIZER);
        props.setProperty("value.deserializer", KafkaProperties.VALUE_DESERIALIZER);
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        return consumer;
    }


    @Override
    public void run() {
        KafkaConsumer consumer = createConsumer();
        consumer.subscribe(Arrays.asList(topic));
        try {
            while(true) {
                ConsumerRecords<String, String> records =
                        consumer.poll(Duration.ofSeconds(1));
//                process(records); // 处理消息
                for (ConsumerRecord<String, String> record : records) {
                    System.out.println("reciver: "+record);
                }
                consumer.commitAsync(); // 使用异步提交规避阻塞
            }
        } catch(Exception e) {
            e.printStackTrace(); // 处理异常
        } finally {
            try {
                consumer.commitSync(); // 最后一次提交使用同步阻塞式提交
            } finally {
                consumer.close();
            }
        }
    }
}
