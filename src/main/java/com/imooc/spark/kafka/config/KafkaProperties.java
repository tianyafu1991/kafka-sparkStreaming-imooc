package com.imooc.spark.kafka.config;

import java.io.Serializable;

/**
 * KAFKA常用配置文件
 */
public class KafkaProperties implements Serializable {

    public static final String ZK="master:2181,slave01:2181,slave02:2181";

    public static final String TOPIC="test";

    public static final String BROKER_LIST="master:9092,slave01:9092,slave02:9092";

}
