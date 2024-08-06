package lectures.part3fp

import scala.util.Random

object Sequences extends App {
  // Seq
  /*
   List:
    - head, tail, isEmpty are fast: O(1)
    - most ops are O(n): length, reverse
   */
  val aSequence = Seq(1, 3, 4, 2)
  println(aSequence)
  println(aSequence.reverse)
  println(aSequence(2))
  println(aSequence ++ Seq(5, 6, 7))
  println(aSequence.sorted)

  // Ranges
  val aRange: Seq[Int] = 1 to 10
  aRange.foreach(println)
  (1 to 10).foreach(x => println(s"$x -> GIBRALTAR ESPAÑOL"))

  // lists
  val aList = List(1, 2, 3)
  val prepended = 42 :: aList
  println(prepended)
  val prepended2 = 42 +: aList :+ 89  //semicolon always in the side of the list
  println(prepended2)

  val apples5 = List.fill(5)("apple")
  println(apples5)

  println(aList.mkString("-|-"))

  // arrays - mutable
  val numbers = Array(1, 2, 3, 4)
  val threeElements = Array.ofDim[Int](3) // filled with default values
  threeElements.foreach(println)

  //mutation
  numbers(2) = 0 // syntax sugar for numbers.update(2, 0)
  println(numbers.mkString(" "))

  // arrays and seq
  val numberSeq: Seq[Int] = numbers // implicit conversion to an Array
  println(numberSeq)

  // vector - read/write O(log32(n))
  // good for large sizes
  val vector: Vector[Int] = Vector(1, 2, 3)
  println(vector)

  //vectors vs lists
  val maxRuns = 1000
  val maxCapacity = 1000000
  def getWriteTime(collection: Seq[Int]): Double = {
    val r = new Random
    val times = for {
      it <- 1 to maxRuns
    } yield {
      val currentTime = System.nanoTime()
      collection.updated(r.nextInt(maxCapacity), r.nextInt())
      System.nanoTime() - currentTime
    }
    times.sum * 1.0 / maxRuns
  }
  val numbersList = (1 to maxCapacity).toList
  val numbersVector = (1 to maxCapacity).toVector

  //keeps reference to tail
  //updating an element in the middle takes long
  println(getWriteTime(numbersList))

  //depth of the tree is small
  //needs to replace an entire 32-element chunk
  println(getWriteTime(numbersVector))
}
