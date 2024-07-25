package lectures.part2oop

object Inheritance extends App {

  // single class inheritance
  sealed class Animal {
    val creatureType = "wild"
    def eat = println("ñam ñam")
  }

  class Cat extends Animal {
    def crunch = {
      eat
      println("crunch crunch")
    }
  }

  val cat = new Cat
  cat.crunch

  // constructors
  class Person(name: String, age: Int) {
    def this(name: String) = this(name, 0)
  }

  class Adult(name: String, age: Int, idCard: String) extends Person(name)

  //overriding
  //class Dog(override val creatureType: String) extends Animal {
    //override val creatureType = "domestic"
    //override def eat = println("crunchy crunchy")
  //}
  // or
  class Dog(dogType: String) extends Animal {
    override val creatureType = dogType
    override def eat = {
      super.eat
      println("crunchy crunchy")
    }
  }

  val dog = new Dog("K9")
  dog.eat
  println(dog.creatureType)

  // type substitution (broad: polymorphism)
  val unknownAnimal: Animal = new Dog("K99")
  unknownAnimal.eat

  // overRIDING vs overLOADING

  // super call parent class

  //preventing overrides
  // 1. final on member
  // 2. final on class
  // 3. seal the class = extend class in THIS FILE, prevent extension in other files

}
