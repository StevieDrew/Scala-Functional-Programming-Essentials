package lectures.part3fp

object TuplesAndMaps extends App {

  // Tuples = finite ordered "lists"
  val aTuple = (2, "hello scala") // Tuple2[Int, String] = (Int, String)

  println(aTuple._1)  // 2
  println(aTuple.copy(_2 = "goodbye Java"))
  println(aTuple.swap)  // ("hello scala", 2)

  // Maps - keys -> values (immutable)
  val aMap: Map[String, Int] = Map()

  val phoneBook = Map(("Jim", 555), "Daniel" -> 789).withDefaultValue(-1)
  // a -> b is sugar for (a,b)
  println(phoneBook)


  // map ops
  println(phoneBook.contains("Jim"))
  println(phoneBook("Mary"))

  // add a pairing
  val newPairing = "Mary" -> 678
  val newPhonebook = phoneBook + newPairing
  println(newPhonebook)

  // functionals on maps
  // map, flatMap, filter
  println(phoneBook.map(pair => pair._1.toLowerCase -> pair._2))

  // filterKeys
  println(phoneBook.filterKeys(x => x.startsWith("J")))
  // mapValues
  println(phoneBook.mapValues(number => "0245-" + number))

  // conversions to other collections
  println(phoneBook.toList)
  println(List(("Daniel",555),("Steven",307)).toMap)
  val names = List("Bob", "James", "Angela", "Mary", "Daniel", "Jim")
  println(names.groupBy(name => name.charAt(0)))

  /*
    1. What would happen if I had two original entries "Jim" -> 555 and "JIM" -> 900 and I ran line 31

       !!! careful with mapping keys
    2. Overly simplified social network based on maps
       Person = String
       - add a person to the network
       - remove
       - friend (add someone as friend (mutual))
       - unfriend

       - number of friends of a person
       - person with most friends
       - how many people have no friends
       - if there is a social connection between 2 people (direct or not)
   */

  val exercise1 = Map("Jim" -> 999, "JIM" -> 900)
  println(exercise1.map(pair => pair._1.toLowerCase -> pair._2))


  def add(network: Map[String, Set[String]], person: String): Map[String, Set[String]] =
    network + (person -> Set())

  def friend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
    val friendsA = network(a)
    val friendsB = network(b)

    network + (a -> (friendsA + b)) + (b -> (friendsB + a))
  }

  def unfriend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
    val friendsA = network(a)
    val friendsB = network(b)

    network + (a -> (friendsA - b))+ (b -> (friendsB - a))
  }

  def remove(network: Map[String, Set[String]], person: String): Map[String, Set[String]] = {
    def removeAux(friends: Set[String], networkAcc: Map[String, Set[String]]): Map[String, Set[String]] =
      if (friends.isEmpty) networkAcc
      else removeAux(friends.tail, unfriend(networkAcc, person, friends.head))

    val unfriended = removeAux(network(person), network)
    unfriended - person
  }

  val empty: Map[String, Set[String]] = Map()
  val network = add(add(empty, "Bob"), "Mary")
  println(network)
  println(friend(network, "Bob", "Mary"))
  println(unfriend(friend(network, "Bob", "Mary"), "Bob", "Mary"))
  println(remove(friend(network, "Bob", "Mary"), "Bob"))


  // Jim,Bob,Mary
  val people = add(add(add(empty, "Bob"), "Mary"), "Jim")
  val jimBob = friend(people, "Bob", "Jim")
  val testNet = friend(jimBob, "Bob", "Mary")

  println(testNet)

  def nFriends(network: Map[String, Set[String]], person: String): Int =
    if (!network.contains(person)) 0
    else network(person).size

  println(nFriends(testNet, "Bob"))

  def mostFriends(network: Map[String, Set[String]]): String =
    network.maxBy(pair => pair._2.size)._1

  println(mostFriends(network))

  def nPeopleWithNoFriends(network: Map[String, Set[String]]): Int =
    network.count(pair => pair._2.isEmpty)

  println(nPeopleWithNoFriends(testNet))

  def socialConnection(network: Map[String, Set[String]], a: String, b: String): Boolean = {
    def bfs(target: String, consideredPeople: Set[String], discoveredPeople: Set[String]): Boolean = {
      if (discoveredPeople.isEmpty) false
      else {
        val person = discoveredPeople.head
        if (person == target) true
        else if (consideredPeople.contains(person)) bfs(target, consideredPeople, discoveredPeople.tail)
        else bfs(target, consideredPeople + person, discoveredPeople.tail ++ network(person))
      }
    }

    bfs(b, Set(), network(a) + a)
  }

  println(socialConnection(testNet, "Mary", "Jim"))
  println(socialConnection(network, "Mary", "Bob"))

}
