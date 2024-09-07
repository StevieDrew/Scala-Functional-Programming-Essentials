package lectures.part2oop

object Generics extends App{
  class MyList[+A] {
    // use the type A
    def add[B >: A](element: B): MyList[B] = ???
    /*
      A = Cat
      B = Animal
     */
  }

  class MyMap[key, Value]
  val listOfIntegers = new MyList[Int]
  val listOfStrings = new MyList[String]

  // Generic Methods
  object MyList {
    def empty[A]: MyList[A] = ???
  }
  val emptyListOfIntegers = MyList.empty[Int]

  class Animal
  class Cat extends Animal
  class Dog extends Animal

  // 1. yes List[Cat] extends List[Animal] = COVARIANCE
  class CovariantList[+A]
  val animal: Animal = new Cat
  val animalList: CovariantList[Animal] = new CovariantList[Cat]
  // animalList.add(new Dog) ??? HARD QUESTION => we return a list of animals

  // 2. NO = INVARIANCE
  class InvariantList[A]
  val invariantAnimalList: InvariantList[Animal] = new InvariantList[Animal]

  // 3. Hell, no! CONTRAVARIANCE
  class Trainer[-A]
  val trainer: Trainer[Cat] = new Trainer[Animal]

  //bounded types
  // Class cage only accepts type A which are subtypes of class Animal
  // <: - Upper Bounded type
  // >: - Lower Bounded type (only accepts something that is super type of Animal, or whatever class)
  class Cage[A <: Animal](animal: A)
  val cage = new Cage(new Dog)

  class Car
  // generic type needs proper bounded type
  // val newCage = new Cage(new Car)

  // bounded types can solve variance problems

  // expand MyList to be generic

}
