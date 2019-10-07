package com.imooc.spark

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * 使用 Spark Streaming 处理文件系统（local）的数据
  */
object FileWordCount {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("FileWordCount")
    val ssc = new StreamingContext(conf,Seconds(5))
    val lines = ssc.textFileStream("file:///F:/temp/")
    val results  = lines.flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_)
    results.print()
    ssc.start()
    ssc.awaitTermination()
  }
}
