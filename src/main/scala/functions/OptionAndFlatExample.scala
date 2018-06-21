package functions

import models.Customer

import scala.util.Random

object OptionAndFlatExample extends App {

  def getCustomerById(id: Long): Option[Customer] = {
    if (Random.nextInt(4) == 0) {
      None
    } else {
      Some(Customer(id, Random.nextString(3), Random.nextString(10)))
    }
  }


  val customer1to10 = (1L to 10L).map(getCustomerById)
  customer1to10.size
  customer1to10.filter(c=>c.isDefined) == customer1to10.flatten.size
  customer1to10.flatMap(c=> c.map(_.id)) // seq of customerId -- Seq[Long]
  customer1to10.flatten.map(_.id) // seq of customerId -- Seq[Long]

}
