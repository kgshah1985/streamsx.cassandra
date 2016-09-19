package com.weather.streamsx.cassandra

import com.datastax.driver.core.Row

import scala.collection.JavaConverters._

// just set
class CollectionsListTest extends PipelineTest(
  "testKEYSLIST",
  "testTABLELIST",
  """
     |create table IF NOT EXISTS testKEYSLIST.testTABLELIST (
     |      count bigint,
     |      listA list<int>,
     |  PRIMARY KEY (count)
     |) with caching = 'none';
  """.stripMargin
){

  override def beforeAll(): Unit = {
    super.beforeAll()
  }

  override def afterAll(): Unit = {
    super.afterAll()
  }

    val structureMap = Map(
                            "count" -> "uint64",
                            "listA"  -> "list<int32>"
                          )
    def row2greeting(r: Row): Map[String, Any] = {
      Map(
        "count" -> r.getLong("count"),
        "listA" -> r.getList("listA", classOf[java.lang.Integer])
      )
    }

  "The operator" should "write only one tuple to C*" in {
    val (tuple, valuesMap) = genAndSubmitTuple(structureMap)
    val rows: Seq[Row] = session.execute(s"select * from $keyspace.$table").all.asScala.toSeq
    val received = row2greeting(rows.head)

    rows should have size 1
    received shouldBe valuesMap
  }
}
