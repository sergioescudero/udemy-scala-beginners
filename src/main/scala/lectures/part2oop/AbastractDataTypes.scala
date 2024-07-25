package lectures.part2oop

object AbastractDataTypes extends App {

  //abstract

  abstract class Animal {
    val creatureType: String = "wild"
    def eat: Unit
  }

  class Dog extends Animal {
    override val creatureType: String = "Canine"
    override def eat: Unit = println("crunch crunch")
  }

  //traits

  trait Carnivore {
    def eat(animal: Animal): Unit
    val preferredMeal: String = "fresh meat"
  }

  trait ColdBlooded
  class Crocodrile extends Animal with Carnivore with ColdBlooded {
    override val creatureType: String = "croc"
    override def eat: Unit = println("nom nom nom")
    override def eat(animal: Animal): Unit = println(s"I am a croc and I'm eating ${animal.creatureType}")
  }

  val dog = new Dog
  val croc = new Crocodrile
  croc.eat(dog)


  // traits vs abstract classes
  // 1. traits do not have  constructor parameters
  // 2. multiple traits may be inherited by the same class
  // 3. traits = behavior, abstract class = "thing"
}
