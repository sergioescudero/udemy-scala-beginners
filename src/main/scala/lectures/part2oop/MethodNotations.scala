package lectures.part2oop

import scala.language.postfixOps

object MethodNotations extends App {

  class Person(val name: String, favoriteMovie: String, val age:Int = 0) {

    def likes(movie: String): Boolean = movie == favoriteMovie

    def +(person: Person): String = s"${this.name} is hanging out with ${person.name}"
    def +(nickName: String): Person = new Person(s"$name ($nickName)", favoriteMovie)

    def unary_! : String = s"$name, what the heck?!"
    def unary_+ : Person = new Person(this.name, this.favoriteMovie, this.age + 1)

    def isAlive: Boolean = true

    def apply(): String = s"Hi, my name is $name and I like $favoriteMovie"
    def apply(number: Int): String = s"$name watched $favoriteMovie $number times."

    def learns(language: String): String = s"$name learns $language"

    def learnsScala: String = this learns "Scala"
  }

  val mary = new Person("Mary", "Inception")

  println(mary.likes("Inception"))
  println(mary likes "Inception")  // infix notiation = operator notation. Only 1 parameter.

  val tom = new Person("Tom", "Fight Club")
  println(mary + tom)

  println( 1 + 2)
  println(1.+(2))

  //ALL OPERATORS ARE METHODS.
  /*
  Akka Actors have operators like Bang or question mark.
  These are the ask patterns or the tell patterns between asynchronous actors.
  All right.
  So at this point you've learned about infix notation and operators.
  */

  // PREFIX NOTATION
  val x = -1
  val y = 1.unary_-

  println(!mary)
  println(mary.unary_!)

  //postfix
  println(mary.isAlive)
  println(mary isAlive)

  println(mary.apply())
  println(mary())

  println((mary + "the rockstar").name)
  println((mary + "the rockstar")())

  println((+mary).age)

  println(mary learns "Scala")

  println(mary learnsScala)

  println(mary(2))



}
