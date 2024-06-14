package lectures.part2oop

object OOBasics extends App {

  val person = new Person("John", 26)
  println(person.age)
  println(person.x)

  person.greet("Daniel")

  person.greet()

  println("----------------------------------------------")
  val writer = new Writer("Leon", "Tolstoi", 1828)
  val imposter = new Writer("Leon", "Tolstoi", 1828)
  val novel = new Novel("Guerra y Paz", 1867, writer)

  println(novel.authorAge)
  println(novel.isWrittenBy(imposter))


  val counter = new Counter
  counter.inc.print
  counter.inc.inc.inc.print
  counter.inc(10).print
}


class Person(name: String, val age: Int) {
  val x = 2

  println(1 + 3)

  def greet(name: String): Unit = println(s"${this.name} says: Hi, $name")

  def greet(): Unit = println(s"Hi, I am $name")

  def this(name: String) = this(name, 0)

  /**
   * Novel and writer classes
   */
}

class Writer(firstName: String, surname: String, val year: Int) {

  def fullName: String = s"$firstName $surname"
}

class Novel(name: String, year: Int, author: Writer) {

  def authorAge: Int = year - author.year

  def isWrittenBy(author: Writer): Boolean = author == this.author

  def copy(newYearRelease: Int) = new Novel(name, newYearRelease, author)
}

class Counter(val count: Int = 0){

  def inc = {
    println("incrementing")
    new Counter(count + 1)
  }

  def dec = {
    println("decrementing")
    new Counter(count - 1)
  }

  def inc(n: Int): Counter  = {
    if (n <= 0) this
    else inc.inc(n-1)
  }

  def dec(n: Int): Counter = {
    if (n <= 0) this
    else dec.dec(n - 1)
  }

  def print = println(count)
}


