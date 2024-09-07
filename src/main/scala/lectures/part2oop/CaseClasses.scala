package lectures.part2oop

object CaseClasses extends App {

  /*
    equals, hashCode, toString
   */

  case class Person(name: String, age: Int)

  // 1. case class parameters are fields
  val jim = new Person("Jim", 34)
  println(jim.name)

  // 2. sensible toString for ease of debugging
  // println(instance) = println(instance.toString) // syntactic sugar
  println(jim.toString)

  // 3. equals and hash code implemented OOTB
  val jim2 = new Person("Jim", 34)
  println(jim == jim2)

  // 4. CCs have handy copy method
  val jim3 = jim.copy(age = 45)
  println(jim3)

  // 5. CCs have companion objects
  val thePerson = Person
  val mary = Person("Mary", 23)

  // 6. CCs are serializable
  // useful when dealing with distributed systems
  // Akka deals with sending serialized messages through the network

  // 7. CCs have extractor patterns
  // CCs can be used in pattern matching

  case object UnitedKingdom {
    def name: String = "The UK of GB and NI"
  }

  /*
    Expand MyList - use classes and case objects
   */
}
