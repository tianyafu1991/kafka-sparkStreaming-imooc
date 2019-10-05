package com.imooc.spark.kafkaProducerTest;

import com.imooc.spark.kafka.config.KafkaProperties;
import com.imooc.spark.kafka.consumer.KafkaConsumerDemo;
import com.imooc.spark.kafka.producer.KafkaProducerDemo;

public class KafkaClientApp {

    public static void main(String[] args) {
        new KafkaProducerDemo(KafkaProperties.TOPIC).start();
        new KafkaConsumerDemo(KafkaProperties.TOPIC).start();
    }
}
