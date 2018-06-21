package types

import models.{Customer, Employee, EmployeeType, Person}

import scala.collection.mutable

class VarianceExample {

  // Covariance can apply from super type to sub type and vise versa
  // Contra variance can only apply from sub type to super type but not vise versa
  trait Container[+A] {
    def add[U >: A](a: U): Container[U]
  }

  class MyContainer[+A](headItem: A, tailContainer: Container[A]) extends Container[A] {
    override def add[U >: A](a: U): Container[U] = new MyContainer(a, this)
    def head: A = headItem
    def tail: Container[A] = tailContainer
  }

  case class Nil[+A]() extends Container[A] {
    override def add[U >: A](a: U): Container[U] = new MyContainer(a, this)
  }


  val employee = Employee(1, "Charles", "Xavier", 10000000d, EmployeeType.Supervisor)
  val customer = Customer(1, "Max", "Eisenhardt")
  val employees = new MyContainer(employee, Nil())
  val personContainer: MyContainer[Person] = employees
  val persons = personContainer.add(customer)


  // Invariance can apply only to its type
  val mEmployees = mutable.ListBuffer[Employee](employee)
//  val mPersons: mutable.ListBuffer[Person] = mEmployees // uncompilable
}
