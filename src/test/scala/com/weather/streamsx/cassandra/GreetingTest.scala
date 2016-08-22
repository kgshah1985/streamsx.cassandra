package com.weather.streamsx.cassandra
import scala.collection.JavaConverters._

class GreetingTest extends PipelineTest(
  "testk",
  "testt",
  """
     |create table testk.testt IF NOT EXISTS testkeyspace.testtable (
     |  greeting varchar,
     |  count bigint,
     |  testList list<int>,
     |  testSet set<int>,
     |  testMap map<int, boolean>,
     |  nInt int,
     |  PRIMARY KEY (count)
     |) with caching = 'none';
  """.stripMargin
){



  "Tuples" should "get written to Cassandra" in {
//    var t = generator.newEmptyTuple()

    val structureMap = Map( "greeting" -> "rstring",
                            "count" -> "uint64",
                            "testList" -> "list<int32>",
                            "testSet" -> "set<int32>",
                            "testMap" -> "map<int32, boolean>",
                            "nullInt" -> "int32"
                          )

    val tuple, valuesMap = genAndSubmitTuple(structureMap)
    
    val rows = session.execute(s"select * from $keyspace.$table").all.asScala.toSeq
    rows should have size 1
  }

}