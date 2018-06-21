package implicits

import functions.HigherOrderFunctionExample.EmployeeCalculator
import models.{Employee, EmployeeType}

object ImplicitsParameterExample extends App {
  implicit val numericEmployee: Numeric[Employee] = EmployeeCalculator
  val employees = Seq(
    Employee(1, "Somchai", "Chalerngrung", 10000.00, EmployeeType.Operator)
    , Employee(2, "Somkid", "Chalerngrung", 11000.00, EmployeeType.Operator)
    , Employee(3, "Sompong", "Chalerngrung", 9000.00, EmployeeType.Operator)
  )
  println(employees.sum.salary)
}


object X {

}
