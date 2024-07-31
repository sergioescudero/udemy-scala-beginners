package lectures.part2oop

object Exceptions extends App {

  val x: String = null
  // println(x.length)
  // this ^^ will crash with a NullPointer Exception

  //1. throwing exceptions

  //val aWeirdValue: String = throw new NullPointerException

  //2. how to catch exceptions

  def getInt(withExceptions: Boolean): Int =
    if (withExceptions) throw new RuntimeException("Not int for you")
    else 42

  val potentialFail = try {
    getInt(true)
  } catch {
    case e: RuntimeException => println("caught a Runtime exception")
  } finally {
    // code that will be get executed NOT MATTER WAHT
    println("finally")
  }

  // 3. Hoe to define your own exceptions
  class MyException extends Exception
  val exception = new MyException

  //throw exception

  println(potentialFail)

  /*
  1. Crash your program with an OutOfMemoryError
  2. Crash with a Stack Overflow error
  3. PocketCalculator
    - add(x, y)
    - subtract(x, y)
    - multiply(x, y)
    - divide(x, y)

    Throw
       - OverflowException if add(x,y) exceeds Int.MAX_VALUE
       - UnderflowException if subtract(x,y) exceeds Int.MIN_VALUE
       - MathCalculationException for division by 0
   */
  // OOM
  // val array = Array.ofDim[String](Int.MaxValue)

  // SO
  //def infinite: Int = 1 + infinite
  //val noLimit = infinite

  class OverflowException extends RuntimeException
  class UnderflowException extends RuntimeException
  class MathCalculationException extends RuntimeException("Division by zero")

  object PocketCalculator {
    def add(x: Int, y: Int): Int = {
      val result = x + y
      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result > 0) throw new UnderflowException
      else result
    }

    def subtract(x: Int, y: Int): Int = {
      val result = x - y
      if (x > 0 && y < 0 && result < 0) throw new OverflowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
      else result
    }

    def multiply(x: Int, y: Int): Int = {
      val result = x * y
      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result < 0) throw new OverflowException
      else if (x > 0 && y < 0 && result > 0) throw new UnderflowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
      else result
    }

    def divide(x: Int, y: Int): Int = {
      if (y == 0) throw new MathCalculationException
      else x / y
    }
  }

  //println(PocketCalculator.add(Int.MaxValue, 10))
  println(PocketCalculator.divide(2, 0))
}