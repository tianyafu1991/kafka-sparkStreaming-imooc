package com.imooc.spark

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * 黑名单过滤
  */
object TranformApp {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[2]").setAppName("TranformApp")
    val ssc = new StreamingContext(conf,Seconds(5))
    /**
      * 构建黑名单 实际上黑名单是保存在数据库中
      */
      val blacks = List("zs","ls")
    val blacksRDD = ssc.sparkContext.parallelize(blacks).map((_,true))

    val lines  = ssc.socketTextStream("master",6789)

    val clickLog = lines.map(x =>(x.split(",")(1),x)).transform(rdd=>{
      rdd.leftOuterJoin(blacksRDD).filter(
        x => x._2._2.getOrElse(false)!=true).map(x => x._2._1)
    }
    )

    clickLog.print()

    ssc.start()
    ssc.awaitTermination()
  }

}
