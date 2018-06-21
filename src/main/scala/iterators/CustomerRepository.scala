package iterators
import java.sql.{Connection, DriverManager, ResultSet}

import models.Customer

class CustomerRepository {
  private lazy val sqlCreateTableCustomer = """CREATE TABLE customers(
                                              | id LONG PRIMARY KEY,
                                              | first_name VARCHAR(50),
                                              | last_name VARCHAR(100)
                                              |)""".stripMargin

  def createTable(): Unit = {
    val conn = getConnection()
    val stmt = conn.prepareStatement(sqlCreateTableCustomer)
    stmt.execute()
    stmt.close()
    conn.close()
  }

  private lazy val sqlDropTableCustomer = "DROP TABLE IF EXISTS customers"
  def dropTable(): Unit = {
    val conn = getConnection()
    val stmt = conn.prepareStatement(sqlDropTableCustomer)
    stmt.execute()
    stmt.close()
    conn.close()
  }

  private lazy val sqlInsert = "INSERT INTO customers(id, first_name, last_name) VALUES (?, ?, ?)"
  def insert(customer: Customer): Unit = {
    val conn = getConnection
    val stmt = conn.prepareStatement(sqlInsert)
    stmt.setLong(1, customer.id)
    stmt.setString(2, customer.firstName)
    stmt.setString(3, customer.lastName)
    stmt.execute()
    stmt.close()
    conn.close()
  }

  private lazy val sqlSelectAll = "SELECT * FROM customers"
  def all(): Seq[Customer] = {
    val conn = getConnection
    val stmt = conn.prepareStatement(sqlSelectAll)
    val rs = stmt.executeQuery()
    val result: Seq[Customer] = new ResultSetIterator(rs, customerFromResultSet).toList
    rs.close()
    stmt.close()
    conn.close()
    result
  }


  private def customerFromResultSet(rs: ResultSet): Customer = {
    Customer(
      rs.getLong("id")
      , rs.getString("first_name")
      , rs.getString("last_name")
    )
  }

  private def getConnection(): Connection = {
    Class.forName("org.h2.Driver")
    DriverManager.getConnection("jdbc:h2:~/test", "", "")
  }
}

class ResultSetIterator[A](rs: ResultSet, parserFunc: (ResultSet)=>A) extends Iterator[A] {
  override def hasNext: Boolean = rs.next()

  override def next(): A = parserFunc(rs)
}
