package iterators

import models.Customer
import org.scalatest._

class CustomerRepositorySpec extends FlatSpec with Matchers {
  val repo = new CustomerRepository()
  repo.dropTable()
  repo.createTable()

  val jackson = Customer(1, "Michael", "Jackson")
  val owen = Customer(2, "Michael", "Owen")
  val learn = Customer(3, "Michael", "LearnToRock")

  "CustomerRepository" should "insert properly" in {
    repo.insert(jackson)
    repo.insert(owen)
    repo.insert(learn)
  }

  "CustomerRepository" should "query properly" in {
    repo.all().size shouldEqual  3
    repo.all().contains(jackson) shouldEqual true
    repo.all().contains(owen) shouldEqual true
    repo.all().contains(learn) shouldEqual true
  }
}
