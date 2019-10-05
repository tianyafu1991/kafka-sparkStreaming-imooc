package com.imooc.spark.kafka.config;

import java.io.Serializable;

/**
 * KAFKA常用配置文件
 */
public class KafkaProperties implements Serializable {

    public static final String ZK="master:2181,slave01:2181,slave02:2181";

    public static final String TOPIC="test";

    public static final String BROKER_LIST="master:9092,slave01:9092,slave02:9092";

    public static final String GROUP_ID="tianyafu";

    public static final String KEY_SERIALIZER="org.apache.kafka.common.serialization.StringSerializer";
    public static final String KEY_DESERIALIZER="org.apache.kafka.common.serialization.StringDeserializer";

    public static final String VALUE_SERIALIZER=KEY_SERIALIZER;
    public static final String VALUE_DESERIALIZER=KEY_DESERIALIZER;

}
