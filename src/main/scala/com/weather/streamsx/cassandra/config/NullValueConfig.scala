package com.weather.streamsx.cassandra.config

import com.weather.streamsx.cassandra.zooklient.ZooKlient
import com.weather.streamsx.cassandra.exception.CassandraWriterException
import scala.util.parsing.json.JSON

//TODO check and see if this breaks if the nullValue config has more than 22 items

object NullValueConfig {
  private def mkMap(zkCli: ZooKlient, znodeName: String): Map[String, Any] = zkCli.readRawString(znodeName) match {
    case Some(str) => {
      val x: Any = JSON.parseFull(str).getOrElse(throw CassandraWriterException("Unable to parse null value JSON"))
      x.asInstanceOf[Map[String, Any]]
    }
    case _ => throw CassandraWriterException(s"Failed to get data from ZNode $znodeName")
  }

  def apply(zkCli: ZooKlient, znodeName: String): Map[String, Any] = znodeName match {
    case null | "" => Map.empty[String, Any]
    case _ => mkMap(zkCli, znodeName)
  }
}