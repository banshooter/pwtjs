package models

trait Person {
  val firstName: String
  val lastName: String
  lazy val fullName = s"$firstName $lastName"
}
