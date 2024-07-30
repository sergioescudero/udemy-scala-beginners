package lectures.part2oop

object AnonymousClasses extends App {

  abstract class Animal {
    def eat: Unit
  }

  // anonymous class
  val funnyAnimal: Animal = new Animal {
    override def eat: Unit = println("ahahahaha")
  }

  /**
   * equivalent with
   *
   * class AnonymousClasses$$anon$1 extends Animal {
   * qoverride def eat: Unit = println("ahahahaha")
   * }
   * val funnyAnimal: Animal = new AnonymousClasses$$anon$1
   */

  println(funnyAnimal.getClass)

  class Person(name: String) {
    def sayHi: Unit = println(s"Hi, my name is $name, how can I help you?")
  }

  val jim = new Person("Jim") {
    override def sayHi: Unit = println(s"Hi, my name is Jim, how could I be of service?")
  }
  // anonymous classes works for abstract or not abstract classes



}
