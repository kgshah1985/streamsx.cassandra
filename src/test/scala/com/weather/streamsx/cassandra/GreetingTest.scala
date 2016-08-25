package com.weather.streamsx.cassandra

import com.datastax.driver.core.Row

import scala.collection.JavaConverters._

class GreetingTest extends PipelineTest(
  "testk",
  "testt",
  """
     |create table IF NOT EXISTS testk.testt (
     |  greeting varchar,
     |  count bigint,
     |  cool varchar,
     |  PRIMARY KEY (count)
     |) with caching = 'none';
  """.stripMargin
){

//  """
//    |create table IF NOT EXISTS testk.testt (
//    |  greeting varchar,
//    |  count bigint,
//    |  testList list<int>,
//    |  testSet set<int>,
//    |  testMap map<int, boolean>,
//    |  nInt int,
//    |  PRIMARY KEY (count)
//    |) with caching = 'none';
//  """.stripMargin

  "Tuples" should "get written to Cassandra" in {

    val structureMap = Map( "greeting" -> "rstring",
                            "count" -> "uint64",
                            "cool" -> "rstring"
//                            "testList" -> "list<int32>",
//                            "testSet" -> "set<int32>",
//                            "testMap" -> "map<int32, boolean>",
//                            "nullInt" -> "int32"
                          )
    def row2greeting(r: Row): Map[String, Any] = {
      Map(
        "greeting" -> r.getString("greeting"),
        "count" -> r.getLong("count"),
        "cool" -> r.getString("cool")
      )
    }

    val (tuple, valuesMap) = genAndSubmitTuple(structureMap)

    val rows: Seq[Row] = session.execute(s"select * from $keyspace.$table").all.asScala.toSeq

    println(s"These are the rows I got back: $rows")
    println(s"this is the map I got back: $valuesMap")

    rows should have size 1

    val received = row2greeting(rows.head)

    received shouldBe valuesMap
  }

}
