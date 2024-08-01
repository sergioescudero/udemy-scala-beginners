package lectures.part3fp

object WhatsAFunction extends App {

  // use functions as first class eleemnts
  // problem: oop

  val doubler = new MyFunction[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }
  println(doubler(2))

  // function types = Function1[A, B]
  val stringToIntConverter = new Function[String, Int] {
    override def apply(string: String): Int = string.toInt
  }
  println(stringToIntConverter("3") + 4)

  val adder: ((Int, Int) => Int)= new Function2[Int, Int, Int] {
    override def apply(a: Int, b: Int): Int = a + b
  }
  // Function types Function2[A, B, R] === (A, B) => R

  //ALL SCALA FUNCTIONS ARE OBJECTS

  /*
    1. a function which takes 2 strings and concatenates them
    2. transform the MyPredicate and MyTransformer into function types
    3. define a function which takes an int and returns another function which takes an int an returns an int
   */

  val concatenateStrings: (String, String) => String = new Function2[String, String, String] {
    override def apply(a: String, b: String): String = a + b
  }
  println(concatenateStrings("hola", "adios"))


  // Function1[Int, Function[Int, Int]]
  val supperAdder: Function1[Int, Function[Int, Int]] = new Function1[Int, Function[Int, Int]] {
    override def apply(x: Int): Function1[Int, Int] = new Function1[Int, Int] {
      override def apply(y: Int): Int = x + y
    }
  }
  val adder3 = supperAdder(3)
  println(adder3)
  println(adder3(4))
  println(supperAdder(3)(4)) //curried function: can be called by multiple parameter list
}

trait MyFunction[A, B] {
  def apply(element: A): B
}