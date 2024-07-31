package lectures.part2oop

import lectures.part2oop.Enums.Permissions.READ

object Enums extends App {

  enum Permissions {
    case READ, WRITE, EXECUTE, NONE

    // add fields/methods
    def openDocument(): Unit =
      if (this == READ) println("opening document ...")
      else println("reading now allowed.")
  }

  val somePermissions: Permissions = Permissions.READ

  //constructor args
  enum PermissionsWithBits(bits: Int) {
    case READ extends PermissionsWithBits(4) //100
    case WRITE extends PermissionsWithBits(2) //010
    case EXECUTE extends PermissionsWithBits(1) //001
    case NONE extends PermissionsWithBits(0) //000
  }

  object PermissionsWithBits {
    def fromBits(bits: Int): PermissionsWithBits =
      PermissionsWithBits.NONE
  }

  //standard API
  val somePermissionsOrdinal = somePermissions.ordinal

  val allPermissions = PermissionsWithBits.values // array of all possible values of the enum
  val readPermission: Permissions = Permissions.valueOf("READ")

  somePermissions.openDocument()
  println(somePermissionsOrdinal)

  println(readPermission)
}
