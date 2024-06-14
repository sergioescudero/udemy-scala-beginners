package lectures.part1basics

import scala.annotation.tailrec

object Recursion extends App {

  def factorial(n: Int): Int =
    if (n <= 1) 1
    else n * factorial(n-1)


  @tailrec
  def concatenateString(aString: String, n: Int, accumulator: String): String = {
    if (n <= 0) accumulator
    else concatenateString(aString, n - 1, aString + accumulator)
  }
  println(concatenateString("hello", 3, ""))


  def isPrime(n: Int): Boolean = {
    @tailrec
    def isPrimeTailRec(t: Int, isStillPrime: Boolean): Boolean = {
      if (!isStillPrime) false
      else if (t <= 1) true
      else isPrimeTailRec(t-1, n % t != 0 && isStillPrime)
    }
    isPrimeTailRec(n/2, true)
  }
  println(isPrime(2003))
  println(isPrime(7))

  def fibonacci(n: Int): Int = {
    @tailrec
    def fibonacciAux(i: Int, last: Int, nextToLast: Int): Int = {
      if (i >= n) last
      else fibonacciAux(i + 1, last + nextToLast, last)
    }
    if (n <= 2) 1
    else fibonacciAux(2, 1, 1)
  }
  println(fibonacci(8))
}

