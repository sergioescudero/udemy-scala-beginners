package lectures.part3fp

object MapFlatmapFilterFor extends App {

    val list = List(1, 2, 3)
    println(list)
    println(list.head)
    println(list.tail)

    //map
    println(list.map(_ + 1))
    println(list.map(_ + " is a number"))

    //filter
    println(list.filter(_ % 2 == 0))

    //flatMap
    val toPair = (x: Int) => List(x, x+1)
    println(list.flatMap(toPair))

    //print all combinations between two lists
    val numbers = List(1, 2, 3, 4)
    val chars = List('a', 'b', 'c', 'd')
    val combinations = numbers.flatMap(n => chars.map(c=> s"$c$n"))
    println(combinations)
    val colors = List("black", "white")
    val combinations3 = numbers.flatMap(n => chars.flatMap(c=> colors.map(color => s"$color-$c$n")))
    println(combinations3)

    // foreach
    list.foreach(println)

    //for-comprehensions
    val forCombinations = for {
      n <- numbers if n % 2 == 0 // equals to numbers.filter(_ % 2 == 0).flatMap(n => chars.flatMap(c=> colors.map(color => s"$color-$c$n")))
      c <- chars
      color <- colors
    } yield s"$color-$c$n"

    println(forCombinations)

    for {
      n <- numbers
    } println(n)

    // syntax overload
    println(list.map { x =>
      x * 2
    })

    /*
      1. MyList support for comprehensions?
        map(f: A => B) => MyList[B]
        filter(p: A => Boolean) => MyList[A]
        flat(f: A => MyList[B]) => MyList[B]
      2. A small collection of at most ONE element - Maybe[+T]
        - map, flatMap, filter
     */


}
