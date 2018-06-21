package futures

import scala.concurrent.{Await, Future, Promise}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.util.{Failure, Success}

object FutureExample extends App {

  def buyBakery(): Future[String] = {
    Future {
      Thread.sleep(100)
      "I bought Danish."
    }
  }

  def buyCoffee(): Future[String] = {
    Future {
      Thread.sleep(1000)
      "I bought Espresso."
    }
  }

  val action: Future[Seq[String]] =
    for (buyBakeryResult <- buyBakery();
       buyCofferResult <- buyCoffee()
      ) yield Seq(buyBakeryResult, buyCofferResult)

  val finalDestination = Future {
    action.onComplete {
      case Success(seq) =>
        seq.foreach(println)
      case Failure(ex) =>
        ex.printStackTrace()
    }
  }
  Await.result(finalDestination, Duration.Inf)
}
