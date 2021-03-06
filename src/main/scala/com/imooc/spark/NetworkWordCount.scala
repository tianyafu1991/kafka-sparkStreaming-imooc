package com.imooc.spark

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  *  Spark Streaming处理Socket数据
  */
object NetworkWordCount {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[2]").setAppName("NetworkWordCount")
    /**
      * 创建StreamingContext需要传入两个参数：SparkConf和batch interval
      */
    val ssc= new StreamingContext(conf,Seconds(5))

    val lines  = ssc.socketTextStream("master",6789)

    val result = lines.flatMap(_.split(" ")).map((_,1)).reduceByKey(_ + _)

    result.print()

    ssc.start()
    ssc.awaitTermination()
  }

}
