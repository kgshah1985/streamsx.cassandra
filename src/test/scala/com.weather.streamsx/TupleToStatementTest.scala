package com.weather.streamsx

import java.io._

import com.ibm.streams.flow.declare.{InputPortDeclaration, OperatorInvocation}
import com.ibm.streams.flow.javaprimitives.{JavaOperatorTester, JavaTestableGraph}
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{BeforeAndAfterAll, FlatSpec, Matchers}
import com.ibm.streams.operator.{StreamingOutput, OutputTuple, Tuple}

import scala.util.Random

@RunWith(classOf[JUnitRunner])
class TupleToStatementTest extends FlatSpec with Matchers with BeforeAndAfterAll {

  "The thing" should "happen" in {

    try {
      val session = CasAnalyticsConnector.session
      val (map, tuple) = TupleGenerator()
//      TupleToStatement(tuple, map, session)
    }
    catch{
      case e: Exception => {
        println(e.getCause)
        val sw = new StringWriter
        e.printStackTrace(new PrintWriter(sw))
        println(sw.toString)
      }

    }
  }

//  "Prepared Statements" should "have the right number of values" in {
//
//  }
//
//  it should "be pulled correctly from the cache" in {
//
//  }
//
//  "New field names" should "flush the cache" in {
//
//  }
//
//  "Bound Statements" should "get created" in {
//
//
//
//
//  }
//
//  it should "have the same fields and values as the tuple" in {
//
//  }

}

object TupleGenerator {


//  val streamingOutput: StreamingOutput[OutputTuple] = graph.getInputTester(testPort)


  def apply(): (Map[String, Boolean], OutputTuple) = {
    //boilerplate

//    val clazz = classOf[CassandraSink]
//
//    val tester: JavaOperatorTester = new JavaOperatorTester()
//    val invocation = tester.singleOp(clazz)
//    val invocation = tester.singleOp(null)
//    val invocation = tester.singleOp(classOf[RandomBeacon])

    //    val graph: JavaTestableGraph = tester.tester(invocation)
//    val ports: InputPortDeclaration = invocation.addInput("blah")
//    val streamingOutput: StreamingOutput[OutputTuple] = graph.getInputTester(ports)
//
//    val t = streamingOutput.newTuple()
//
//    val map: Map[String, Boolean] = Map("str" -> true, "nullStr" -> false, "int" -> true, "nullInt" -> false)
//
//    t.setString("str", "this should be written")
//    t.setString("nullStr", "this should NOT be writter")
//    t.setInt("int", 93)
//    t.setInt("nullInt", 44)
//
////    val types = List("boolean", "int8", "int16", "int32", "int64", "uint8", "uint16", "uint32", "rstring", "ustring")
////    for (x <- Random.shuffle(types).take(3)) addRandomTupleField(x, t)
//    (map, t)
    (null, null)
  }





  def addRandomTupleField(splType: String, outputTuple: OutputTuple): OutputTuple = {
    val fieldname = splType + Random.alphanumeric.take(5)

    splType match {
      case "boolean" => outputTuple.setBoolean(fieldname, Random.nextBoolean())
      case "int8" => outputTuple.setByte(fieldname, ((Math.abs(Random.nextInt) % 256) - 128).toByte)
      case "int16" => outputTuple.setShort(fieldname, ((Math.abs(Random.nextInt) % 32768) - 16384).toShort)
      case "int32" => outputTuple.setInt(fieldname, Random.nextInt())
      case "int64" => outputTuple.setLong(fieldname, Random.nextLong())
      case "uint8" => outputTuple.setByte(fieldname, (Math.abs(Random.nextInt) % 256).toByte)
      case "uint16" => outputTuple.setShort(fieldname, (Math.abs(Random.nextInt) % 32768).toShort)
      case "uint32" => outputTuple.setInt(fieldname, ((Math.abs(Random.nextLong())) % 4294967296L).toInt)
//      case "uint64" => outputTuple.getLong(attr.index)
//      case "float32" => outputTuple.getFloat(attr.index)
//      case "float64" => outputTuple.getDouble(attr.index)
//      case "decimal32" => outputTuple.getBigDecimal(attr.index)
//      case "decimal64" => outputTuple.getBigDecimal(attr.index)
//      case "decimal128" => outputTuple.getBigDecimal(attr.index)
//      case "timestamp" => outputTuple.getTimestamp(attr.index)
      case "rstring" => outputTuple.setString(fieldname, Random.alphanumeric.take(10).toString())
      case "ustring" => outputTuple.setString(fieldname, Random.alphanumeric.take(10).toString())
//      case "blob" => outputTuple.getBlob(attr.index)
//      case "xml" => outputTuple.getXML(attr.index).toString //Cassandra doesn't have XML as data type, thank goodness
//      case "list" => outputTuple.getList(attr.index) //I'm dubious, I don't think collection types are going to be this easy
//      //I wonder if there will need to be more specific qualifications with list<boolean>, list<int>, etc
//      case "map" => outputTuple.getMap(attr.index) //same dubiosity for maps as for lists
//      //    case "outputTuple" => outputTuple.getoutputTuple(attr.index)
//      case "" => "figure out better error logging than this"
    }

    outputTuple
  }
}