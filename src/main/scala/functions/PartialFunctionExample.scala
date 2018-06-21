package functions

class PartialFunctionExample {
  val multiplyByTwo: (Int) => Int = {
    case i: Int if i > 10 => 10 * 2
  }


}
