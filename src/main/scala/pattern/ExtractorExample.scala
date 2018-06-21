package pattern

object ExtractorExample extends App {
  class Test(val x: Int, val y:Int)

  object Test {
    def apply(x: Int, y: Int) = new Test(x,y)
    def unapply(test: Test) = Option((test.x,test.y))
  }

  val test = Test.apply(1,2)
  test match {
    case Test(x,y) => x + y
    case _ => -1
  }
}
