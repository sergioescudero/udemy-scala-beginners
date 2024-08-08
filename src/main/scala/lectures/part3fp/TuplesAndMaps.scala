package lectures.part3fp

import scala.annotation.tailrec
import scala.collection.MapView

object TuplesAndMaps extends App {

  //tuples = finite ordered "lists"
  val aTuple: (Int, String) = new Tuple2(2, "hello, Scala") //Tuple2[Int, String] = (Int, String)
  val aTuple2 = (2, "hello, Scala") //max 22 elements

  println(aTuple._1)
  println(aTuple.copy(_2 = "Goodbye java"))
  println(aTuple.swap) //("hello, Scala", 2)

  // Maps - keys -> values
  val aMap: Map[String, Int] = Map()

  //val phoneBook = Map(("Jim", 555), ("Pepe", 789)) or
  val phoneBook = Map(("Jim", 555), "Pepe" -> 789).withDefaultValue(-1)
  // a -> b is sugar for (a, b)
  println(phoneBook)

  //maps ops
  println(phoneBook.contains("Jim"))
  println(phoneBook.get("Jim")) // return Option[Int]
  println(phoneBook("Jim")) // return Int

  // add a pairing
  val newPairing = "Mary" -> 678
  val newPhoneBook = phoneBook + newPairing
  println(newPhoneBook)

  //functionals on maps
  // map, flatMap, filter
  println(phoneBook.map(pair => pair._1.toLowerCase -> pair._2))

  //filterKeys
  println(phoneBook.view.filterKeys(x => x.startsWith("J")).toMap)

  //mapValues
  println(phoneBook.view.mapValues(number => number * 10).toMap)
  println(phoneBook.view.mapValues(number => "02655" + number).toMap)

  // conversions to other collections
  println(phoneBook.toList)
  println(List(("Daniel", 555)).toMap)

  val names = List("Bob", "James", "Angela", "Mary", "Daniel", "Jim")
  println(names.groupBy(name => name.charAt(0)))

  /*
    1. What would happen if I had 2 original entries "Jim" -> 555 AND "JIM" ->  900
        !!! careful with mapping keys
    2. Overly simplified social network based on maps
        Person = String
          - add a person to the network
          - remove
          - friend (mutual)
          - unfriend

          - number of friends of a person
          - person with most friends
          - how many people have NO friends
          - if there is a social connection between 2 people (direct or not)
   */
  val exercise1 = Map ("Jim" -> 555, "JIM" -> 900)
  println(exercise1)
  val exercise1Copy = exercise1.map(pair => pair._1.toUpperCase -> pair._2)
  println(exercise1Copy)

  val socialNetwork = Map.empty[String, Set[String]].withDefaultValue(Set())

  def add(socialNetwork: Map[String, Set[String]], person: String): Map[String, Set[String]] =
    socialNetwork + (person -> Set())
  println(s"adding Sergio to socialNetwork ${add(socialNetwork, "Sergio")}")

  def friend(socialNetwork: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] =
    //val x = socialNetwork.updated(person, socialNetwork(person) ++ List(friend))
    //x.updated(friend, socialNetwork(friend) ++ List(person))
    val friendsA = socialNetwork(a)
    val friendsB = socialNetwork(b)
    socialNetwork + (a -> (friendsA + b))  + (b -> (friendsB + a))

  println(friend(socialNetwork, "Juan", "Antonio"))

  val socialNetworkUpdated = friend(socialNetwork, "Juan", "Antonio")

  def unfriend(socialNetwork: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] =
    //val x = socialNetwork.updated(person, socialNetwork(person).filter(x => x != friend))
    //x.updated(friend, socialNetwork(friend).filter(x => x != person))
    val friendsA = socialNetwork(a).filter(x => x != b)
    val friendsB = socialNetwork(b).filter(x => x != a)
    socialNetwork + (a -> friendsA) + (b -> friendsB) // or -


  def remove(socialNetwork: Map[String, Set[String]], person: String): Map[String, Set[String]] = {
    def removeAux(friends: Set[String], socialNetworkAcc: Map[String, Set[String]]): Map[String, Set[String]] =
      if (friends.isEmpty) socialNetworkAcc
      else removeAux(friends.tail, unfriend(socialNetworkAcc, person, friends.head))

    val unfriended = removeAux(socialNetwork(person), socialNetwork)
    unfriended - person
  }

  val empty: Map[String, Set[String]] = Map()
  val network = add(add(empty, "Bob"), "Mary")
  println(network)
  println(friend(network, "Bob", "Mary"))
  println(unfriend(friend(network, "Bob", "Mary"), "Bob", "Mary"))
  println(remove(friend(network, "Bob", "Mary"), "Bob"))

  // Jim, Bob, Mary
  val people = add(add(add(empty, "Bob"), "Mary"), "Jim")
  val jimBob = friend(people, "Bob", "Jim")
  val testNet = friend(jimBob, "Bob", "Mary")

  println(testNet)

  def nFriends(network: Map[String, Set[String]], person: String): Int =
    if (!network.contains(person)) 0
    else network(person).size

  println(nFriends(testNet, "Bob"))
  println(nFriends(testNet, "Jim"))

  def mostFriends(network: Map[String, Set[String]]): String =
    network.maxBy(pair => pair._2.size)._1

  println(mostFriends(testNet))

  def nPeopleWithNoFriends(network: Map[String, Set[String]]): Int =
    //network.view.filterKeys(k => network(k).isEmpty).size
    network.count(pair => pair._2.isEmpty)

  println(nPeopleWithNoFriends(testNet))

  def socialConnection(network: Map[String, Set[String]], a: String, b: String): Boolean = {
    //breadthFirstSearch
    @tailrec
    def bfs(target: String, consideredPeople: Set[String], discoveredPeople: Set[String]): Boolean = {
      if (discoveredPeople.isEmpty) false
      else {
        val person = discoveredPeople.head
        if (person == target) true
        else if (consideredPeople.contains(person)) bfs(target, consideredPeople, discoveredPeople.tail)
        else bfs(target, consideredPeople + person, discoveredPeople.tail ++ network(person))
      }
    }
    bfs(b, Set(a), network(a) + a)
  }

  println(socialConnection(testNet, "Mary", "Jim"))

  println(socialConnection(network, "Mary", "Bob"))
}
