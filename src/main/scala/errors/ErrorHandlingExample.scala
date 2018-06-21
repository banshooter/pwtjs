package errors

import java.io.IOException

import scala.util.{Failure, Success, Try}
import scala.util.control.NonFatal

object ErrorHandlingExample extends App {

  try {
    throw new IllegalArgumentException("just wanna throw")
  } catch {
    case NonFatal(ex) => ex.printStackTrace()
  }

  Try(
    "10000".toInt
  ) match {
    case Success(i) => println(s"Number is ${i}")
    case Failure(t) => t.printStackTrace()
  }

  def getContent(urlString: String): String = {
    val source = io.Source.fromURL(urlString, "UTF-8")
    val content = source.getLines().mkString
    source.close()
    content
  }

  Try(getContent("http://localhost:8080"))
    .recoverWith {  case ioEx: IOException => Try("http://localhost:8081") }
  match {
    case Success(s) => println(s" content is ${s}")
    case Failure(t) => t.printStackTrace()
  }

}
