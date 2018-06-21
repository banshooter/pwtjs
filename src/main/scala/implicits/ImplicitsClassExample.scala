package implicits

import models.{Employee, EmployeeType}

object ImplicitsClassExample extends App {

  implicit class EmployeeResign(employee: Employee) {
    def close(): Unit = {
      println(s"${employee.fullName} has resigned")
    }

  }

  def close(o: {def close(): Unit}): Unit = {
    o.close()
  }

  val employee = Employee(1, "Jump", "TheShark", 10000.00, EmployeeType.Operator)
  close(employee)

}
