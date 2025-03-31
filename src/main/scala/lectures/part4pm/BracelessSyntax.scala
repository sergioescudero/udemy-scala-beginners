package lectures.part4pm

object BracelessSyntax {

  // if - expressions
  val anIfExpression = if (2 > 3) "bigger" else "smaller"

  // java - style
  val anIfExpression_v2 =
    if (2 > 3) {
      "bigger"
    } else {
      "smaller"
    }

  // compact
  val anIfExpression_v3 =
    if (2 > 3) "bigger"
    else "smaller"

  // scala 3
  val anIfExpression_v4 =
    if 2 > 3 then
      "bigger"  // higher indentation than the if part
    else
      "smaller"

  // scala 3
  val anIfExpression_v5 =
    if 2 > 3 then
      val result = "bigger"
      result
    else
      val result = "smaller"
      result

  // scala 3 one-liner
  val anIfExpression_v6 = if 2 > 3 then "bigger" else "smaller"

  // for comprehensions
  val aForComprehension = for {
    n <- List(1, 2, 3)
    s <- List("black", "white")
  } yield s"$n$s"

  // scala 3
  val aForComprehension_v2 =
    for
      n <- List(1, 2, 3)
      s <- List("black", "white")
    yield s"$n$s"

  // pattern matching
  val meaningOfLife = 42
  val aPatterMatch = meaningOfLife match {
    case 2 => "the one"
    case _ => "something else"
  }

  // scala 3
  val aPatterMatch_v2 = meaningOfLife match
    case 2 => "the one"
    case _ => "something else"

  // method without braces
  def computeMeaningOfLife(arg: Int): Int = {
    val partialResult = 40

    partialResult + 2
  }

  // scala 3
  def computeMeaningOfLife_v2(arg: Int): Int = {
    val partialResult = 40

    partialResult + 2
  }


  //class
  class Animal {
    def eat(): Unit =
      println("eating")
  }

  //scala 3
  class Animal_v2:
    def eat(): Unit =
      println("eating")
    end eat

    def grow(): Unit =
      println("growing")

    //thousands lines of code

  end Animal_v2 // for if, match, for, methods, classes, traits, enums, objects

  // anonymous classes
  val aSpecialAnimal = new Animal:
    override def eat(): Unit = println("eat")

  //indentation = strictly larger indentation
  // 3 spaces + 2 tabs > 2 spaces + 2 tabs
  // 3 spaces + 2 tabs > 3 spaces + 1 tab
  // 3 tabs + 2 spaces ??? 2 tabs + 3 spaces
  // don't mix

  def main(args: Array[String]): Unit = {
    println(anIfExpression_v5)
  }
}
