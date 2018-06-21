package models

import models.EmployeeType.EmployeeType

object EmployeeType extends Enumeration {
  type EmployeeType = Value
  val Operator, Supervisor = Value
}

case class Employee(id: Long
                   , firstName: String
                   , lastName: String
                   , salary: Double
                   , employeeType: EmployeeType
                   ) extends Person
