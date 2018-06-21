package pattern

import models.{Customer, Employee}

import scala.util.Random

object PatternMatchingExample extends App {
  val x = Random.nextInt(8)
  x match {
    case 0 => println("zero")
    case 1 => println("one")
    case 2 => println("two")
    case 3 => println("three")
    case _ => println("more than two")
  }

  (1, true, "test") match {
    case (_, false, _) => println("false")
    case (_, _, "production") => println("production")
    case (_, true, _) => println("true")
    case _ => println("other")
  }

  val person = Customer(1L, "Michael", "Angel")
  person match {
    case Customer(_, "Michael", _) => println("It's Michael.")
    case _ => println("Someone else")
  }

  person match {
    case p: Employee => println("Employee") // compiler will warn because person is Customer
    case p: Customer if p.firstName == "Michael" => println("It's Michael.")
    case _ => println("Unemployed")
  }



  val intSeq = 10 :: 20 :: 30 :: Nil
  intSeq match {
    case Seq(_, second, _*) => Option(second)
    case _ => None
  }
}
