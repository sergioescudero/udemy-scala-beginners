package lectures.part3fp

object AnonymousFunctions extends App {

  /*
  val doubler = new Function[Int, Int] {
    override def apply(x: Int): Int = x * 2
  }
  */
  //scala way. syntatic sugar. Anonymous function (LAMBDA)
  //val doubler: Int => Int = x => x * 2
  val doubler = (x: Int) => x * 2

  //multiple params in a lambda
  val adder: (Int, Int) => Int = (a: Int, b: Int) => a + b

  // no params
  val justDoSomething: () => Int = () => 3
  println(justDoSomething) // function itself
  println(justDoSomething()) //call

  // curly braces with lambdas
  val stringToInt = { (str: String) =>
    str.toInt
  }

  //MOAR synctactic sugar
  val niceIncrementer: Int => Int = _ + 1 // equivalent to x => x + 1
  val niceAdder: (Int, Int) => Int = _ + _ // equivalent to (a,b) => a + b

  /*
   1. MyList: replace all FunctionX calls with lambdas
   2. Rewrite the "special" added asa an anonymous function
   */

  val superAdd = (x: Int) => (y: Int) => x + y
  println(superAdd(3)(4))

}
