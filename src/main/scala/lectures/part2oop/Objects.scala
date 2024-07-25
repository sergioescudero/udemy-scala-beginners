package lectures.part2oop

object Objects extends App {

  // SCALA DOES NOT HAVE CLASS-LEVEL FUNCTIONALITY ("static")
  object Person { // type + its only instance
    // "static"/"class" - level functionality
    val N_EYES = 2
    def canFly: Boolean = false

    // factory method
    def apply(mother: Person, father: Person): Person = new Person("Bobby")
  }
  class Person(val name : String){
    // instance-leve funtionality
  }
  // COMPANIONS

  println(Person.N_EYES)
  println(Person.canFly)

  // Scala object = SINGLETON INSTANCE
  val mary = Person
  val john = Person
  println(mary == john)
  println(s"object mary == object john -> ${mary==john}")

  val person1 = new Person("Mary")
  val person2 = new Person("John")
  println(s"class person1 == class person2 -> ${person1==person2}")

  val bobbie = Person(person1, person2)

  // Scala Apps = Scala Object with
  // def main(args: Array[String]): Unit


}
