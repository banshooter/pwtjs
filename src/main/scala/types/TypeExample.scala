package types

import java.io.Closeable

class TypeExample {

  def close1(o : Closeable): Unit = {
    o.close()
  }
























  def close2( o : { def close(): Unit } ): Unit = {
    o.close()
  }





  def close3[U <: Closeable](o: U) {
    o.close()
  }


  def close4[U <: { def close(): Unit }](o : U ): Unit = {
    o.close()
  }

}
