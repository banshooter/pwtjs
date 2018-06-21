package implicits

import models.{Employee, EmployeeType}

object ImplicitConversionExample extends App {
  implicit def employeeToDouble(employee: Employee): Double = employee.salary
  def printDouble(double: Double) = println(double)
  val employee = Employee(1, "X", "Y", 1234.56, EmployeeType.Operator)
  printDouble(employee)
}
