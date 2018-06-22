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


  val action2: Future[Seq[String]] = buyBakery()
    .flatMap(b =>
      buyCoffee().map(c => Seq(b,c))
    )

  def finalDestination(action: Future[Seq[String]]): Future[Unit] = {
    val p = Promise[Unit]()
    action.onComplete {
      case Success(seq) =>
        p.success()
        seq.foreach(println)
      case Failure(ex) =>
        p.failure(ex)
        ex.printStackTrace()
    }
    p.future
  }

  Await.result(finalDestination(action), Duration.Inf)
}
