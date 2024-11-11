package lectures.part3fp

import scala.util.{Failure, Random, Success, Try}

object HandlingFailure extends App {

  // create success and failure
  val aSuccess = Success(3)
  val aFailure = Failure(new RuntimeException("SUPER FAILURE"))

  println(aSuccess)
  println(aFailure)

  def unsafeMethod(): String = throw new RuntimeException("NO STRING")

  // Try objects via the apply method
  val potentialFailure = Try(unsafeMethod())
  println(potentialFailure)

  // syntax sugar
  val anotherPotentialFailur = Try {
    // code that might throw
  }

  //utilities
  println(potentialFailure.isSuccess)

  //orElse
  def backupMethod(): String = "A valid result"
  val fallbackTry = Try(unsafeMethod()).orElse(Try(backupMethod()))
  println(fallbackTry)

  //If you design the API
  def betterUnsafeMethod(): Try[String] = Failure(new RuntimeException())
  def betterBackupMethod(): Try[String] = Success("A better valid result")
  val betterFallback = betterUnsafeMethod() orElse betterBackupMethod()
  println(betterFallback)


  // map, flatMap, filter
  println(aSuccess.map(_ * 2))
  println(aSuccess.flatMap(x => Success(x * 10)))
  println(aSuccess.filter(_ > 10))
  // => for-comprehensions

  /**
   * Exercise
   */
  println("Exercise-----------------")
  val hostname = "localhost"
  val port = "8080"
  def renderHTML(page: String) = println(page)

  class Connection {
    def get(url: String): String = {
      val random = new Random(System.nanoTime())
      if (random.nextBoolean()) "<hml>...</html>"
      else throw new RuntimeException("Connection interrupted")
    }

    def getSafe(url: String): Try[String] =
      Try(get(url))
  }

  object HttpService {
    val random = new Random(System.nanoTime())

    def getConnection(host: String, port: String): Connection = {
      if (true) new Connection
      else throw new RuntimeException("Some else took the port")
    }

    def getSafeConnection(host: String, port: String): Try[Connection] =
      Try(getConnection(host, port))

  }
  // if you get the html page from the connection, print it to the console i.e. call renderHTML
  val possibleConnection = HttpService.getSafeConnection(host = hostname, port = port)
  val possibleHTML = possibleConnection.flatMap(connection => connection.getSafe("/home"))
  possibleHTML.foreach(renderHTML)

  // shorthand version
  HttpService.getSafeConnection(hostname, port)
    .flatMap(connection => connection.getSafe("/home"))
    .foreach(renderHTML)

  // for-comprenhension version
  for {
    connection <- HttpService.getSafeConnection(hostname, port)
    htmlPage   <- connection.getSafe("/home")
  } renderHTML(htmlPage)

  /*
  try {
    connection = HttpService.getConnection(host, port)
    try {
      page = connection.get("/home")
      renderHTML(page)
     } catch (some other exception)

    } catch (exception) {
   }

   */
}
