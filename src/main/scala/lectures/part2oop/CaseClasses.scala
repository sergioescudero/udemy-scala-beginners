package lectures.part2oop

object CaseClasses extends App {


  case class Person(name: String, age: Int)

  // 1. class parameters are fields
  val jim  = new Person("Jim", 34)
  println(jim.name)

  // 2. sensible toString
  //println(instance) = println(instance.toString) // syntactic sugar
  println(jim.toString)
  println(jim)

  // 3. equals and hashCode implemented OOTB (out of the box)
  val jim2 = new Person("Jim", 34)
  println(jim == jim2)

  // 4. case classes have handy copy method
  val jim3 = jim.copy(age=45)
  println(jim3)

  //5. case classes have companion objects
  val thePerson = Person
  println(thePerson)
  val mary = Person("Mary", 23)

  // 6. case classes are serializable
  // akka

  // 7. case classes have extractor patters = CCs can be used in PATTERN MATCHING

  case object UnitedKingdom {
    def name: String = "The UKK of GB and NI"
  }

  /*
  Expand MyList - use classes and case objects
   */
}
