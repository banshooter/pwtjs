package functions

import models.{Employee, EmployeeType}

object HigherOrderFunctionExample extends App {

  val intSeq = Seq(1,2,3,4)
  val sumOfIntSeq = intSeq.sum // 10
  val factorialOfIntSeq = intSeq.reduce(_ * _) // 24
  intSeq.reduce((a,b) => a * b) // 24

  val floatSeq = Seq(44.4f, 33.3f, 22.2f, 11.1f)
  val sumOfDoubleSeq = floatSeq.sum // 110.99999
  val floatIntTupleSeq = floatSeq.zip(intSeq) // List((44.4,1), (33.3,2), (22.2,3), (11.1,4))

  val multiplyOfTupleItemSeq = floatIntTupleSeq.map(t => t._1 * t._2)


  val intSeq2 = Seq(Integer.MAX_VALUE, 1)
  intSeq2.sum // -2147483648 aka INTEGER.MIN_VALUE
  intSeq2.foldLeft(0L)( (longNum, intValue) => longNum + intValue) // 2147483648
  intSeq2.foldLeft(0L)(_ + _) // 2147483648

  val employees = Seq(
    Employee(1, "Somchai", "Chalerngrung", 10000.00, EmployeeType.Operator)
    , Employee(2, "Somkid", "Chalerngrung", 11000.00, EmployeeType.Operator)
    , Employee(3, "Sompong", "Chalerngrung", 9000.00, EmployeeType.Operator)
  )
  employees.map(_.salary).sum // total paid monthly salary 30000.00

  employees.sum(EmployeeCalculator).salary  // total paid monthly salary 30000.00


  object EmployeeCalculator extends Numeric[Employee] {
    override def plus(x: Employee, y: Employee): Employee = x.copy(salary = x.salary + y.salary)

    override def minus(x: Employee, y: Employee): Employee = x.copy(salary = x.salary - y.salary)

    override def times(x: Employee, y: Employee): Employee = x.copy(salary = x.salary * y.salary)

    override def negate(x: Employee): Employee = x.copy(salary = -x.salary)

    override def fromInt(x: Int): Employee =
      Employee(id = -1
      , firstName = null
      , lastName = null
        , salary = x,
        employeeType = EmployeeType.Operator
      )

    override def toInt(x: Employee): Int = x.salary.toInt

    override def toLong(x: Employee): Long = x.salary.toLong

    override def toFloat(x: Employee): Float = x.salary.toFloat

    override def toDouble(x: Employee): Double = x.salary

    override def compare(x: Employee, y: Employee): Int = if (x.salary > y.salary)
      1
    else if (x.salary == y.salary)
      0
    else
      -1
  }
}
