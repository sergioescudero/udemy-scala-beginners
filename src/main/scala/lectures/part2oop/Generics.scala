package lectures.part2oop

object Generics extends App {

  //traits can be generics too.

  class MyList[+A] {
    //use the type A
    //def add(element: A): MyList[A] = ???
    // if covariant, it is replace by
    def add[B >: A](element: B): MyList[B] = ???

    /**
     * A = Cat
     * B = Dog, which is an Animal, B is basically Animal
     */
  }

  class MyMap[Key, Value]

  val listOfIntegers = new MyList[Int]
  val listOfStrings = new MyList[String]

  // generic methods
  object MyList {
    def empty[A]: MyList[A] = ???
  }

  val emptyListOfIntegers = MyList.empty[Int]

  //variance problem
  class Animal
  class Cat extends Animal
  class Dog extends Animal

  //then a list of cats extends of a list of Animals ?

  // 3 options
  // 1. Yes, List[Cat] extends List[Animal] = COVARIANCE
  class CovariantList[+A]
  val animal: Animal = new Cat
  val animalList: CovariantList[Animal] = new CovariantList[Cat]
  // animalList.add(new Dog) ??? -> return a list of animals

  // 2. No = INVARIANCE
  class InvariantList[A]
  val invariantAnimalList: InvariantList[Animal] = new InvariantList[Animal]

  // 3. Hell, No = CONTRAVARIANCE
  class Trainer[-A]
  val trainer: Trainer[Cat] = new Trainer[Animal]

  // bounded types
  class Cage[A <: Animal](animal: A) //class Cage only accepts subtypes of class Animal
  val cage = new Cage(new Dog)

  class Cage2[A >: Animal](animal: A) //class Cage2 only accepts supertypes of class Animal
  val cage2 = new Cage2(new Dog)

  // expand MyList to be generic

}
